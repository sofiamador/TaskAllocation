package Helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class WriteToFile {
	
	/// write to file fishers algorithm output
	public static void writeFisherOutpuToFile(Double[][] ds) {
		try {
			BufferedWriter out = openFile("output_allocation.csv");
			for (int i = 0; i < ds.length; i++) {
				for (int j = 0; j < ds[i].length; j++) {
					String o = "" + ds[i][j] + ",";
					out.write(o);
				}
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Couldn't write to file");
		}
	}
	
	/// prints all cumulative utilities by time to file
	public static void writeCumultiveUtilitiesToFile(Map<Double, Double> timesFis,
			Map<Double, Double> timesLP, Map<Double, Double> timesGreedy,
			Map<Double, Double> timesSA, TreeMap<Double, Double> timesGennigs, int run) {
		
		try {			
			BufferedWriter out = openFile("outputCUs.csv");
			for (Iterator<java.util.Map.Entry<Double, Double>> iterator = timesFis
					.entrySet().iterator(); iterator.hasNext();) {
				java.util.Map.Entry<Double, Double> t = iterator.next();
				String o = "Time," + t.getKey() + "," + t.getValue() / run
						+ "," + timesSA.get(t.getKey()) / run + ","
						+ timesLP.get(t.getKey()) / run + ","
						+ timesGreedy.get(t.getKey()) / run+ ","+ timesGennigs.get(t.getKey()) / run;
				out.write(o);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Couldn't write to file");
		}

	}

	//print to file cumulative utility by time
	public static void writeCumultiveUtilitiyToFile(Map<Double, Double> timesFis, int runs) {
		try {
			
			BufferedWriter out = openFile("outputCU.csv");
			for (Iterator<java.util.Map.Entry<Double, Double>> iterator = timesFis
					.entrySet().iterator(); iterator.hasNext();) {
				java.util.Map.Entry<Double, Double> t = iterator.next();
				String o = "Time," + t.getKey() + "," + t.getValue() / runs;
				out.write(o);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Couldn't write to file");
		}

	}
	/// print total utilities to file
	public static void writeUtilitiesToFile(double fisherSW, double LPSW,
			double greedySW, double simulatedSW, double gennigsSW) {
		BufferedWriter out = null;
		try {
			FileWriter s = new FileWriter("outputUtilities.csv", true);
			out = new BufferedWriter(s);
			String o = "Fisher sw," + fisherSW + ",simulated sw," + simulatedSW
					+ ",LP sw," + LPSW + ",greedy sw," + greedySW+ ",Genningd sw," + gennigsSW;
			out.write(o);
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.err.println("Couldn't write to file");
		}

	}

	public static BufferedWriter openFile(String fileName){
		BufferedWriter out = null;
		try {
			FileWriter s = new FileWriter(fileName, true);
			out = new BufferedWriter(s);
			out.newLine();
			
		}catch (Exception e) {
			System.err.println("Couldn't open the file");
		}
	return out;	
		
	}
}
