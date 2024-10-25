package labyrinthe.rencontres;

/** Une classe qui représente les rencontres. Les sous classes
 * auront des strings différents pour les rencontres
 * @author William Perktold */
public abstract class Rencontre {

    /** Retourne un string en lien avec le type de rencontre
     * @return un string propre au type de rencontre */
    public abstract String rencontrer();

}
