package labyrinthe.soumission;

import labyrinthe.code_squelette.*;
import labyrinthe.rencontres.*;

import java.util.Random;

public class DIROgue {

	public static void main(String[] args) {

		// Cette invocation est ici juste pour vous demontrer quelques choses
		// TODO: supprimez l'appel a la methode expliquerQuelquesChoses()
		expliquerQuelquesChoses();

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
			scenario += x.rencontrer() + "/n";
		}
		return null;
	}

}
