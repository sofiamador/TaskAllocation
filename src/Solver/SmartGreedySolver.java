package Solver;

import java.util.Collections;
import java.util.Vector;


import policeAllocation.Allocating;

public class SmartGreedySolver extends GreedySolver {

	public SmartGreedySolver(Double[][] input) {
		super(input);
		// TODO Auto-generated constructor stub
	}
	// finds best greedy allocation
	// 
	public void solve(){
		
		int coopar=0;
		for (int j = 0; j < input[0].length; j++) {
			int maxAgent=-1;
			double max=-100000;
			for (int i = 0; i < input.length; i++) {
				if(max<input[i][j] && output[i].size()<=coopar/input.length){
					maxAgent=i;
					max=input[i][j];
				}
			}
			output[maxAgent].add(new Assignment(j, maxAgent, 1, max));	
			coopar++;
		}
		sortOutput();
	}	
}
