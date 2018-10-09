import java.util.Arrays;
import java.util.LinkedList;


public class FirstComeFirstServe extends Algorithms{
	
	

	Parameters parameters;
	
	public FirstComeFirstServe(Process[] processPool) {
		super.processPool = processPool;
		parameters = new Parameters();
	}
	

	@Override
	public Parameters run() {
		//write code....
		
		
		parameters.setAvgTurnAroundTime(15);
		parameters.setAvgWaitingTime(12);
		parameters.setAvgResponseTime(16);
		return parameters;
	}

}
