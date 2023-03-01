/* Une classe pour créer une main de joueur */

public class MainJoueur{
    /**
     * nom du joueur qui possède la main
     */
    private final String nom;

    /**
     * la liste des cartes dans la main
     */
    private final CollectionCartes cartes;

    /**
     * constructeur de main
     * @param nom le nom du joueur
     */
    public MainJoueur(String nom){
        this.nom = nom;
        this.cartes = new CollectionCartes(nom);
    }

    /**
     * constructeur par recopie
     * @param m la main à recopier
     */
    public MainJoueur(MainJoueur m){
        this.nom = m.nom;
        this.cartes = new CollectionCartes(m.nom);
        if(m.getCartesMain().getTaille()>0)
            this.cartes.getList().addAll(m.getCartesMain().getList());
    }

    /**
     * récupérer la liste des cartes
     * @return CollectionCartes
     */
    public CollectionCartes getCartesMain(){
	    return this.cartes;
    }

    /**
     * cherche la carte dans la main
     * @param c carte à chercher
     * @return int
     */
    public int contient(Carte c){
        for(int i=0;i<this.cartes.getTaille();i++) {
            if(this.cartes.getCarte(i).equals(c)){
                return i;
            }
        }
        return -1;
    }

    /**
     * affiche les cartes
     * @return String
     */
    public String toString(){
        return ""+ this.cartes;
    }
}