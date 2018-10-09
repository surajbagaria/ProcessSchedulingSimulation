
public class Simulation {
	public static void main(String args[]) {
		ProcessFactory pf = new ProcessFactory();
		pf.createPool();
	
		pf.sortProcess();
		pf.getAllProcess();
		pf.getSimulatedInfo();
		
		
		
		Algorithms fcfs = new FirstComeFirstServe(pf.getPool());
		System.out.println(fcfs.run().getParameterInfo());
	}
}
