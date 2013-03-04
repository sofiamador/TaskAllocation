package TaskAllocation;

public class LinearUtility extends Utility {

	private double linearUtility;
	@Override
	
	public LinearUtility(double distance)
	
	public double getUtility(double ratio) {
		return ratio*linearUtility;
	}

}
