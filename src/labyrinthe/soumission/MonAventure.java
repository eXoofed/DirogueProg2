package labyrinthe.soumission;
import labyrinthe.code_squelette.Aventure;
import labyrinthe.code_squelette.Labyrinthe;
import labyrinthe.code_squelette.Piece;
import labyrinthe.code_squelette.RencontreType;

public class MonAventure extends Aventure {

    // Constructeur
    public MonAventure(Labyrinthe c) {
        super(c);
    }

    @Override
    public boolean estPacifique() {

        Piece[] pieces = carte.getPieces(); // un tableau des pieces
        int i = 0; // nombre d'itérations
        boolean pacifique = true; // si le labyrinthe est pacifique pour le moment

        while (pacifique && i < pieces.length) {
            // verifier si les rencontres sont non pacifiques
            boolean estMonstre = pieces[i].getRencontreType() == RencontreType.MONSTRE;
            boolean estBoss = pieces[i].getRencontreType() == RencontreType.BOSS;

            if (estMonstre || estBoss)
                pacifique = false; // le labyrinthe n'est pas pacifique

            i++;
        }
        return pacifique;
    }

    @Override
    public boolean contientDuTresor() {

        Piece[] pieces = carte.getPieces();
        int i = 0; // itération
        boolean tresor = false;  // s'il y a trésor

        while (!tresor && i < pieces.length) {
            if (pieces[i].getRencontreType() == RencontreType.TRESOR)
                tresor = true;
            i++;
        }
        return tresor;
    }

    @Override
    public int getTresorTotal() {

        Piece[] pieces = carte.getPieces();
        int i = 0;
        int ntresors = 0; // nombre de rencontres type trésor

        while (i < pieces.length) {
            if (pieces[i].getRencontreType() == RencontreType.TRESOR)
                ntresors ++; // augmente le nombre de tresor
            i++;
        }
        return ntresors;
    }

    @Override
    public boolean contientBoss() {
        Piece[] pieces = carte.getPieces();
        int i = 0;
        boolean boss = false;

        while (i < pieces.length && !boss) {
            if (pieces[i].getRencontreType() == RencontreType.BOSS)
                boss = true;
            i++;
        }
        return boss;
    }

    @Override
    public Piece[] cheminJusquAuBoss() {
        Piece[] pieces = carte.getPieces();
        int i = 0;
        boolean discontinue = false; // vérifie si discontinuité (pas de chemin entre i et i+1 ou i+1 n'existe pas)
        int pieceBoss = -1; // indice de la piece avec boss

        while (i < pieces.length && (pieceBoss ==-1) && !discontinue) {
            boolean aProchain = pieces[i].getID() + 1 == pieces[i+1].getID();  // prochain existe
            boolean aCorridor = false;  // prochain est connecté ou non
            if (aProchain) // vérifie si le prochain existe avant de vérifier connection
                aCorridor = carte.existeCorridorEntre(pieces[i], pieces[i+1]);

            if (pieces[i].getRencontreType() == RencontreType.BOSS)
                pieceBoss = i;
            else if (!aProchain || !aCorridor)
                discontinue = true;

            i++;
        }

        // créer le tableau qui sera retourné
        Piece[] chaine;
        if (discontinue)
            chaine = new Piece[0];
        else {
            chaine = new Piece[pieceBoss + 1];
            for (int j = 0; j < pieceBoss; j++) {
                chaine[i] = pieces[i];
            }
        }

        return chaine;
    }


}
