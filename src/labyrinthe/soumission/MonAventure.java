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
        Piece[] pieces = carte.getPieces();
        int i = 0;
        boolean cond = pieces[i] != null;
        boolean pacifique = true;
        while (cond && pacifique) {
            boolean estRien = pieces[i].getRencontreType() == RencontreType.RIEN;
            boolean estTresor = pieces[i].getRencontreType() == RencontreType.TRESOR;
            if (estRien || estTresor)
                pacifique = false;
            i++;
            cond = pieces[i] != null;
        }
        return pacifique;
    }

    @Override
    public boolean contientDuTresor() {
        Piece[] pieces = carte.getPieces();
        int i = 0;
        boolean cond = pieces[i] != null;
        boolean tresor = false;
        while (cond && !tresor) {
            if (pieces[i].getRencontreType() == RencontreType.TRESOR)
                tresor = true;
            i++;
            cond = pieces[i] != null;
        }
        return tresor;
    }

    @Override
    public int getTresorTotal() {
        Piece[] pieces = carte.getPieces();
        int i = 0;
        boolean cond = pieces[i] != null;
        int ntresors = 0;
        while (cond) {
            if (pieces[i].getRencontreType() == RencontreType.TRESOR)
                ntresors ++;
            i++;
            cond = pieces[i] != null;
        }
        return ntresors;
    }

    @Override
    public boolean contientBoss() {
        Piece[] pieces = carte.getPieces();
        int i = 0;
        boolean cond = pieces[i] != null;
        boolean boss = false;
        while (cond && !boss) {
            if (pieces[i].getRencontreType() == RencontreType.BOSS)
                boss = true;
            i++;
            cond = pieces[i] != null;
        }
        return boss;
    }

    // Incomplet
    @Override
    public Piece[] cheminJusquAuBoss() {
        Piece[] pieces = carte.getPieces();
        int i = 0;
        boolean cond = pieces[i] != null;
        boolean discontinue = false;
        int pieceBoss = -1;
        while (cond && (pieceBoss ==-1) && !discontinue) {
            if (pieces[i].getRencontreType() == RencontreType.BOSS)
                pieceBoss = i;
            else if (!carte.existeCorridorEntre(pieces[i], pieces[i+1]))
                discontinue = true;
            i++;
            cond = pieces[i] != null;
        }
        return new Piece[0];
    }

    // Une fonction pour tester la classe
    public void test() {
        System.out.println(estPacifique());
        System.out.println(contientDuTresor());
        System.out.println(contientBoss());
        System.out.println(getTresorTotal());
        System.out.println(cheminJusquAuBoss());
    }
}
