package labyrinthe.soumission;
import labyrinthe.code_squelette.*;

public class MonLabyrinthe implements Labyrinthe {
    /**
     * La listeAdj[a] est le tableau qui contient les IDs des pièces adjacentes à la pièce ayant le ID a.
     * -1 pour les emplacements vides.
     */
    int[][] listesAdj;
    /**
     * Contient toutes les pièces possibles, a une longueur de 50 et [ID] retourne la pièce associée
     * null aux pièces non-existance
     */
    Piece[] pieces;

    /**
     * Prend en paramètre un tableaux de pièces, initialise tous les listes adjacentes avec des -1 à chaque élément
     * @param pieces
     */
    public MonLabyrinthe(Piece[] pieces) {

        //On déclare 50 et 8 emplacements afin d'éviter une quantité excessive de resizes.
        listesAdj = new int[50][8];
        for (int i = 0; i < 50; i++) {
            //-1 désigne du "vide"
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
        //validité du param
        if (e!=null)
        {
            id = e.getID();
            //Si l'extérieur n'existe pas ou la pièce, on la rajoute.
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


        } else {
            System.out.println("Une ou les pièce(s) n'existe(nt) pas.");
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
        Piece[] temp;
        Piece[] piecesConnectees = new Piece[0];

        if (e== null)
            return null;

        int i =0;
        int id = e.getID();

        //Collectionne les pièces adjacentes en parcourant les listes adjacentes pour prendre leur ID.
        while (i<8 && listesAdj[id][i] != -1) {
            temp = piecesConnectees;

            piecesConnectees= new Piece[piecesConnectees.length+1];

            piecesConnectees[i]=pieces[listesAdj[id][i]];

            for (int j = 0; j < i; j++) {
                piecesConnectees[j] = temp[j];
            }

            i++;
        }

        return piecesConnectees;
    }


}
