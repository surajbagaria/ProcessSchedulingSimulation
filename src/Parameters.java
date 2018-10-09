public class Parameters {
	
	int avgTurnAroundTime;
	int avgWaitingTime;
	int avgResponseTime;
	
	public void setAvgTurnAroundTime (int avgTurnAroundTime) {
		
		this.avgTurnAroundTime = avgTurnAroundTime;
	}
	
	public void setAvgWaitingTime (int avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}
	
	public void setAvgResponseTime (int avgResponseTime) {
		this.avgResponseTime = avgResponseTime;
	}
	
	public int getAvgTurnAroundTime () {
		return this.avgTurnAroundTime;
	}
	
	public int getAvgWaitingTime () {
		return this.avgWaitingTime;
	}
	
	public int getAvgResponseTime () {
		return this.avgResponseTime;
	}
	
	public String getParameterInfo() {
		return "Average Turn Around Time: " + this.avgTurnAroundTime + " Average Waiting Time: " + this.avgWaitingTime + " Average Response Time: " + this.avgResponseTime;
	}
}
