import java.util.Arrays;
import java.util.LinkedList;

public class ShortestJobFirst extends Algorithms {
	
	Parameters parameters;
	public ShortestJobFirst(Process[] processPool) {
		super.queue = new LinkedList<>(Arrays.asList(processPool));
		parameters = new Parameters();
	}
	
	
	@Override
	public Parameters run() {
		
		return null;
	}

}
