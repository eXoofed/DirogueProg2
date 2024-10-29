package labyrinthe.soumission;
import java.util.Scanner;
import labyrinthe.code_squelette.*;
import labyrinthe.rencontres.*;

import java.util.Random;

public class DIROgue {

	public static void main(String[] args) {

		// Créer MonAventure et MonLabyrinthe
		Piece piecesLab[] = new Piece[0];
		Piece temp[];
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
				temp = piecesLab;

				piecesLab = new Piece[temp.length+1];
				for (int i = 0; i < temp.length; i++) {
					piecesLab[i] = temp[i];
				}

				piecesLab[temp.length] = new Piece(id, RencontreType.valueOf(input));

			}
			else {
				System.out.println("Le ID ou type de rencontre est invalide.");
			}
			input = scanObj.nextLine();
			System.out.println(input);
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
			}
			else{
				System.out.println("Un des deux IDs est invalide. Ils doivent être différents puis entre 0 et 50.");
			}
			input = scanObj.nextLine();
		}



		//Je ajoute des pièces

		//Attend pour CORRIDORS

		//Je ajoute des corridors

		//Attend pour FIN




	}

	/*
	 * Cette methode n'est pas necessaire pour le TP, c'est ici juste pour vous
	 * demontrer comment utiliser la classe Exterieur.
	 */
	private static void expliquerQuelquesChoses() {
		// ceci est la seule facon de creer une instance de la classe Exterieur!
		Exterieur lExterieur = Exterieur.getExterieur();
		// l'exterieur contient le type de rencontre RencontreType.RIEN
		System.out.println(lExterieur.getRencontreType() == RencontreType.RIEN);
	}

	public static String genererRapport(Aventure a) {
		// TODO: à remplir!
		return null;
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
		String scenario = "";
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
			scenario += x.rencontrer() + "/n";
		}
		return scenario;
	}
}
