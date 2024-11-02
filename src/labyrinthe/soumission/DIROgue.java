package labyrinthe.soumission;
import java.util.Scanner;
import labyrinthe.code_squelette.*;
import labyrinthe.rencontres.*;

import java.util.Random;

public class DIROgue {

	public static void main(String[] args) {


		// Créer MonAventure et MonLabyrinthe
		Piece[] piecesLab = new Piece[50];
		Scanner scanObj = new Scanner(System.in);
		String input = "";
		int id;
		int idDeux;
		int esp1;
		int esp2;
		boolean cond1;
		boolean cond2;
		boolean rencontreValide;


		System.out.println("Vous pouvez rajouter des pièces (piece id rencontre)");
		input = scanObj.nextLine();


		while (!input.equals("CORRIDORS")) {

			esp1 = input.indexOf(" ");
			esp2 = input.lastIndexOf(" ");
			id = Integer.valueOf(input.substring(esp1+1,esp2));
			input = input.substring(esp2+1, input.length()).toUpperCase();

			cond1 = (input.equals(RencontreType.BOSS.name()) || input.equals(RencontreType.RIEN.name()));
			cond2 = (input.equals(RencontreType.MONSTRE.name()) || input.equals(RencontreType.TRESOR.name()));
			rencontreValide = cond1 || cond2;

			if (id > 0 && id < 50 && rencontreValide) {

				piecesLab[id] = new Piece(id, RencontreType.valueOf(input));

			}
			else {
				System.out.println("Le ID ou type de rencontre est invalide.");
			}
			input = scanObj.nextLine();
			System.out.println(input);
		}

		MonLabyrinthe labyrinthe = new MonLabyrinthe(piecesLab);

		if (labyrinthe.listesAdj[0][0]==-1){
			labyrinthe.ajouteEntree(Exterieur.getExterieur(),labyrinthe.pieces[1]);

		}

		System.out.println("Vous pouvez rajouter des corridors (corridor id1 id2)");
		input = scanObj.nextLine();
		while (!input.equals("FIN")){

			esp1 = input.indexOf(" ");
			esp2 = input.lastIndexOf(" ");
			id = Integer.valueOf(input.substring(esp1+1,esp2));
			idDeux = Integer.valueOf(input.substring(esp2+1, input.length()));


			if (id >=0 && idDeux >=0 && id <50 && idDeux<50 && id!=idDeux){
 				System.out.println("waoh"+id+"woohoo"+idDeux);
				 labyrinthe.ajouteCorridor(labyrinthe.pieces[id],labyrinthe.pieces[idDeux]);
			}
			else{
				System.out.println("Un des deux IDs est invalide. Ils doivent être différents puis entre 0 et 50.");
			}
			input = scanObj.nextLine();
		}


		MonAventure aventure = new MonAventure(labyrinthe);
		System.out.println(genererRapport(aventure));
		System.out.println(genererScenario(aventure));

	}



	/** Une méthode qui génère un rapport sur l'aventure
	 * incluant quelques pieces, si c'est pacifique, contient un boss
	 * quantité de trésor, et le chemin au boss
	 * @param a l'aventure qui est décrite par le rapport
	 * @return le rapport sous forme de string */
	public static String genererRapport(Aventure a) {
		String rapport = "Rapport:\n";  // string contenant le rapport

		Labyrinthe carte = a.getLabyrinthe();
		Piece[] pieces = carte.getPieces();
		int npieces = carte.nombreDePieces();

		rapport += "Donjon avec " + npieces + " pieces:\n";

		for (int i=0; i<50 || i<4 ; i++) {
			if (pieces[i] != null) {
				rapport += pieces[i] + ":";
				Piece[] connections = carte.getPiecesConnectees(pieces[i]);
				for (int j = 0; j < connections.length; j++) {
					rapport += connections[j];
				}
				rapport += "\n";
			}
		}

		// Affiche si c'est pacifique ou non
		if (a.estPacifique())
			rapport += "Pacifique.\n";
		else
			rapport += "Non pacifique.\n";

		// affiche si le labyrinthe contient un boss ou non
		if (a.contientBoss())
			rapport += "Contient un boss.\n";
		else rapport += "Ne contient pas un boss.\n";

		// affiche la quantité de trésors dans le labyrinthe
		rapport += "Contient " + a.getTresorTotal() + " tresors.\n";

		// Ajouter le chemin jusqu'au boss :
		Piece[] cheminBoss = a.cheminJusquAuBoss();
		if (cheminBoss.length > 0) {
			rapport += "Chemin jusqu'au boss :\n";
			for (int i=0; i<cheminBoss.length ; i++) {
				rapport += cheminBoss[i] + "\n";
			}
		}

		return rapport;
	}

	/** Génère le scénario en affichant une phrase appropriée pour chaque rencontre
	 * sur le chemin vers le boss.
	 * @author William Perktold.
	 *
	 * @param a l'objet de type Aventure qu'on traverse.
	 * @return Un String scenario qui contient les phrases associées à chaque rencontre.
	 * */
	public static String genererScenario(Aventure a) {
		Piece[] chemin = a.cheminJusquAuBoss();
		String scenario = "Scenario:\n";
		System.out.println(chemin.length);

		for (int i = 0; i < chemin.length; i++) {
			Rencontre x = null;

			if (chemin[i].getRencontreType() == RencontreType.BOSS)
				x = new Boss();
			else if (chemin[i].getRencontreType() == RencontreType.RIEN)
				x = new Rien();
			else if (chemin[i].getRencontreType() == RencontreType.TRESOR) {
				int r = new Random().nextInt(3);
				switch (r) {
					case 0:
						x = new SacDeButin(); //… choix 1
						break;
					case 1:
						x = new Potion(); //… choix 2
						break;
					case 2:
						x = new ArtefactMagique(); //… choix 3
				}
			}
			else if (chemin[i].getRencontreType() == RencontreType.MONSTRE) {
				int r = new Random().nextInt(3);
				switch (r) {
					case 0:
						x = new Gobelin(); //… choix 1
						break;
					case 1:
						x = new Orque(); //… choix 2
						break;
					case 2:
						x = new Gargouille(); //… choix 3
				}
			}
			else scenario += "ERREUR";

			scenario += x.rencontrer() + "\n";
		}
		return scenario;
	}
}
