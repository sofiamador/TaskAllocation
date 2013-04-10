package Solver;

import java.util.Collections;
import java.util.Vector;

import policeAllocation.Allocating;

public class GreedySolver extends Solver {
	protected Double [][] input;
	protected Vector<Assignment>[] output;

	public Vector<Assignment>[] getOutput() {
		return output;
	}
	public GreedySolver(Double[][] input) {
		super();
		this.input = input;
		output = new Vector[input.length];
		for (int i = 0; i < input.length; i++) {
			output[i]=new Vector<Assignment>();
			
		}
	}
	// finds best greedy allocation
	// 
	public void solve(){
		
		for (int j = 0; j < input[0].length; j++) {
			int maxAgent=-1;
			double max=-100000;
			for (int i = input.length-1; i >0; i++) {
				if(max<input[i][j]){
					maxAgent=i;
					max=input[i][j];
				}
			}
			output[maxAgent].add(new Assignment(j, maxAgent, 1, max));			
		}
		sortOutput();
	}
	protected void sortOutput() {
		for (int i = 0; i < output.length; i++) {
			Collections.sort(output[i],UtilityComparator.com);
			Collections.reverse(output[i]);
		}
		
	}
	
	// Greedy allocation
		public Vector<Assignment>[] runGreedyAllocation() {
			GreedySolver gr = new SmartGreedySolver(input);
			gr.solve();
			return gr.getOutput();
		}
	
	
	
}
