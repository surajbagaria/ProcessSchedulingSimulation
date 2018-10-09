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
		System.out.println(((Process) queue.peek()).getInfo());
		
		parameters.setAvgTurnAroundTime(15);
		parameters.setAvgWaitingTime(12);
		parameters.setAvgResponseTime(16);
		return parameters;
	}

}
