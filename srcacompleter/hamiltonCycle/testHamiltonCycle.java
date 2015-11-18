package hamiltonCycle;
import java.io.FileReader;
import java.util.Scanner;

import travellingSalemansProblem.CertificatTSP;
import travellingSalemansProblem.TSP;


public class testHamiltonCycle{  

public static void main(String[] arg) throws Exception {
 	//saisie du probleme
	if (arg.length < 2)
	    System.out.println("java testHamiltonCycle  mode file.ham lg");
	else {	
    //le probleme dans un fichier de donnees
    Scanner donnee = new Scanner (new FileReader(arg[1]));
    for (int i=0; i<3; i++) donnee.nextLine();
    donnee.next();
    int nbv= donnee.nextInt();	 
    for (int i=0; i<4; i++) donnee.nextLine();
    boolean[][] D=new boolean[nbv][nbv];
    for (int i=0; i<nbv; i++){
       for (int j=0; j<nbv; j++) {D[i][j]= donnee.nextBoolean();System.out.print(D[i][j]+" ");}
       System.out.println();}
    
    HamiltonCycle hamiltonCycle =new HamiltonCycle(nbv,D);
    TSP pb = hamiltonCycle.tsp;
    System.out.println(arg[0]);
    // les differents modes
    if (arg[0].equals("-verif")) { 
    	CertificatTSP c= pb.cert();
    	System.out.print("Proposez un certificat de " + nbv + " caracteres");
    	c.saisie();
    	c.display();

    	System.out.print("votre certificat est-il correct? ");
    	System.out.println(pb.estCorrect(c));
    	}
    else if (arg[0].equals("-nondet")) {
    	CertificatTSP c = pb.cert();
        System.out.println(pb.aUneSolutionNonDeterministe());
        }
    else if (arg[0].equals("-exhaust"))  { 
    	System.out.println("le probleme a-t-il une solution?: ");
    	System.out.println(pb.aUneSolution());
    	}
    else
    	System.out.println("erreur de mode");
	}
}

}
