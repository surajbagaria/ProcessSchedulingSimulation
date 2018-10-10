import java.util.Arrays;
import java.util.LinkedList;

public class ShortestJobFirst extends Algorithms {
	
	Parameters parameters;
	public ShortestJobFirst(Process[] processPool) {
		super.queue = new LinkedList<>(Arrays.asList(sjfnpSort(processPool)));
		parameters = new Parameters();
	}
	
	
	@Override
	public Parameters run() {
		
		double totalWT = 0.0;
		double totalTAT = 0.0;
		double totalRT = 0.0;
		double avgwait = 0.0;
		double avgturnaround = 0.0;
		double avgresponse = 0.0;
	    double throughput = 0.0;

		double timeQuantum = 0.0;
		int processedJobsCount = 0; // keep track of the number of jobs processed between time quanta 0-99

		int startingQueueSize = queue.size();
		Process prevJob = null;
		while (!queue.isEmpty()) {

			// no process should get the CPU for the first time after time quantum 99
			if (timeQuantum <= 99.0) {

				Process currJob = queue.peek();

				// if first job to be processed or there is some idle time
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
				int prevtime = (int)timeQuantum;
				timeQuantum = currJob.getCompletionTime();
                                if(currJob.getArrivalTime() > prevtime){
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
					System.out.println("Job #" + queue.peek().getId() + " was not processed because it got CPU for the first time after time quantum 99.");
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
	
	
	public Process[] sjfnpSort(Process[] processPool) {

		Process tempJob = null;
		double refTime = 0.0; // reference time for sorting by run time

		for (int i = 0; i < processPool.length; i++) {
			if (processPool[i].getArrivalTime() > refTime) {
				refTime = processPool[i].getArrivalTime();
			}
			// for all the available processes (i.e. processes that already arrived and are ready to be processed), 
			// sort by increasing order of run time (i.e. shortest to longest run time)
			for (int k = i + 1; k < processPool.length; k++) {
				if (processPool[k].getArrivalTime() <= refTime && processPool[k].getRunTime() < processPool[i].getRunTime()) {
					tempJob = processPool[i];
					processPool[i] = processPool[k];
					processPool[k] = tempJob;
				}
			}
			refTime += processPool[i].getRunTime();
		}

		return processPool;
}

}
