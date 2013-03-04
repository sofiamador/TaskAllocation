package TaskAllocation;


public class DiaryEvent implements Comparable<DiaryEvent>{
	//diary time, time when event appears or ends
	private int time;
	
	private MissionEvent event;
	private boolean end;
	private int unitArrivaTime;
	private int unitsOnTheMission;
	private double timeLeft;

	
	public DiaryEvent(MissionEvent ms) {
		time=(int) ms.getArrivalTime();
		timeLeft=ms.getDuration();
		event=ms;
		setEnd(false);
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public MissionEvent getEvent() {
		return event;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public boolean isEnd() {
		return end;
	}
	
	public int getUnitArrivaTime() {
		return unitArrivaTime;
	}
	public void setUnitArrivaTime(int unitArrivaTime) {
		this.unitArrivaTime = unitArrivaTime;
		event.setArrivalTime(unitArrivaTime);
	}
	public int getUnitsOnTheMission() {
		return unitsOnTheMission;
	}
	public void setUnitsOnTheMission(int unitsOnTheMission) {
		this.unitsOnTheMission = unitsOnTheMission;
	}
	public double getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(double d) {
		this.timeLeft = d;
		event.setDuration(d);
	}

	@Override
	public int compareTo(DiaryEvent o) {
		// TODO Auto-generated method stub
		if(this.getTime() > o.getTime())return 1;
		if(this.getTime() < o.getTime())return -1;
		return 0;
	}
	public boolean equals(Object o){
	 if(o instanceof DiaryEvent) {
			return event.equals(((DiaryEvent) o).getEvent());
		}
	 return false;
	}
		
	public String toString() {
		 return ("Time: "+time+" End: "+end+" "+event.toString());
		//return ("Mission Code: "+event.getMissionCode()+", Priority: "+event.getPriority()+", Time: "+event.getArrivalTime()+", Duration: "+event.getDuration()+", Units Required: "+event.getUnitsRequired()+", Location: "+event.getLocation().toString()+"\n");
	}
	
	public Object clone() {
		DiaryEvent o=new DiaryEvent((MissionEvent) event.clone());
		o.setEnd(end);
		o.setTime(time);
		return o;		
	}
	
	
	
}
