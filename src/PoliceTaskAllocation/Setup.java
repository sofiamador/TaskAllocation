package PoliceTaskAllocation;

import Helpers.StdRandom;
import TaskAllocation.Location;

import java.util.TreeSet;

public class Setup {
	
	public static TreeSet<DiaryEvent> createDiary(int numOfMissions, double x, double y,double time, double duration){
		TreeSet<DiaryEvent> diary= new TreeSet<DiaryEvent>();
		for (int i = 0; i < numOfMissions; i++) {
			MissionEvent m=createMission(x,y,time,duration,i);
			diary.add(new DiaryEvent(m));
		}
		return diary;
	}

	private static MissionEvent createMission(double x, double y, double time, double duration, int id) {
		double x1=StdRandom.random()*x;
		double y1=StdRandom.random()*y;
		double duration1 =  StdRandom.exp(1/3500.0);
		return new MissionEvent(new Location(x1, y1), duration1, time, id);
		
	}

}
