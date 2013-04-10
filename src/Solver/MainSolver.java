package Solver;

import java.util.TreeMap;
import java.util.Vector;

import SW.Calculation;
import TaskAllocation.Assignment;

public class MainSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws GRBException {
		setupFile();
		for (int j = 10; j <= 20; j = j + 10) {
			// variables
			double sumfish = 0, sumSA = 0, sumLP = 0, sumGreedy = 0,sumJN=0;
			TreeMap<Double, Double> timesFisher = new TreeMap<Double, Double>();
			TreeMap<Double, Double> timesSA = new TreeMap<Double, Double>();
			TreeMap<Double, Double> timesLP = new TreeMap<Double, Double>();
			TreeMap<Double, Double> timesGreedy = new TreeMap<Double, Double>();
			TreeMap<Double, Double> timesGennigs = new TreeMap<Double, Double>();

			for (int i = 0; i < 50; i++) {
				Double[][] input = Calculation.inputUniform(j, 10);
				TaskAllocation al = new TaskAllocation(input, j * 1000, 4.0);
				// ////////////////////////////////////////////////////////////////////////
				Vector<Assignment>[] output = al.runFisherAllocation();
				sumfish = sumfish
						+ Calculation.camulativeUtility1(output,
								 timesFisher);
				// //////////////////////////////////////////////////////////////////////////
				Vector<Assignment>[] output1 = al.runLPAllocation();
				sumLP = sumLP
						+ Calculation.camulativeUtility1(output1, timesLP);
				// ///////////////////////////////////////////////////////////////////////////
				sumGreedy = sumGreedy
						+ Calculation.camulativeUtility1(
								al.runGreedyAllocation(), timesGreedy);
				// /////////////////////////////////////////////////////////////////////////////
				Vector<Assignment>[] output2 = al
						.runSimulatedAnnealingAllocation(j * 50);
				sumSA = sumSA
						+ Calculation.camulativeUtility1(output2, timesSA);
				// ///////////////////////////////////////////////////////////////////////////////
				Vector<Assignment>[] output3=al.runGenningsAllocation();
				sumJN= sumJN
						+ Calculation.camulativeUtility1(output3, timesGennigs);
				
			}
			 Calculation.writeToFile(sumfish / 50, sumLP / 50, sumGreedy / 50,
			 sumSA / 50, sumJN / 50);
			//Calculation.printTimeToFileFisher(timesFisher, 50);
			//Calculation.printTimeToFile1(timesSA, 50);
			Calculation.printTimeToFile(timesFisher, timesLP, timesGreedy,
					timesSA, timesGennigs,50);
			// Calculation.printTimeToFile(timesSW,10);
		}
	}

}
