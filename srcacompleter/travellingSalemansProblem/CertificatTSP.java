package travellingSalemansProblem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import classesPb.*;

public class CertificatTSP implements Certificat{
// A completer
	private List<Character> liste;
	private List<Character> lstSaisie; // originale
	private List<Character> valeursPossibles;
	private TSP tsp;
	
	public CertificatTSP(TSP tsp){
		this.tsp = tsp;
		this.liste = new ArrayList<Character>(tsp.nbVilles);
		this.lstSaisie = new ArrayList<Character>(tsp.nbVilles);
		
		this.valeursPossibles = new ArrayList<Character>(); 
		for (int i = 0; i < this.tsp.nbVilles ; i++) {
			this.valeursPossibles.add((char) ('A' + i));
		}
	}
	
	@Override
	public void saisie() {
		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine();
		while (saisie.length() != this.tsp.nbVilles) {
			System.out.println("Vous devez entrer exactement " + this.tsp.nbVilles + " caractères");
			saisie = sc.nextLine();
		}
		for (int i = 0; i < saisie.length(); i++) {
			this.liste.add(saisie.charAt(i));
			this.lstSaisie.add(saisie.charAt(i));
		}
		this.valeursPossibles = new ArrayList<Character>();
		this.liste.forEach(f -> {
			if (!this.valeursPossibles.contains(f)) {
				this.valeursPossibles.add(f);
			}
		});
		this.valeursPossibles.sort(null);
	}
	
	@Override
	public void display() {
		System.out.println(liste.toString());
	}
	
	@Override
	public void alea() {
		System.out.println("Certificat aleatoire");
		List<Character> newCertif = new ArrayList<Character>();
		for (int i = 0; i < this.tsp.nbVilles ; i++) {
			newCertif.add(this.valeursPossibles.get((int) (Math.random() * this.valeursPossibles.size())));
		}
		this.liste = newCertif;		
	}
	@Override
	public void reset() {
		System.out.println("Certificat reset");
		for (int i = 0; i < this.liste.size(); i++) {
			this.liste.set(i, this.lstSaisie.get(i));
		}
	}
	@Override
	public boolean estDernier() {
		for (int i = 0; i < this.liste.size(); i++) {
			if (this.liste.get(i) != this.valeursPossibles.get(this.valeursPossibles.size() - 1)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public void suivant() {
		System.out.println("Certificat suivant");
		if (this.estDernier()) {
			return;
		}
		
		boolean exit = false;
		// on parcourt la liste en commençant par le dernier caract
		for (int i = this.liste.size() - 1; i >= 0; i--) {
			if (exit) {
				return;
			}
			// si pas le caract max, on ncrémente le caract
			if (this.liste.get(i) != this.valeursPossibles.get(this.valeursPossibles.size() - 1)) {
				this.liste.set(i, this.valeursPossibles.get(this.valeursPossibles.indexOf(this.liste.get(i)) + 1));
				exit = true;
			}
			// sinon, c'ets le dernier caract possible, on insère le premier et passe à la ville precédente
			else {
				this.liste.set(i, this.valeursPossibles.get(0));
			}
		}	
	}

	@Override
	public boolean haveDoublons() {
		List<Character> lst = new ArrayList<Character>();
		for (int i = 0; i < this.liste.size(); i++) {
			if (lst.contains(this.liste.get(i))) {
				return true;
			}
			lst.add(this.liste.get(i));
		}
		return false;
	}

	public List<Character> getList() {
		return this.liste;
	}
	
	public List<Character> getValeursPossibles() {
		return this.valeursPossibles;
	}
	
	@Override
	public void resetFirst() {
		System.out.println("Certificat reset to first possible value");
		List<Character> newCertif = new ArrayList<Character>();
		for (int i = 0; i < this.liste.size(); i++) {
			newCertif.add(this.valeursPossibles.get(i));
		}
		System.out.println("Certificat aleatoire");
		this.liste = newCertif;	
	}
}
