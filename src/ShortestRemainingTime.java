import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestRemainingTime extends Algorithms {

	Parameters parameters;
	public ShortestRemainingTime(Process[] processPool) {
		super.processPool = processPool;
		parameters = new Parameters();
	}
	
	@Override
	public Parameters run() {
		// TODO Auto-generated method stub
		System.out.println("HEre===============");
		parameters = calculateAverageTime();
		System.out.println(parameters.getParameterInfo());
		return parameters;
	}
	
	public Parameters calculateAverageTime() {
		Process p[] = super.process;
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
		
		parameters.setAvgWaitingTime(totalWaitTime/p.length);
		parameters.setAvgTurnAroundTime(totalTurnAroundTime/p.length);
		return parameters;
	}
	
	private int[] calculateTurnAroundTime(Process[] p, int[] turn_around_time, int[] wait_time) {
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
				processesCompleted++;
				end_time = t+1;
				wait_time[short_process] = end_time - p[short_process].getRunTime() - p[short_process].getTurnAroundTime() - p[short_process].getArrivalTime();
				
				if(wait_time[short_process] < 0) {
					wait_time[short_process] = 0;
				}
			}
			t++;
		}
		return wait_time;
	}
}
