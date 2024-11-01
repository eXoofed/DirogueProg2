package labyrinthe.soumission;
import labyrinthe.code_squelette.*;

public class MonLabyrinthe implements Labyrinthe { //TODO: extends ? implements?
    /**
     * Contient les ids que chaque piece a accès.
     */
    int[][] listesAdj;
    /**
     *
     */
    Piece[] pieces;

    public MonLabyrinthe(Piece[] pieces) {
        Piece temp;
        int nbPieces = pieces.length;
        //On déclare 8 emplacements afin d'éviter une quantité excessive de resizes.
        listesAdj = new int[nbPieces][8];
        for (int i = 0; i < nbPieces; i++) {
            listesAdj[i] = new int[] {-1,-1,-1,-1,-1,-1,-1,-1};
        }
        this.pieces = pieces;

        for (int i = 0; i < nbPieces-1; i++) {
            for (int j = 0; j < nbPieces-1-i; j++) {
                if (this.pieces[j].getID()>this.pieces[j+1].getID()){
                    temp= this.pieces[j];
                    this.pieces[j]=this.pieces[j+1];
                    this.pieces[j+1]=temp;
                }
            }
        }



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
