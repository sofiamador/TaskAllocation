package Jennings;

import TaskAllocation.*;;

public class JennigsTask implements Comparable<JennigsTask>{
	

	private Task task;
	private int id;
	private Agent bestAgent;
	private double distance;
	private double evluation;
	
	public JennigsTask(Task e, int id) {
		super();
		this.id=(id);
		this.task = e;
	}

	@Override
	public int compareTo(JennigsTask o) {
		if(evluation>o.evluation){
			return 1;
		}else if(evluation>o.evluation){
			return -1;
		}
		return 0;
	}

	
	//getters setters
	public Agent getBestUnit() {
		return bestAgent;
	}

	public void setBestUnit(Agent bestAgent) {
		this.bestAgent = bestAgent;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getEvluation() {
		return evluation;
	}

	public void setEvluation(double evluation) {
		this.evluation = evluation;
	}

	public Task getE() {
		return task;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof JennigsTask))
			return false;
		JennigsTask other = (JennigsTask) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	@Override
	public String toString() {
		return "JennigsTask [id=" + id + ", bestUnit=" + bestAgent
				+ ", evluation=" + evluation + "]";
	}
	

}
