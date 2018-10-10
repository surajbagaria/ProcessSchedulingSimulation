import java.util.Arrays;
import java.util.LinkedList;


public class FirstComeFirstServe extends Algorithms{
	
	

	Parameters parameters;
	
	public FirstComeFirstServe(Process[] processPool) {
		super.queue = new LinkedList<>(Arrays.asList(processPool));
		parameters = new Parameters();
	}
	

	@Override
	public Parameters run() {
		//write code....
		
		double totalWT = 0.0;
		double totalTAT = 0.0;
		double totalRT = 0.0;
		double avgwait = 0.0;
		double avgturnaround = 0.0;
		double avgresponse = 0.0;
	    double throughput = 0.0;

		int timeQuantum = 0;
		int processedJobsCount = 0; // keep track of the number of jobs processed between time quanta 0-99

		int startingQueueSize = queue.size();
		Process prevJob = null;

		int prevtime = 0;

		while (!queue.isEmpty()) {
			// no process should get the CPU for the first time after time quantum 99
			if (timeQuantum <= 99 ) {
				Process currJob = (Process) queue.peek();

				if (startingQueueSize == queue.size() || (currJob.getArrivalTime() > prevJob.getCompletionTime())) {
					currJob.setCompletionTime(currJob.getArrivalTime() + currJob.getRunTime());
					currJob.setWaitingTime(0);
					currJob.setResponseTime(0);
				} else {
					currJob.setCompletionTime(prevJob.getCompletionTime() + currJob.getRunTime());
					currJob.setWaitingTime(prevJob.getCompletionTime() - currJob.getArrivalTime());
					currJob.setResponseTime(prevJob.getCompletionTime() - currJob.getArrivalTime());
				}

				currJob.setTurnAroundTime(currJob.getWaitingTime() + currJob.getRunTime());

				totalWT += currJob.getWaitingTime();
				totalTAT += currJob.getTurnAroundTime();
				totalRT += currJob.getResponseTime();

				// on last iteration/processed job, this will be the time it took to finish all the jobs between time quanta 0-99
				prevtime = (int)timeQuantum;
				timeQuantum = currJob.getCompletionTime();
				if(currJob.getArrivalTime() > prevtime || processedJobsCount > 10){
					int diff = currJob.getArrivalTime() - prevtime;
					for(int x = 0; x<diff; x++){
						System.out.println("Quant: "+(prevtime+x)+"\t|\t IDLE");
					}
					prevtime += diff;
				}
				if(timeQuantum <= 99){
					for(int x = prevtime; x<timeQuantum; x++){
						System.out.println("Quant: "+x+"\t|\t #"+currJob.getId());
					}
				}else{
					for(int x = prevtime; x<=99; x++){
						System.out.println("Quant: "+x+"\t|\t #"+currJob.getId());
						processedJobsCount--;
						
					}
				}
				
				processedJobsCount++;
				prevJob = currJob;

				
			} else {
				if (timeQuantum > 99) {
					Process currJob = (Process) queue.peek();
					System.out.println("Job #" + currJob.getId() + " is not processed because it got CPU for the first time after time quantum 99.");
					timeQuantum = 99;
				}

			}
			
			queue.remove();
		}

		if (timeQuantum < 99.0) {
			for(; timeQuantum <= 99.0; ){
                System.out.println("Quant: "+(int)(timeQuantum++)+"\t|\t IDLE");
        }
		}
		
		avgwait = totalWT / processedJobsCount;
		avgturnaround = totalTAT / processedJobsCount;
		avgresponse = totalRT / processedJobsCount;
        throughput = processedJobsCount / timeQuantum;
		
		parameters.setAvgTurnAroundTime(avgturnaround);
		parameters.setAvgWaitingTime(avgwait);
		parameters.setAvgResponseTime(avgresponse);
		parameters.setThroughput(throughput);
		return parameters;
	}

}
