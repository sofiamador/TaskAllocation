package Jennings;

public class JennigsAgent implements Comparable<JennigsAgent>{
	@Override
	public String toString() {
		return "JennigsAgent [id=" + id + ", minTime=" + minTime + "]";
	}

	private int id;
	private double minTime;
	
	@Override
	public int compareTo(JennigsAgent o) {
		if(minTime>o.minTime){
			return 1;
		}else if(minTime<o.minTime){
			return -1;
		}
		return 0;
	}

	public int getId() {
		return id;
	}

	public JennigsAgent(int id, double minTime) {
		super();
		this.id = id;
		this.minTime = minTime;
	}

	public double getMinTime() {
		return minTime;
	}

	public void setMinTime(double minTime) {
		this.minTime = minTime;
	}

	
	
}