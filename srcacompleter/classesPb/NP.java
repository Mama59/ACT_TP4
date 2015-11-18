package classesPb;

import travellingSalemansProblem.CertificatTSP;
import travellingSalemansProblem.TSP;

public abstract class NP extends ExpTime{
    
//on doit pouvoir definir pour le pb la notion de  certificat 
//Attention: on doit pouvoir borner polynomialement la taille du certificat
//	par rapport a la taille du probleme
	abstract public Certificat cert(); 

//Algo de verification d'un certificat pour le probleme
//c'est l'algo A de la definition de NP par les certificats
	abstract public boolean estCorrect(Certificat cert);

//algo exponentiel de decision de la propriete basee sur l'enumeration
// NP est inclus dans EXPTime	
	public boolean aUneSolution() {
		Certificat c = new CertificatTSP((TSP) this);
		c.alea();
		c.resetFirst();
		while (!c.estDernier()) {
			if (this.estCorrect(c)) {
				System.out.println(c.getList());
				return true;
			}
			c.suivant();
		}
		return false;
    }

	
//algo non deterministe  polynomial:
//si il existe une solution AU MOINS UNE execution retourne Vrai
//si il n'en existe pas TOUTES les executions retournent faux!
	public boolean aUneSolutionNonDeterministe() {
     	 //ACOMPLETER
		Certificat c = new CertificatTSP((TSP) this);
		c.alea();
		System.out.println(c.getValeursPossibles());
		System.out.println(c.getList());
		return this.estCorrect(c);
    }
}
 