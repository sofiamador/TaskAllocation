package TaskAllocation;

public class Event extends Task {
	protected double firstUnitTime;

	public Event(Location location, double duration, double startTime, int id) {
		super(location, duration, startTime, id);
	}
	
	public double getFirstUnitTime() {
		return firstUnitTime;
	}

	public void setFirstUnitTime(double firstUnitTime) {
		this.firstUnitTime = firstUnitTime;
	}

}
