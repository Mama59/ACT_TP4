package travellingSalemansProblem;

import java.util.List;

import classesPb.Certificat;
import classesPb.NP;

public class TSP extends NP {
	public int nbVilles;
	public int[][] distances;
	public int longueurTournee;

	public TSP(int nb, int dist[][], int lg) {
		this.nbVilles = nb;
		this.distances = dist;
		this.longueurTournee = lg;
	}

	public CertificatTSP cert() {
		return new CertificatTSP(this);
		// Singleton ?
	}

	/*
	-On vérifie qu'il n'y a pas de doublons sinon faux   O(n)
	-on fais la somme des distances entre l'élément et le suivant + Distance entre le dernier et le premier O(n)
	-vrai si la somme des distances <= l O(1)
	*/
	// TODO
	public boolean estCorrect(Certificat c) {
		if (c.haveDoublons()) {
			return false;
		}
		int longueur = 0;
		List<Character> parcours = c.getList();
		List<Character> villesTriees = c.getValeursPossibles();
		for (int i = 1; i < parcours.size(); i++) {
			longueur += distances[villesTriees.indexOf(parcours.get(i - 1))][villesTriees.indexOf(parcours.get(i))];
		}
		// retour case depart
		longueur += distances[villesTriees.indexOf(parcours.get(parcours.size() - 1))][villesTriees.indexOf(parcours.get(0))];
		//System.out.println("Longueur tournee : " + longueur);
		return longueur <= this.longueurTournee;
	}
}
