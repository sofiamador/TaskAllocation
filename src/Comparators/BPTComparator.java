package Comparators;
import TaskAllocation.*;

import java.util.Comparator;



public class BPTComparator implements Comparator<Assignment>{
	public static final BPTComparator com=new BPTComparator();
	@Override
	public int compare(Assignment arg0, Assignment arg1) {
		if(arg0.getTotalUtility()>arg1.getTotalUtility()){
		return 1;
		}
		else if(arg0.getUtility()<arg1.getUtility()){
			return -1;
		}
		return 0;
	}

}
