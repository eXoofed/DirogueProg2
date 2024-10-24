package labyrinthe.soumission;
import labyrinthe.code_squelette.*;

public class MonLabyrinthe implements Labyrinthe { //TODO: extends ? implements?
    /**
     *
     */
    Piece listesAdj[][];
    /**
     *
     */
    Piece pieces[];

    public MonLabyrinthe(int nbPieces) {
        listesAdj = new Piece[50][8];
        pieces = new Piece[nbPieces];
    }


    @Override
    public Piece[] getPieces() {
        return this.pieces;
    }

    @Override
    public int nombreDePieces() {
        return pieces.length;
    }

    @Override
    public void ajouteEntree(Exterieur out, Piece e) {
        int id = e.getID();
        int i = 0;

        while(i<8 && listesAdj[id-1][i] != null) {
            i++;
        }
        if (i <8){

            listesAdj[id-1][i]=out;
        }

    }

    @Override
    public void ajouteCorridor(Piece e1, Piece e2) {
        int id = e2.getID();
        int i = 0;
        while(i<8 && listesAdj[id-1][i] != null) {
            i++;
        }
        if (i <8){

            listesAdj[id-1][i]=e1;
        }

    }

    @Override
    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        for (int i = 0; i < 8; i++) {
            if (listesAdj[e2.getID()][i] == e1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Piece[] getPiecesConnectees(Piece e) {
        return listesAdj[e.getID()];
    }
}
