/* Une classe pour créer une carte */
public final class Carte implements Comparable<Carte>{
    /**
     * hauter de la carte
     */
	private final Hauteur hauteur;

    /**
     * la couleur de la carte
     */
	private final Couleur couleur;

    /**
     * constructeur de la carte
     * @param hauteur hauteur
     * @param couleur couleur
     */
	public Carte(Hauteur hauteur, Couleur couleur){
	    this.hauteur = hauteur;
	    this.couleur = couleur;
    }

    /**
     * récupèrer la hauteur
     * @return Hauteur
     */
    public Hauteur getHauteur(){ return this.hauteur; }

    /**
     * récupèrer la couleur
     * @return Couleur
     */
    public Couleur getCouleur(){ return this.couleur; }

    /**
     * vérifier la hauteur de 2 cartes
     * @param card carte à vérifier
     * @return boolean
     */
    @Override
    public boolean equals(Object card) {
	    Carte c = (Carte)card;
	    return (c.hauteur==this.hauteur || c.couleur == this.couleur);
    }

    /**
     * vérifier la différence de 2 cartes
     * @param c carte à vérifier
     * @return int
     */
    @Override
    public int compareTo(Carte c) {
        //TODO
        if(this.hauteur.ordinal() > c.hauteur.ordinal()) return 1;
        if(this.hauteur.ordinal() < c.hauteur.ordinal()) return -1;
        else                   return 0;
    }

    /**
     * affiche la carte
     * @return String
     */
    public String toString(){ return  this.hauteur.name() + " " + this.couleur.name() ; }

}