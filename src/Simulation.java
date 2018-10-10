
public class Simulation {
	public static void main(String args[]) {
		ProcessFactory pf = new ProcessFactory();
		pf.createPool();
		pf.sortProcess();
		pf.getAllProcess();
				
		Algorithms fcfs = new FirstComeFirstServe(pf.getPool());
		System.out.println(fcfs.run().getParameterInfo());
				
		Algorithms sjf = new ShortestJobFirst(pf.getPool());
		System.out.println(sjf.run().getParameterInfo());

		
		//Algorithms rr = new RoundRobin(pf.getPool());
		//System.out.println(rr.run().getParameterInfo());
		//pf.getSimulatedInfo();
		
		Algorithms srtf = new ShortestRemainingTime(pf.getPool());
		System.out.println(srtf.run().getParameterInfo());
		
/*		Algorithms rr = new RoundRobin(pf.getPool());
		System.out.println(rr.run().getParameterInfo());*/

		pf.getSimulatedInfo();
	}
}