
public class Simulation {
	public static void main(String args[]) {
		ProcessFactory pf = new ProcessFactory();
		pf.createPool();
	
		pf.sortProcess();
		pf.getAllProcess();
		pf.getSimulatedInfo();
		
		
		
		Algorithms rr = new RoundRobin(pf.getPool());
		System.out.println(rr.run().getParameterInfo());
	}
}