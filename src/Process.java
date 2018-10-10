import java.util.Random;

public class Process {
	String id;
	int arrivalTime;
	int runTime;
	int priority;
	int responseTime;
	int turnAroundTime;
	int waitingTime;
	
	int completionTime;
	Random rn = new Random();


public Process(String id) {
	this.id = id;
}

public void setArrivalTime() {
	
	arrivalTime = Math.abs(rn.nextInt() % 100);
}

public void setRunTime() {
	this.runTime = Math.abs(rn.nextInt() % 11);
}

public void setPriority() {
	int a = Math.abs(rn.nextInt() % 5);
	if (a != 0)
	{
		this.priority = a;
	}
	else
	{
		a = a + 1;
		this.priority = a;
		
	}
}

public void setCompletionTime(int completionTime) {
	this.completionTime = completionTime;
}

public int getCompletionTime() {
	return completionTime;
}

public void setTurnAroundTime(int turnAroundTime) {
	this.turnAroundTime = turnAroundTime;
}

public void setWaitingTime(int waitingTime) {
	this.waitingTime = waitingTime;
}

public void setResponseTime(int responseTime) {
	this.responseTime = responseTime;
}

public String getId() {
	return this.id;
}
public int getArrivalTime() {
	return this.arrivalTime;
}

public int getRunTime() {
	return this.runTime;
}

public int getPriority() {
	return this.priority;
}

public int getTurnAroundTime() {
	return this.turnAroundTime;
}

public int getWaitingTime() {
	return this.waitingTime;
}

public int getResponseTime() {
	return this.responseTime;
}
	
public String getInfo() {
	return "Id: " + this.getId() + " Arrival Time: "+ this.getArrivalTime() + " Run Time: "+ this.getRunTime() + " Priority: " + this.getPriority();
}

public String getSimulation() {
	return "Response Time: " + this.getResponseTime() + " Turn Around Time: "+ this.getTurnAroundTime() + " Waiting Time: " + this.getWaitingTime();
}


}


