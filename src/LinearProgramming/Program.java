package LinearProgramming;
import gurobi.*;

public class Program {
	
	private Double [][] input;
	private double[][] output;
	private GRBModel model; 
	private GRBVar[][] variables;
	
	public Program(Double[][] input) throws GRBException {
		super();
		this.input = input;
		variables=new GRBVar[input.length][input[0].length];
		model= new GRBModel(new GRBEnv());

		createVariables();
	}
	
	public void solve() throws GRBException{
		createVariables();
		model.update();
		createConstrains();
		model.update();
		model.set(GRB.IntAttr.ModelSense, GRB.MAXIMIZE);
		model.update();
        model.optimize();
        createOutput();	
	}

	// gets variables value from best solution
	private void createOutput() throws GRBException {
		output=model.get(GRB.DoubleAttr.X, variables);		
	}

	//create good's constraints 
	private void createConstrains() throws GRBException {
		GRBLinExpr expr = null;
        for (int j = 0; j < input[0].length; j++) {
            expr = new GRBLinExpr();
            for (int i = 0; i < input.length; i++) {
                expr.addTerm(1.0, variables[i][j]);
            }
            model.addConstr(expr, GRB.EQUAL, 1.0, "c" + j);
        }
		
	}

	//creates decision variables
	private void createVariables() throws GRBException {
		String []name=new String[input[0].length];
		double [] coeff=new double[input[0].length];
		double []ub=new double[input[0].length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {		
				name[j]="x"+i+j;
				coeff[j]=input[i][j];
				ub[j]=1;
			}
			variables[i]=model.addVars(null,ub, coeff, null,name);		
		}		
	}

	public Double[][] getOutput() {
		Double [][]out=new Double[input.length][input[0].length];		
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
				out[i][j]=output[i][j];
			}			
		}
		return out;
	}

	
		

}
