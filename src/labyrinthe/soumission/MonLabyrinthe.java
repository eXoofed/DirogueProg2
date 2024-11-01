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

        //On déclare 8 emplacements afin d'éviter une quantité excessive de resizes.
        listesAdj = new int[50][8];
        for (int i = 0; i < 50; i++) {
            listesAdj[i] = new int[] {-1,-1,-1,-1,-1,-1,-1,-1};
        }
        this.pieces = pieces;
        /*
        for (int i = 0; i < nbPieces-1; i++) {
            for (int j = 0; j < nbPieces-1-i; j++) {
                if (this.pieces[j].getID()>this.pieces[j+1].getID()){
                    temp= this.pieces[j];
                    this.pieces[j]=this.pieces[j+1];
                    this.pieces[j+1]=temp;
                }
            }
        }
         */

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
        ajouteCorridor(out, e);
    }

    @Override
    public void ajouteCorridor(Piece e1, Piece e2) {

        int i = 0;
        int idUn;
        int idDeux;



        if (e1!=null && e2!=null){
            idUn = e1.getID();
            idDeux = e2.getID();
            if (!existeCorridorEntre(e1,e2))
            {
                while(i<8 && listesAdj[idUn][i] != -1) {
                    i++;
                }
                if (i <8){

                    listesAdj[idUn][i]=idDeux;
                }

                while(i<8 && listesAdj[idDeux][i] != -1) {
                    i++;
                }
                if (i <8){

                    listesAdj[idDeux][i]=idUn;
                }
        }


        }



    }

    @Override
    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        int idUn = e1.getID();
        int idDeux = e2.getID();
        if (pieces[idUn]!=null && pieces[idDeux]!=null)
        {
            for (int i = 0; i < 8; i++) {
                if (listesAdj[idDeux][i] == idUn) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Piece[] getPiecesConnectees(Piece e) {
        Piece[] temp = new Piece[0];
        Piece[] piecesConnectees = new Piece[0];

        if (e== null)
            return null;

        int i =0;
        int id = e.getID();

        while (i<8 && listesAdj[id][i] != -1) {
            temp = piecesConnectees;

            piecesConnectees= new Piece[i+1];

            piecesConnectees[i]=pieces[listesAdj[id][i]];

            for (int j = 0; j < i; j++) {
                piecesConnectees[j] = temp[j];
            }

            i++;
        }

        return piecesConnectees;
    }


}
