package PoliceTaskAllocation;

import TaskAllocation.Location;

public class MissionEvent extends Event{
	private int priority;
	private int unitsRequiered;

	public MissionEvent(Location location, double duration, double startTime,
			int id) {
		super(location, duration, startTime, id);
		// TODO Auto-generated constructor stub
	}

	public MissionEvent(Location location, double duration, double startTime,
			int id, int priority, int unitsRequiered) {
		super(location, duration, startTime, id);
		this.priority = priority;
		this.unitsRequiered = unitsRequiered;
	}

	public int getPriority() {
		return priority;
	}

	public int getUnitsRequiered() {
		return unitsRequiered;
	}


}
