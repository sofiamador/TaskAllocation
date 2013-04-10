package SW;


import java.util.*;

import TaskAllocation.*;

public class AdditiveSW extends SW{
	

	public AdditiveSW(double dF, double timeUnit,int tMax) {
		super(dF, timeUnit, tMax);
	}
	
	
	
	public double camulativeUtility(Vector<Assignment>[] output) {
		setOutput(output);
		double utility = 0,timeBefore=0,time=timeUnit,power;
		divideTimeAfter();
		while (time < tMax) {
			for (int i = 0; i < output.length; i++) {
				if (!output[i].isEmpty()) {
					timeBefore=time-timeUnit;
					for (Iterator<Assignment>iterator = output[i].iterator(); iterator.hasNext();) {
						Assignment d=iterator.next();
						power=output[i].get(0).getEndTime()/timeUnit;
						if(d.getEndTime()>=time){
							power=time/timeUnit;// the power for discount factor 
							utility = utility + d.getbetweenUtility(timeBefore, time)*Math.pow(DF, power);
							if(d.getEndTime()==time){
								iterator.remove();
							}
							break;
						}else{
							power=output[i].get(0).getEndTime()/timeUnit;// the power for discount factor
							utility = utility +d.getbetweenUtility(timeBefore, d.getEndTime())*Math.pow(DF, power);
							timeBefore=d.getEndTime();
							iterator.remove();
						}
					}
				}
			}
			if (times.containsKey(time)) {
				times.put(time, times.get(time) + utility);
			} else {
				times.put(time, utility);
			}
			time=time+timeUnit;
		}

		return utility;
	}
	
	/// cumulative utility in a particular time
	public void cumulativeUtilityAtTime (double oldT, double newT) {
		double utility=0,power=0;
		power=newT/timeUnit;// the power for discount factor 
		for (int i = 0; i < output.length; i++) {
			Assignment d=output[i].get(0);
			utility=utility+d.getbetweenUtility(oldT, newT)*Math.pow(DF, power);
		}
		
		if(!times.containsKey(newT)){
			times.put( newT, utility);
		}{
			times.put( newT, times.get(newT)+utility);
		}
	}
	
	
	/// order cumulative utilities according to  particular time unit 
	public  TreeMap<Double, Double> cumulativeUtilityForTimeUnit() {
		double utility = 0;
		TreeMap<Double, Double> times = new TreeMap<Double, Double>();
		for (double from = 0, to = timeUnit; to < tMax; to = to + timeUnit, from = from +timeUnit) {
			double tempUtility = 0;
			SortedMap<Double, Double> temp = times.subMap(from, to);
			if (!temp.isEmpty()) {
				for (Double e : temp.values()) {
					tempUtility = tempUtility + e;
				}
			}
			utility = utility + tempUtility;
			times.put(to, utility);
		}
		return times;

	}



}
