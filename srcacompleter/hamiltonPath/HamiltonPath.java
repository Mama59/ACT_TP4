package hamiltonPath;

import classesPb.Certificat;
import classesPb.NP;
import hamiltonCycle.HamiltonCycle;
import travellingSalemansProblem.CertificatTSP;
import travellingSalemansProblem.TSP;

public class HamiltonPath extends NP {
	public int nbSommets;
	public boolean[][] aretes;
	public HamiltonCycle hamiltonCycle;

	public HamiltonPath(int nb, boolean aretes[][]) {
		this.nbSommets = nb;
		this.aretes = aretes;
		redToHamiltonCycle();
	}

	public void redToHamiltonCycle() {
		boolean[][] aretesPlus = new boolean[nbSommets+1][nbSommets+1];;
		for(int i = 0; i < this.nbSommets + 1; i++) {
			for(int j = 0; j < this.nbSommets + 1; j++) {
				if(i == this.nbSommets || j == this.nbSommets) {
					aretesPlus[i][j] = (i != j);
				}
				else {
					aretesPlus[i][j] = this.aretes[i][j];
				}
			}
		}
		
		this.hamiltonCycle = new HamiltonCycle(this.nbSommets + 1, aretesPlus);
	}
	public boolean estCorrect(Certificat c) {
		return this.hamiltonCycle.estCorrect(c);
	}

	public TSP getTsp() {
		return this.hamiltonCycle.getTsp();
	}
	@Override
	public CertificatTSP cert() {
		// TODO Auto-generated method stub
		return this.hamiltonCycle.cert();
	}
}
