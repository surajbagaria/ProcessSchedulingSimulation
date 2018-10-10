import java.util.Arrays;


public class ShortestRemainingTime extends Algorithms {

    Parameters parameters;
    public ShortestRemainingTime(Process[] processPool) {	
		super.processPool = processPool;
		parameters = new Parameters();
	}
	
	@Override
	public Parameters run() {
		// TODO Auto-generated method stub
		Process[] proc = super.processPool;
		findavgTime(proc, proc.length); 
		return parameters;
	}
	
    // Method to calculate average time 
    public void findavgTime(Process proc[], int n) 
    { 
        int wt[] = new int[n], tat[] = new int[n]; 
        int  total_wt = 0, total_tat = 0; 
       
        // Function to find waiting time of all 
        // processes 
        findWaitingTime(proc, n, wt); 
       
        // Function to find turn around time for 
        // all processes 
        findTurnAroundTime(proc, n, wt, tat); 
       
        // Display processes along with all 
        // details 
        System.out.println("Processes " + 
                           " Burst time " + 
                           " Waiting time " + 
                           " Turn around time"); 
       
        // Calculate total waiting time and 
        // total turnaround time 
        for (int i = 0; i < n; i++) { 
            total_wt = total_wt + wt[i]; 
            total_tat = total_tat + tat[i];
            
            proc[i].setWaitingTime(wt[i]);
            proc[i].setTurnAroundTime(tat[i]);
            System.out.println(" " + proc[i].getId() + "\t\t"
                             + proc[i].getRunTime() + "\t\t " + wt[i] 
                             + "\t\t" + tat[i]); 
        } 
       
        parameters.setAvgWaitingTime((double)total_wt/(double)n);
        parameters.setAvgTurnAroundTime((double)total_tat/(double)n);
        System.out.println("Average waiting time = " + 
                          (double)total_wt / (double)n); 
        System.out.println("Average turn around time = " + 
                           (double)total_tat / (double)n); 
    }
    
    // Method to calculate turn around time 
    static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) 
    { 
        // calculating turnaround time by adding 
        // bt[i] + wt[i] 
        for (int i = 0; i < n; i++) 
            tat[i] = proc[i].getRunTime() + wt[i]; 
    } 
    
    // Method to find the waiting time for all 
    // processes 
    static void findWaitingTime(Process proc[], int n, int wt[]) 
    { 
        int rt[] = new int[n]; 
       
        // Copy the burst time into rt[] 
        for (int i = 0; i < n; i++) 
            rt[i] = proc[i].getRunTime(); 
       
        int complete = 0, t = 0, minm = Integer.MAX_VALUE; 
        int shortest = 0, finish_time; 
        boolean check = false; 
       
        // Process until all processes gets 
        // completed 
        while (complete != n) { 
       
            // Find process with minimum 
            // remaining time among the 
            // processes that arrives till the 
            // current time` 
            for (int j = 0; j < n; j++)  
            { 
                if ((proc[j].getArrivalTime() <= t) && 
                  (rt[j] < minm) && rt[j] > 0) { 
                    minm = rt[j]; 
                    shortest = j; 
                    check = true; 
                } 
            } 
       
            if (check == false) { 
                t++; 
                check = false;
                continue; 
            } 
            //System.out.println("Before ==>>> " + rt[shortest]);
            // Reduce remaining time by one 
            rt[shortest]--; 
            //System.out.println("After ===>> " + rt[shortest]);
            // Update minimum 
            minm = rt[shortest]; 
            if (minm <= 0) 
                minm = Integer.MAX_VALUE; 
       
            // If a process gets completely 
            // executed 
            if (rt[shortest] <= 0) { 
       
                // Increment complete 
                complete++; 
       
                // Find finish time of current 
                // process 
                finish_time = t + 1; 
       
                // Calculate waiting time 
                wt[shortest] = finish_time - proc[shortest].getArrivalTime() -
                             proc[shortest].getRunTime(); 
       
                if (wt[shortest] < 0) 
                    wt[shortest] = 0; 
            } 
            // Increment time 
            t++; 
        } 
    } 
	
	/*public Parameters calculateAverageTime() {
		Process p[] = super.processPool;
		System.out.println("Came here ==>>>" + p.length);
		int wait_time[] = new int[p.length];
		int turn_around_time[] = new int[p.length];
		int totalWaitTime = 0, totalTurnAroundTime = 0;
		
		wait_time = calculateWaitingTime(p, wait_time);
		System.out.println("wt ==>>" + wait_time);
		
		turn_around_time = calculateTurnAroundTime(p, turn_around_time, wait_time);
		
		for(int i=0; i<p.length; i++) {
			totalWaitTime = totalWaitTime + wait_time[i];
			totalTurnAroundTime = totalTurnAroundTime + turn_around_time[i];
		}
		
		for(int i=0 ;i<p.length; i++) {
			p[i].setWaitingTime(wait_time[i]);
			p[i].setTurnAroundTime(turn_around_time[i]);
		}
		
		parameters.setAvgWaitingTime(totalWaitTime/p.length);
		parameters.setAvgTurnAroundTime(totalTurnAroundTime/p.length);
		return parameters;
	}*/
	
	/*private int[] calculateTurnAroundTime(Process[] p, int[] turn_around_time, int[] wait_time) {
		// TODO Auto-generated method stub
		for(int i=0; i<p.length; i++) {
			turn_around_time[i] = p[i].getRunTime() + wait_time[i];
		}
		return turn_around_time;
	}
	public int[] calculateWaitingTime(Process[] p, int[] wait_time) {
		int remaining_time[] = new int[p.length];
		for(int i=0; i<p.length; i++)
			remaining_time[i] = p[i].getRunTime();
		System.out.println("rem time ==>>" + Arrays.toString(remaining_time));
		int processesCompleted = 0, minLeftTime = Integer.MAX_VALUE, short_process = 0, end_time, t=0;
		boolean flag = false;
		while(processesCompleted != p.length) {
			for(int j=0; j<p.length; j++) {
				if((p[j].getArrivalTime() <= t) && (remaining_time[j] < minLeftTime) && remaining_time[j] > 0) {
					minLeftTime = remaining_time[j];
					short_process = j;
					flag = true;
				}
			}
			
			if(!flag) {
				t++;
				continue;
			}
			remaining_time[short_process]--;
			
			minLeftTime = remaining_time[short_process];
			if(minLeftTime == 0)
				minLeftTime = Integer.MAX_VALUE;
			
			if(remaining_time[short_process] == 0) {
				flag = false;
				processesCompleted++;
				end_time = t+1;
				wait_time[short_process] = end_time - p[short_process].getRunTime() - p[short_process].getTurnAroundTime() - p[short_process].getArrivalTime();
				
				if(wait_time[short_process] < 0) {
					wait_time[short_process] = 0;
				}
			}
			t++;
			System.out.println("See this ==>>" + t);
		}
		return wait_time;
	}*/
}
