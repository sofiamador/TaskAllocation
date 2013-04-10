package TaskAllocation;

public class LinearUtility extends Utility {

	private double linearUtility;
	
	public LinearUtility(double distance,double priotiyUtility){
		linearUtility=priotiyUtility-distance*Utility.ditanceFactor;
		
	}
	
	public double getUtility(double ratio) {
		return ratio*linearUtility;
	}

}
