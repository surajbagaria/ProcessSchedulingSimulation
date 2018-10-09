import java.util.Arrays;
import java.util.LinkedList;

public class RoundRobin extends Algorithms {

	
	Parameters parameters;
	public RoundRobin(Process[] processPool) {
		super.processPool = processPool;
		parameters = new Parameters();
	}
	@Override
	public Parameters run() {
		int quantum = 1;
		int n = super.processPool.length;
		System.out.println(n);
		int[] bt = new int[n];
		int[] wt = new int[n];
		for (int i =0; i < super.processPool.length; i++) {
			bt[i] = super.processPool[i].getRunTime();
		}
		wt = findWatingTime(super.processPool, n, bt, wt, quantum);
		
		for(int i =0; i < n; i++) {
			super.processPool[i].setWaitingTime(wt[i]);
		}
		
		return null;
	}
	
	public int[] findWatingTime(Process[] processPool, int n, int bt[], int wt[], int quantum) {
		
		int rem_bt[] = new int[n];
		for(int i =0; i < n; i++)
			rem_bt[i] = bt[i];
		
		int t =0; //current time.
		
		while(true) {
			boolean done = true;
			
			for (int i =0; i<n ; i++) {
				if(rem_bt[i] >0) {
					done = false;
					
					if (rem_bt[i] > quantum) {
						t += quantum;
						
						rem_bt[i] -= quantum;
					}
					
					else {
						t = t + rem_bt[i];
						
						wt[i] = t - bt[i];
						
						System.out.println("waiting time"+i + ":::"+wt[i]);
						
						rem_bt[i] = 0;
					}
				}
			}
			
			if (done == true)
				break;
				
		}
		return wt;
		
	}

}
