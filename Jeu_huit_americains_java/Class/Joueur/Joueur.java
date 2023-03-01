/* Une classe pour créer un joueur */

import java.util.Collections;

public class Joueur{
    /**
     * nom du joueur
     */
    private String nom;

    /**
     * main du joueur
     */
    private final MainJoueur main;

    /**
     * les points du joueurs
     */
    private int points;

    /**
     * le tour du joueur
     */
    private boolean tour;

    /**
     * le saut de tour du jouer
     */
    private boolean ilAsaute;

    /**
     * constructeur pour créer un joueur
     * @param nom nom du jouer
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.tour=true;
        this.ilAsaute = false;
        this.points = 0;
        this.main = new MainJoueur(nom);
    }

    /**
     * constructeur par recopie
     * @param joueur joueur à recopier
     */
    public Joueur(Joueur joueur) {
        this.nom = joueur.nom;
        this.tour=joueur.tour;
        this.ilAsaute = joueur.ilAsaute;
        this.points = joueur.points;
        this.main = new MainJoueur(joueur.getMain());
    }

    /**
     * méthode pour récupérer le saut
     * @return boolean
     */
    public boolean getIlaSaute(){return this.ilAsaute;}

    /**
     * méthode pour récupérer le tour
     * @return boolean
     */
    public boolean getTour(){return this.tour;}

    /**
     * méthode pour récupérer les points du joueur
     * @return int
     */
    public int getPoints(){return this.points;}

    /**
     * méthode pour récupérer le nom du joueur
     * @return String
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * méthode pour vérifier la du joueur si elle est vide
     * @return boolean
     */
    public boolean mainVide(){return this.main.getCartesMain().estVide();}

    /**
     * récupérer la main du joueur avec le nom
     * @return MainJoueur
     */
    public MainJoueur getMain() { return this.main; }

    /**
     * les cartes de la main du joueur
     * @return CollectionCartes
     */
    public CollectionCartes getCartesJoueur(){return this.main.getCartesMain();}

    /**
     * modifier le tour
     * @param bool la valeur à donner
     */
    public void setTour(boolean bool){this.tour = bool;}

    /**
     * modifier le saut
     * @param bool la valeur à donner
     */
    public void setIlaSaute(boolean bool){this.ilAsaute = bool;}

    /**
     * piocher une carte dans pioche
     * @param pioche le Tas de Cartes à piocher dedans
     */
    public void piocher(CollectionCartes pioche){
        this.main.getCartesMain().ajouter(pioche.retirerDernierCarte());
        Collections.sort(this.main.getCartesMain().getList());
    }

    /**
     * compter les points du joueur
     * @param c la carte à vérifier
     */
    public void compterPoints(Carte c){
        if(c.getHauteur()==Hauteur.HUIT)
            this.points= this.points+20;
        if(c.getHauteur().ordinal()>Hauteur.VALET.ordinal() && c.getHauteur().ordinal()<Hauteur.HUIT.ordinal())
            this.points= this.points+10;
        if(c.getHauteur().ordinal()<Hauteur.VALET.ordinal())
            this.points= this.points+c.getHauteur().ordinal()+1;
    }

    /**
     * compter les cartes restantes et appliquer les pénalités
     */
    public void compterPenalites(){
        for(int i=0;i<this.main.getCartesMain().getTaille();i++) {
            if (this.main.getCartesMain().getCarte(i).getHauteur() == Hauteur.HUIT)
                this.points = this.points - 20;
            if (this.main.getCartesMain().getCarte(i).getHauteur().ordinal() > Hauteur.VALET.ordinal() && this.main.getCartesMain().getCarte(i).getHauteur().ordinal() < Hauteur.HUIT.ordinal())
                this.points = this.points - 10;
            if (this.main.getCartesMain().getCarte(i).getHauteur().ordinal() < Hauteur.VALET.ordinal())
                this.points = this.points - (this.main.getCartesMain().getCarte(i).getHauteur().ordinal() + 1);
        }
        if(this.points<=0)
            this.points=0;
    }

    /**
     * vérifie si la main a la carte c
     * @param c la carte à chercher
     * @return int
     */
    public int verifierMain(Carte c){
        int pos=-1;
        if(this.main.contient(c) >-1 ){
            pos= this.main.contient(c);
        }
        return pos;
    }

    /**
     * affichage du nom du joueur
     * @return String
     */
    public String toString() {
        return this.nom;
    }
}