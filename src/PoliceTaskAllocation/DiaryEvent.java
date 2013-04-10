package PoliceTaskAllocation;



public class DiaryEvent implements Comparable<DiaryEvent>{
	//diary time, time when event appears or ends
	private double time;
	
	private MissionEvent event;
	private boolean end;

	
	public DiaryEvent(MissionEvent ms) {
		time=ms.getStartTime();
		event=ms;
		setEnd(false);
	}
	public double getTime() {
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
		DiaryEvent o=new DiaryEvent(this.event);
		o.setEnd(end);
		o.setTime(time);
		return o;		
	}
	private void setTime(double time2) {
		time=time2;
		
	}
	
	
	
}
