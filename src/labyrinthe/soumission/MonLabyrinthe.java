package labyrinthe.soumission;
import labyrinthe.code_squelette.*;

public class MonLabyrinthe implements Labyrinthe {
    /**
     * Contient les ids que chaque piece a accès.
     */
    int[][] listesAdj;
    /**
     *
     */
    Piece[] pieces;

    public MonLabyrinthe(Piece[] pieces) {

        //On déclare 50 et 8 emplacements afin d'éviter une quantité excessive de resizes.
        listesAdj = new int[50][8];
        for (int i = 0; i < 50; i++) {
            listesAdj[i] = new int[] {-1,-1,-1,-1,-1,-1,-1,-1};
        }
        this.pieces = pieces;


    }



    @Override
    public Piece[] getPieces() {

        return this.pieces;
    }

    @Override
    public int nombreDePieces() {
        int j=0;
        //Puisque ceci peut varier selon les méthodes appelées, on calcule à chaque fois
        for (int i = 0; i < 50; i++) {
            if (pieces[i]!=null){
                j++;
            }
        }
        return j;
    }


    @Override
    public void ajouteEntree(Exterieur out, Piece e) {
        int id;

        if (e!=null)
        {
            id = e.getID();
            if (pieces[id]==null)
                pieces[id] = e;
            if (pieces[0]==null)
                pieces[0] = out;
            ajouteCorridor(out, e);
        }



    }

    @Override
    public void ajouteCorridor(Piece e1, Piece e2) {

        int i = 0;
        int idUn;
        int idDeux;

        //On vérifie que les param sont valables
        if (e1!=null && e2!=null){
            idUn = e1.getID();
            idDeux = e2.getID();
            //On ajoute le corridor si seulement il n'existe pas
            if (!existeCorridorEntre(e1,e2))
            {
                //On cherche le prochain espace dispo
                while(i<8 && listesAdj[idUn][i] != -1) {
                    i++;
                }
                if (i <8){

                    listesAdj[idUn][i]=idDeux;
                }
                //On fait de même pour l'autre pièce
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
        int idUn;
        int idDeux;
        //Vérification des params
        if (e1!=null && e2!=null)
        {
            idUn = e1.getID();
            idDeux = e2.getID();
            //Si matching, seulement besoin de vérifier sur une liste car ajouterCorridor fait les deux
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
        //Beson de tampon pour resize
        Piece[] temp = new Piece[0];
        Piece[] piecesConnectees = new Piece[0];

        if (e== null)
            return null;

        int i =0;
        int id = e.getID();

        //Collectionne les pièces adjacentes en parcourant les listes adjacentes pour prendre leur ID.
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
