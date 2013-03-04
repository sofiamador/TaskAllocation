package TaskAllocation;

import java.util.Vector;

abstract public class Task {
	protected Location location;
	protected double duration;
	protected double startTime;
	protected double EndTime;
	protected int id;
	protected Vector<Agent> agents;
	
	public Task(Location location, double duration, double startTime, int id) {
		super();
		agents=new Vector<Agent>();
		this.location = location;
		this.duration = duration;
		this.startTime = startTime;
		this.id = id;
	}

	public double getEndTime() {
		return EndTime;
	}

	public void setEndTime(double endTime) {
		EndTime = endTime;
	}

	public Location getLocation() {
		return location;
	}

	public double getDuration() {
		return duration;
	}

	public double getStartTime() {
		return startTime;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Task){
			return ((Task)arg0).id==this.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Task [location=" + location + ", duration=" + duration
				+ ", startTime=" + startTime + ", id=" + id + "]";
	}

	
	
	
	

}
