package Solver;

import java.util.Collections;
import java.util.Vector;

import Comparators.*;
import SW.*;
import TaskAllocation.*;

public class SASolver extends Solver {
	
	private double temperature;
	private double cooler = 0.999;
	private static Double EPSILON = new Double(1E-11);
	
	private Vector<Assignment>[] tempSolution ;
	private Vector<Assignment>[] newSolution;
	private double utility ;
	
	
	public SASolver(Utility[][] input, double coop, Agent[] agnets,
			Task[] tasks, double temperature) {
		super(input, coop, agnets, tasks);
		this.temperature = temperature;
		
		tempSolution = null;
		newSolution = new Vector[input[0].length];
		utility = -1000000;
	}


	private Vector<Assignment>[] runSimulatedAnnealingAllocation(double d) {

		// algorithm
		tempSolution = createSolution();
		utility = Calculation
				.calculateUtility1(checkAllocation(cloneSol(tempSolution)));
		double tempUtility = 0;
		while (temperature > EPSILON) {
			int j = (int) (Math.random() * input[0].length);
			int j2 = (int) (Math.random() * coop);
			newSolution = search(j, j2, utility, tempSolution);
			tempUtility = Calculation
					.calculateUtility1(checkAllocation(cloneSol(newSolution)));
			if (tempUtility != utility) {
				utility = tempUtility;
				tempSolution = cloneSol(newSolution);
			}
		}
		return checkAllocation(tempSolution);
	}

	/// Calculate the probability of the next state
	private double getProbability(double deltaEnergy) {
		double x = Math.exp(-deltaEnergy / temperature);
		temperature = temperature * cooler;
		return x;

	}
	
	// search better assignment for unit i
	private Vector<Assignment>[] search(int task, int part, double utility,
			Vector<Assignment>[] tempSolution) {

		Vector<Assignment>[] newSolution = cloneSol(tempSolution);
		double tempUtility = -1000000;
		while (true) {
			int i = (int) (Math.random() * input.length);
			if (i != tempSolution[task].get(part).getAgent().getId()) {
				newSolution[task].get(part).setAgent(agnets[i]);
				newSolution[task].get(part).setUtility(input[i][task]);
				tempUtility = Calculation
						.calculateUtility1(checkAllocation(cloneSol(newSolution)));

				if (tempUtility > utility
						|| getProbability(utility - tempUtility) > Math
								.random()) {
					tempSolution[task].get(part).setAgent(agnets[i]);
					tempSolution[task].get(part).setUtility(input[i][task]);
					utility = tempUtility;
				}
				break;
			}
		}
		return tempSolution;
	}
	
	/// create copy of the current solution
	private Vector<Assignment>[] cloneSol(Vector<Assignment>[] tempSolution) {
		Vector<Assignment>[] solution = new Vector[tempSolution.length];
		for (int i = 0; i < solution.length; i++) {
			solution[i] = new Vector<Assignment>();
			for (Assignment e : tempSolution[i]) {
				solution[i].add(e.clone());
			}
		}
		return solution;
	}

	// check allocation and merge task for the same agent
	private Vector<Assignment>[] checkAllocation(
			Vector<Assignment>[] finalAllocation) {
		for (int i = 0; i < finalAllocation.length; i++) {
			Collections.sort(finalAllocation[i], UtilityComparator.com);

			int coop1 = (int) coop - 1;
			for (int j = 0; j < coop1; j++) {
				if (finalAllocation[i].get(j).getAgent() == finalAllocation[i]
						.get(j + 1).getAgent()) {
					finalAllocation[i].get(j).setRatio(
							finalAllocation[i].get(j).getRatio()
									+ finalAllocation[i].get(j + 1).getRatio());
					finalAllocation[i].remove(j + 1);
					j--;
					coop1--;
				}

			}
		}
		return divideAllocation(finalAllocation, input.length);
	}

	// create random solution
	private Vector<Assignment>[] createSolution() {
		Vector<Assignment>[] solution = new Vector[input[0].length];
		
		for (int j = 0; j < solution.length; j++) {
			solution[j] = new Vector<Assignment>();
			for (int k = 0; k < coop; k++) {
				int t = (int) (Math.random() * (double) (input.length));
				solution[j].add(new Assignment(agnets[j], tasks[t], input[t][j],1.0 / coop));
			}
		}
		return solution;

	}

}
