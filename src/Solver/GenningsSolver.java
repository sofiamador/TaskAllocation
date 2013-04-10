package Solver;

import java.util.Vector;

import TaskAllocation.Assignment;

public class GenningsSolver extends Solver {
	public Vector<Assignment>[] runGenningsAllocation() throws GRBException {
		 JenningsHeuristicAbstract JN=new  JenningsHeuristicAbstract(input);		
		return JN.algorithm();
	}

}
