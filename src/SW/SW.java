package SW;

import java.util.*;

import TaskAllocation.*;

public abstract class SW {

	protected double DF;
	protected double timeUnit;
	protected Vector<Assignment>[] output;
	protected TreeMap<Double, Double> times;
	protected int tMax;

	public SW(double dF, double timeUnit, int tmax) {
		super();
		DF = dF;
		this.timeUnit = timeUnit;
		times = new TreeMap<Double, Double>();
		tMax = tmax;
	}

	public TreeMap<Double, Double> getTimes() {
		return times;
	}

	public void setOutput(Vector<Assignment>[] output) {
		this.output = output;
	}

	public abstract double camulativeUtility(Vector<Assignment>[] output);

	// divides the utility with discount factor
	protected void divideTimeAfter() {
		for (int i = 0; i < output.length; i++) {
			if (output[i].isEmpty()) {
				continue;
			}
			output[i].get(0).setEndTime(0);
			for (int j = 1; j < output[i].size(); j++) {
				output[i].get(j).setEndTime(output[i].get(j - 1).getEndTime());
			}
		}
	}

}
