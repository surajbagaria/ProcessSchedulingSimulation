
public class Simulation {
	public static void main(String args[]) {
		ProcessFactory pf = new ProcessFactory();
		pf.createPool();
	
		pf.sortProcess();
		pf.getAllProcess();
		pf.getSimulatedInfo();
		
		
		
		//Algorithms fcfs = new FirstComeFirstServe(pf.getPool());
		//System.out.println(fcfs.run().getParameterInfo());
		
		Algorithms sjf = new ShortestJobFirst(pf.getPool());
		System.out.println(sjf.run().getParameterInfo());
		
		//Algorithms rr = new RoundRobin(pf.getPool());
		//System.out.println(rr.run().getParameterInfo());
	}
}
