package Solver;

import java.util.Vector;

import SW.Calculation;
import TaskAllocation.Assignment;

public class LPSolver extends Solver {
	public Vector<Assignment>[] runLPAllocation() throws GRBException {
		Program LP = new Program(input);
		LP.solve();
		Calculation.outpuToFile(LP.getOutput());
		return creatFisherSolution2(LP.getOutput());
	}

	

}
