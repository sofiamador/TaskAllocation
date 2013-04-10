package TaskAllocation;


public class Assignment{

	private double ratio;
	private Agent agent;// agent for the task
	private Task task;
	private Utility utility;// the utility function for agent and task for current allocation
	private double endTime; // estimated end time
	private double arrivalTime;
	
	public Assignment( Agent agent, Task task, Utility utility,double ratio) {
		super();
		this.ratio = ratio;
		this.agent = agent;
		this.task = task;
		this.utility = utility;
	}
	@Override
	public String toString() {
		return "Allocating [ratio=" + ratio + ", agent=" + agent.id + ", task="
				+ task.id + ", utility=" + utility + "]";
	}
	public double getRatio() {
		return ratio;
	}

	public Agent getAgent() {
		return agent;
	}

	public Task getTask() {
		return task;
	}

	public double getUtility() {
		return utility.getUtility(ratio);
	}
	
	public double getbetweenUtility(double from,double to){
		
		if(arrivalTime<=from){
			 return (to-from)/task.getDuration()*getUtility();
		}else{
			return(to-arrivalTime)/task.getDuration()*getUtility();
		}
	}
	
	public double getTotalUtility(){
		return utility.getUtility(1);
	}

	public double getEndTime() {
		return endTime;
	}
	public void setUtility(Utility utility) {
		this.utility = utility;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public Assignment clone(){
		return new Assignment(agent, task, utility, ratio);
	}
	public void setEndTime(double time) {
		endTime=arrivalTime+task.getDuration()*ratio+time;
	}
	public double getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
