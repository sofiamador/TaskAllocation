package Jennings;

import policeAllocation.Event;
import policeAllocation.PoliceUnit;

public class JennigsTaskAbstract implements Comparable<JennigsTaskAbstract>{
	
	@Override
	public String toString() {
		return "JennigsTaskAbstract [id=" + id + ", bestAgent=" + bestAgent
				+ ", evluation=" + evluation + "]";
	}

	private int id;
	private int bestAgent;
	private double evluation;

	public JennigsTaskAbstract( int id) {
		super();
		this.id=(id);

	}

	public int getBestAgent() {
		return bestAgent;
	}

	public void setBestAgent(int bestAgent) {
		this.bestAgent = bestAgent;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(JennigsTaskAbstract o) {
		if(evluation>o.evluation){
			return 1;
		}else if(evluation>o.evluation){
			return -1;
		}
		return 0;
	}


	public double getEvluation() {
		return evluation;
	}

	public void setEvluation(double evluation) {
		this.evluation = evluation;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof JennigsTaskAbstract))
			return false;
		JennigsTaskAbstract other = (JennigsTaskAbstract) obj;
		if (id != other.id)
			return false;
		return true;
	}		

}
