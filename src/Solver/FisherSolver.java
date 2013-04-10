package Solver;

import java.util.*;

import fisher.*;
import Comparators.*;
import TaskAllocation.*;

public class FisherSolver extends Solver {

	public FisherSolver(Utility[][] input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	// run Fisher allocation
	public Vector<Assignment>[] runFisherAllocation() {
		Double[][] fisherInput = createFisherInput(input);
		FisherPolinom f2 = new FisherPolinom(fisherInput);
		return TaskPrioritization(creatFisherSolution(f2.getOutput()));
	}

	// / convert utility to double for fisher input
	private Double[][] createFisherInput(Utility[][] input) {
		Double[][] fisherInput = new Double[input.length][input[0].length];
		for (int i = 0; i < fisherInput.length; i++) {
			for (int j = 0; j < fisherInput[0].length; j++) {
				fisherInput[i][j] = input[i][j].getUtility(1);
			}

		}
		return fisherInput;
	}

	// create allocation from fisher output
	private Vector<Assignment>[] creatFisherSolution(Double[][] output) {
		Vector<Assignment>[] solution = new Vector[agnets.length];
		for (int i = 0; i < solution.length; i++) {
			solution[i] = new Vector<Assignment>();

		}
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < output[0].length; j++) {
				if (output[i][j] != null) {
					solution[i].add(new Assignment(agnets[i], tasks[j],
							input[i][j], output[i][j]));
				}
			}
		}
		return solution;
	}
}
