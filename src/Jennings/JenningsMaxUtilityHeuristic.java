package Jennings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import policeAllocation.PatrolEvent;
import policeAllocation.PoliceUnit;

public class JenningsMaxUtilityHeuristic extends JenningsSumUtilityHeuristic{

	public JenningsMaxUtilityHeuristic(Vector<PoliceUnit> policeUnits,
			Double[][] unary, int[][] constraints, double a) {
		super(policeUnits, unary, constraints, a);
		// TODO Auto-generated constructor stub
	}
	
	protected void findEvaluation() {
		
		for (JennigsTask i : tasks) {
			TreeSet<Double> max=new TreeSet<Double>();
			double utility = unary[i.getBestUnit().getUnitNum() - 1][i.getId()];
			for (JennigsTask j : tasks) {
				if (i.getId() != j.getId()) {
					double distanceTime = (UC.getDemoDistance(i.getE()
							.getLocation(), j.getE().getLocation())/40.0);
					if (j.getE() instanceof PatrolEvent) {
						max.add( utility + j.getE().getPriority()
								- (a * distanceTime));
					} else {
						max.add( utility
								+ constraints[0][j.getE().getPriority() - 1]
								- (a * distanceTime));
					}

				}
			}if(tasks.size()==1){
				i.setEvluation(utility);
			}
			else{
				i.setEvluation(max.last());
			}

		}

	}

}
