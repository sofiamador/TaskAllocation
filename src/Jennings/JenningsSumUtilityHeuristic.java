package Jennings;

import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;

import policeAllocation.Allocating;
import policeAllocation.Location;
import policeAllocation.PatrolEvent;
import policeAllocation.PoliceUnit;

public class JenningsSumUtilityHeuristic extends JenningsHeuristic {

	protected int[][] constraints;
	protected double a;

	public Vector<Assignment>[] algorithm() {
		inactivatePoliceUnit();
		while (!tasks.isEmpty()) {
			findClosestUnit();
			findEvaluation();
			allocteBestTask(Collections.max(tasks));
			
		}
		return finalSol;

	}

	public JenningsSumUtilityHeuristic(Vector<PoliceUnit> policeUnits,
			Double[][] unary, int[][] constraints, double a) {
		super(policeUnits, unary);
		this.constraints = constraints;
		this.a = a;
		// TODO Auto-generated constructor stub
	}

	protected void findEvaluation() {
		
		for (JennigsTask i : tasks) {
			double utility = unary[i.getBestUnit().getUnitNum() - 1][i.getId()];
			for (JennigsTask j : tasks) {
				if (i.getId() != j.getId()) {
					double distanceTime = (UC.getDemoDistance(i.getE()
							.getLocation(), j.getE().getLocation())/40.0);
					if (j.getE() instanceof PatrolEvent) {
						utility = utility + j.getE().getPriority()
								- (a * distanceTime);
					} else {
						utility = utility
								+ constraints[0][j.getE().getPriority() - 1]
								- (a * distanceTime);
					}

				}
			}
			i.setEvluation(utility);

		}

	}


}
