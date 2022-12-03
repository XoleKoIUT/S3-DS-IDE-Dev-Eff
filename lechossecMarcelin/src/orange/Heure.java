package orange;

public class Heure implements Comparable<Heure> {
	private int he;
	private int nbE;

	public Heure(int he, int nbE) {
		this.he  = he;
		this.nbE = nbE;
	}
	
	@Override
	public int compareTo(Heure h) {
		return this.nbE - h.nbE;
	}

	@Override
	public String toString() {
		return  he + ", nbE=" + nbE + "]";
	}

	public int getHe() {
		return he;
	}

	public void setHe(int he) {
		this.he = he;
	}
	
	

}