package TaskAllocation;

public class Allocating implements Comparable<Allocating>{

	private double ratio;
	private Agent agent;
	private Task task;
	private Utility utility;
	
	public Allocating(double ratio, Agent agent, Task task, Utility utility) {
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

	public Utility getUtility() {
		return utility;
	}


	@Override
	public int compareTo(Allocating a) {
		if(utility.getUtility(ratio)>a.utility.getUtility(ratio))
			return 1;
		else if(utility.getUtility(ratio)<a.utility.getUtility(ratio))
			return -1;
		return 0;
	}
	
	
	
	
	
}
