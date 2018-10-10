public class Parameters {
	
	double avgTurnAroundTime;
	double avgWaitingTime;
	double avgResponseTime;
	double Throughput;
	
	public void setAvgTurnAroundTime (double avgTurnAroundTime) {
		
		this.avgTurnAroundTime = avgTurnAroundTime;
	}
	
	public void setAvgWaitingTime (double avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}
	
	public void setAvgResponseTime (double avgResponseTime) {
		this.avgResponseTime = avgResponseTime;
	}
	
	public double getAvgTurnAroundTime () {
		return this.avgTurnAroundTime;
	}
	
	public void setThroughput (double Throughput) {
		this.Throughput = Throughput;
	}
	
	public double getThroughput () {
		return this.Throughput;
	}
	
	public double getAvgWaitingTime () {
		return this.avgWaitingTime;
	}
	
	public double getAvgResponseTime () {
		return this.avgResponseTime;
	}
	
	public String getParameterInfo() {
		return "Average Turn Around Time: " + this.avgTurnAroundTime + " Average Waiting Time: " + this.avgWaitingTime + " Average Response Time: " + this.avgResponseTime  + " Throughput: " + this.Throughput;
	}
}
