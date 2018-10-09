import java.util.Arrays;

public class ProcessFactory {
	Process[] processPool = new Process[10];

	public void createPool() {
		for (int i = 0; i < processPool.length; i++) {
			processPool[i] = new Process(Integer.toString(i + 1));
			processPool[i].setArrivalTime();
			processPool[i].setRunTime();
			processPool[i].setPriority();

		}

	}

	public void sortProcess() {
		int[] arrayOfArrivalTime = new int[10];
		for (int i = 0; i < processPool.length; i++) {
			arrayOfArrivalTime[i] = processPool[i].getArrivalTime();
		}

		Arrays.sort(arrayOfArrivalTime);
		
		for (int i = 0; i < arrayOfArrivalTime.length; i++) {
			processPool[i].arrivalTime = arrayOfArrivalTime[i];
		}
		
		processPool[0].arrivalTime = 0;
		
		
	}
	
	public void getAllProcess() {
		for (int i =0; i < processPool.length; i++) {
			System.out.println(processPool[i].getInfo());
		}
	}
	
	public void getSimulatedInfo() {
		for (int i =0; i < processPool.length; i++) {
			System.out.println(processPool[i].getSimulation());
		}
	}
	
	public Process[] getPool() {
		return this.processPool;
	}
}
