package Jennings;

import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import policeAllocation.Allocating;
import policeAllocation.Event;
import policeAllocation.PatrolEvent;
import policeAllocation.PoliceUnit;
import policeAllocation.URLConnectionReader;

public class JenningsHeuristicAbstract {

	protected Double[][] utilities;
	protected Vector<Assignment>[] finalSol;
	protected Vector<JennigsTaskAbstract> tasks;
	protected double DF = 0.9;
	protected int numOfPatrolAreas;
	protected double timeUnit=0.25;

	public JenningsHeuristicAbstract(Double[][] utility) {
		super();
		this.utilities = utility;
		createJennigsTasks();
		finalSol = new Vector[utility.length];
		for (int i = 0; i < finalSol.length; i++) {
			finalSol[i] = new Vector<Assignment>();
		}
	}

	// creates jennigs tasks for the algorithm
	private void createJennigsTasks() {
		tasks = new Vector<JennigsTaskAbstract>();
		int i = 0;
		for (int j = 0; j < utilities[0].length; j++) {
			tasks.add(new JennigsTaskAbstract(j));
			i++;
		}

	}

	// / Jennings algorithm
	public Vector<Assignment>[] algorithm() {
		while (!tasks.isEmpty()) {
			findClosestUnit();
			findEvaluation();
			allocteBestTask(Collections.max(tasks));
		}
		return finalSol;

	}

	protected void allocteBestTask(JennigsTaskAbstract jennigsTask) {
		tasks.remove(jennigsTask);
		int unit = jennigsTask.getBestAgent();
		double utility = utilities[unit][jennigsTask.getId()];
		double duration = 1;
		Assignment a = new Assignment(jennigsTask.getId(), unit, 1, utility,
				duration);
		finalSol[unit].add(a);

	}

	protected void findEvaluation() {
			
			for (JennigsTaskAbstract i : tasks) {
				TreeSet<Double> max=new TreeSet<Double>();
				double utility =utilities[i.getBestAgent()][i.getId()]*Math.pow(DF, finalSol[i.getBestAgent()].size()/timeUnit);
				for (JennigsTaskAbstract j : tasks) {
					if (i.getId() != j.getId()) {

							max.add(utility
									+ utilities[i.getBestAgent()][j.getId()]*Math.pow(DF,(finalSol[i.getBestAgent()].size()+1)/timeUnit));

					}
				}if(tasks.size()==1){
					i.setEvluation(utility);
				}
				else{
					i.setEvluation(max.last());
				}

			}

		}

	// / finds the units that can be as fast as possible at the event
	protected void findClosestUnit() {
		for (JennigsTaskAbstract e : tasks) {
			findClosesUnit(e);
		}
	}

	private void findClosesUnit(JennigsTaskAbstract e) {
		Vector<JennigsAgent> agents = new Vector<JennigsAgent>();
		for (int i = 0; i < utilities.length; i++) {
			if (finalSol[i].isEmpty()) {
				agents.add(new JennigsAgent(i, utilities[i][e.getId()]));
			} else {
				agents.add(new JennigsAgent(i, utilities[i][e.getId()]
						* Math.pow(DF, finalSol[i].size() / timeUnit)));
			}
		}
		JennigsAgent j = Collections.max(agents);
		e.setBestAgent(j.getId());

	}

};
