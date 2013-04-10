package Solver;

import java.io.*;
import java.util.*;



import Comparators.UtilityComparator;
import TaskAllocation.*;


public abstract class Solver {
	
	protected Utility[][] input;
	protected double coop;
	protected Agent[] agnets;
	protected Task[] tasks;
	
	

	public Solver(Utility[][] input, double coop, Agent[] agnets, Task[] tasks) {
		super();
		this.input = input;
		this.coop = coop;
		this.agnets = agnets;
		this.tasks = tasks;
	}



	public Solver(Utility[][] input) {
		this.input = input;
	}
	// prioritize tasks for each agent 
	protected Vector<Assignment>[] TaskPrioritization(Vector<Assignment>[] allocation){
		for (int i = 0; i < allocation.length; i++) {
			Collections.sort(allocation[i], UtilityComparator.com);
			Collections.reverse(allocation[i]);
		}
		
		return allocation;
	}
	
	/// convert division by tasks to division by agents
	protected Vector<Assignment>[] divideAllocation(
			Vector<Assignment>[] finalAllocation, int length) {

		Vector<Assignment>[] solution = new Vector[length];
		for (int i = 0; i < solution.length; i++) {
			solution[i] = new Vector<Assignment>();

		}
		for (int i = 0; i < finalAllocation.length; i++) {
			
			for (Assignment a : finalAllocation[i]) {
				
				solution[a.getAgent().getId()].add(a);
			}
		}
		TaskPrioritization(solution);
		return solution;
	}

}
