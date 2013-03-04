package TaskAllocation;

public abstract class Utility {
	
	public static final double ditanceFactor=9;
	//public static final double penaltyFactor=1000;
	protected double distance;
	
	public abstract double getUtility(double ratio);	
}
