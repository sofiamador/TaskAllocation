package Jennings;

import java.util.*;
import TaskAllocation.*;


public class JenningsHeuristic {
	protected Vector<Agent> agents;
	protected Double[][] unary;
	protected Vector<Assignment>[] finalSol;
	protected Vector<JennigsTask> tasks;
	protected URLConnectionReader UC;
	protected int numOfPatrolAreas;

	public JenningsHeuristic(Vector<Agent> agents,
			Double[][] doubles, int numOfPatrolAreas) {
		super();
		this.agents=agents;
		this.unary = doubles;
		createJennigsTasks();
		finalSol = new Vector[agents.size()];
		for (int i = 0; i < finalSol.length; i++) {
			finalSol[i] = new Vector<Assignment>();
		}
		UC=new URLConnectionReader();
		this.numOfPatrolAreas=numOfPatrolAreas;
	}

	// creates jennigs tasks for the algorithm
	private void createJennigsTasks() {
		tasks = new Vector<JennigsTask>();
		int i=0;
		for (Event e : PoliceUnits.get(0).getDomain()) {
			tasks.add(new JennigsTask(e,i));
			i++;
		}

	}

		// / Jennings algorithm
	public Vector<Assignment>[] algorithm() {
		inactivatePoliceUnit();
		while (!tasks.isEmpty()) {
			findClosestUnit();
			findEvaluation();	
			Collections.shuffle(tasks);
			allocteBestTask(tasks.elementAt(0));
		}
		return finalSol;

	}

		protected void inactivatePoliceUnit() {
			for (PoliceUnit p : PoliceUnits) {
				p.setActive(false);			
			}			
		}

		protected void allocteBestTask(JennigsTask jennigsTask) {
			tasks.remove(jennigsTask);
			jennigsTask.getBestUnit().setActive(true);
			jennigsTask.getBestUnit().setCurrentAllocation(jennigsTask.getE());
			int unit=jennigsTask.getBestUnit().getUnitNum()-1;
			double utility=unary[unit][jennigsTask.getId()];
			double duration=PoliceUnits.get(unit).getDomain().get(jennigsTask.getId()).getDuration();
			Assignment a=new Assignment(jennigsTask.getId(), unit, 1, utility,duration);
			finalSol[unit].add(a);
		
	}

		private void findEvaluation() {
		for (JennigsTask j : tasks) {
			j.setEvluation(PoliceUnits.get(0).getDomain().size());			
		}
		
	}
/// finds the units that can be as fast as possible at the event
		protected void findClosestUnit() {
			for (JennigsTask e : tasks) {
				findClosesUnit(e);				
			}			
		}

		private void findClosesUnit(JennigsTask e) {
			Vector<JennigsAgent> agents=new Vector<JennigsAgent>();
			for (PoliceUnit p : PoliceUnits) {	
				double time=UC.getDemoDistance(e.getE().getLocation(), p.getCurrentLocation())/40.0*3600.0;
				if(!p.isActive()){
					agents.add(new JennigsAgent(p.getUnitNum()-1,time));
				}else{
					agents.add(new JennigsAgent(p.getUnitNum()-1,time+p.getCurrentAllocation().getDuration()));
				}				
			}
			JennigsAgent j=Collections.min(agents);
			e.setBestUnit(PoliceUnits.get(j.getId()));
			e.setDistance(j.getMinTime());
			
		}

}
;