package hamiltonCycle;

import classesPb.Certificat;
import classesPb.NP;
import travellingSalemansProblem.CertificatTSP;
import travellingSalemansProblem.TSP;

public class HamiltonCycle extends NP {
	public int nbSommets;
	public boolean[][] aretes;
	public TSP tsp;
	private int[][] dimensions = null;

	public HamiltonCycle(int nb, boolean aretes[][]) {
		this.nbSommets = nb;
		this.aretes = aretes;
		redToTsp();
	}

	public void redToTsp() {
		dimensions = new int[nbSommets][nbSommets];
		for(int i = 0; i < this.nbSommets; i++) {
			for(int j = 0; j < this.nbSommets; j++) {
				if(this.aretes[i][j]) {
					dimensions[i][j] = 1;
				}
				else {
					dimensions[i][j] = this.nbSommets + 1 ;
				}	
			}
		}
		this.tsp = new TSP(this.nbSommets, dimensions, this.nbSommets);
	}
	public boolean estCorrect(Certificat c) {
		return this.tsp.estCorrect(c);
	}

	@Override
	public CertificatTSP cert() {
		// TODO Auto-generated method stub
		return this.tsp.cert();
	}

	public TSP getTsp() {
		return this.tsp;
	}
}
