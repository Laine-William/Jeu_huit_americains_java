/* Une classe pour créer le jeu de cartes les 8 américains */

import java.util.*;

public class Jeu {
    /**
     *la pioche du jeu
     */
    private TasDeCartes pioche;
    /**
     *la défausse du jeu
     */
    private TasDeCartes defausse;
    /**
     *la liste des joueurs
     */
    private ArrayList<Joueur> joueurs;
    /**
     *vérification du changement de sens du jeu
     */
    private boolean jeuAChangeSens;
    /**
     * carte joué précédement jouée
     */
    private Carte cartePrecendent;
    /**
     *indice du joueur courant si on change de sens
     */
    private int indiceJoueur;

    /**
     *constructeur du jeu
     */
    public Jeu() {
        this.pioche = new TasDeCartes("PIOCHE");
        this.indiceJoueur=0;
        this.joueurs = new ArrayList<Joueur>();
        //création de defausse
        this.defausse = new TasDeCartes("DEFAUSSE");
    }

    /**
     * retourne le joueur à la position i
     * @param i la position du joueur
     * @return Joueur
     */
    public Joueur getJoueur(int i) {
        return joueurs.get(i);
    }

    /**
     * retourne la position du joueur dans la liste
     * @param joueur le joueur à chercher
     * @return int
     */
    public int getJoueurIndice(Joueur joueur) {
        return joueurs.indexOf(joueur);
    }

    /**
     * retourne la position du joueur dans la liste par rapport à son nom
     * @param nom le nom du joueur à chercher
     * @return int
     */
    public int getJoueurIndice(String nom) {
        int position=0;
        for(int i=0;i<joueurs.size();i++){
            if((joueurs.get(i).getNom()).equals(nom))
                return i;
        }
        return position;
    }

    /**
     * ajoute un joueur dans la liste
     * @param joueur le joueur à ajouter dans la liste de joueurs
     */
    public void ajouterJoueur(Joueur joueur) {
        joueurs.add(joueur);
    }

    /**
     * change le sens du jeu quand un valet est posée
     * @param joueurCourant  le joueur qui est entrain de jouer son tour
     */
    public void changerSens(Joueur joueurCourant){
        //on change le sens
        jeuAChangeSens=true;
        afficherJoueurs();
        //si le nombre de joueurs est de 2 le joueur suivant ne joue pas donc il suffit d'inverser leur position pour que le joueur courant rejoue
        if(joueurs.size()==2) {
            Collections.reverse(joueurs);
        }
        //si le nombre de joueurs est supérieur à 2
        else{
            //si le joueur courant est au milieu  et que la taille du tableau est impair, il suffit d'inverser les positions des joueurs avant et apres.
            if(joueurs.size()%2==1 && getJoueurIndice(joueurCourant)==((joueurs.size()/2)+1)){
                indiceJoueur=getJoueurIndice(joueurCourant)-1;
                Collections.reverse(joueurs);

            }
            else{
                boolean entredansIf=false;
                //le joueur courant se situe dans la première position donc c'est le tour du joueur avant lui
                if(getJoueurIndice(joueurCourant)==0){
                    Joueur j = new Joueur(joueurs.remove(0));
                    Collections.reverse(joueurs);
                    joueurs.add(0,j);
                    indiceJoueur=0;
                    entredansIf=true;
                }
                //le joueur courant se situe dans la dernière position, la liste des joueurs est inversé et on le met en première positon
                //donc le joueur suivant sera le joueur qui était avant lui
                if(getJoueurIndice(joueurCourant)==joueurs.size()-1 && !entredansIf){
                    Joueur j = new Joueur(joueurs.remove(joueurs.size()-1));
                    Collections.reverse(joueurs);
                    joueurs.add(0,j);
                    indiceJoueur = 0;
                    entredansIf=true;
                }
                //si le jouer courant n'est pas le premier ni le dernier dans la liste des joueurs
                if(getJoueurIndice(joueurCourant)<joueurs.size()-1 && getJoueurIndice(joueurCourant)>0 && !entredansIf){
                    ArrayList<Joueur> list = new ArrayList<Joueur>();
                    String nom = joueurCourant.getNom();
                    //0 à position joueur-1
                    while(!nom.equals(getJoueur(0).getNom())){
                        list.add(joueurs.remove(0));
                    }
                    //on inverse la liste des joueurs avant joueur courant
                    Collections.reverse(list);
                    // on inverse les joueurs restants dans la liste(la liste contient le joueur courant + les joueurs d'apres
                    //qui doivent pas jouer leur car le jeu a changé de sens. On inverse leur position pour les mettre avant joueur courant.
                    Collections.reverse(joueurs);
                    //on ajoute les joueurs qui doivent jouer(qui etaient dans des positions avant joueur courant) à la fin de la liste et ca devient leur tour
                    joueurs.addAll(joueurs.size(),list);
                    //on recherche sa place actuelle apres changement de sens
                    indiceJoueur = getJoueurIndice(nom);
                }
            }
        }
        //le jeu a changé de sens donc si le joueur précédent a sauté avec une carte spéciale avant joueur courant. Ca devient son tour.
        for(Joueur jou:joueurs){
            jou.setIlaSaute(false);
        }
    }

    /**
     * méthode pour permettre de jouer les cartes au joueur courant
     * @param joueurCourant le joueur qui doit jouer son tour
     */
    public void jouer(Joueur joueurCourant){
        //la carte tout en haut de défausse qu'on doit vérifier avant de jouer une carte
        Carte defausseTete= defausse.getDerniereCarte();

        //si la carte au dessus de defausse est AS le joueur pioche 2 cartes(si la carte retournée de defausse au debut du jeu est un AS on pioche pas
        if(defausseTete.getHauteur()==Hauteur.AS && defausse.getTaille()>1 && !pioche.estVide()) {
            System.out.println(joueurCourant+": c'est un AS qui a été posé, je pioche 2 cartes");
            if(pioche.getTaille()>=2){
                joueurCourant.piocher(pioche);
                joueurCourant.piocher(pioche);
            }else{
                //si la pioche n'a qu'une carte à la fin on prend la dernière
                joueurCourant.piocher(pioche);
            }
        }
        //le joueur saute si la carte précédente posée est un SEPT sinon il joue son tour
        if(defausseTete.getHauteur()==Hauteur.SEPT && cartePrecendent.getCouleur()!=defausseTete.getCouleur() && defausse.getTaille()>1) {
            System.out.println(joueurCourant+": c'est un 7 qui a été posé, je saute mon tour");
            joueurCourant.setTour(false);
            cartePrecendent= defausseTete;
            joueurCourant.setIlaSaute(true);
        }else{
            joueurCourant.setTour(true);
            //tant que le joueur n'a pas joué de carte
            while(joueurCourant.getTour()){
                //verifie si le joueur a la carte en haut de defausse
                if(joueurCourant.verifierMain(defausseTete)>-1) {
                    //il pose la carte qui correpond de couleur ou hauteur avec la carte de defausse
                    int val =joueurCourant.verifierMain(defausseTete);
                    joueurCourant.compterPoints(defausseTete);
                    defausse.ajouter(joueurCourant.getCartesJoueur().retirer(val));
                    System.out.println(joueurCourant+": je pose " + defausse.getDerniereCarte());
                    joueurCourant.setIlaSaute(false);
                    //si c'est un valet on change de sens
                    if(defausse.getDerniereCarte().getHauteur()==Hauteur.VALET && defausse.getTaille()>1 && joueurCourant.getTour()) {
                        System.out.println(joueurCourant+": c'est un VALET donc le jeu change de sens");
                        changerSens(joueurCourant);
                        joueurCourant.setTour(false);
                    }
                    joueurCourant.setTour(false);
                }
                //si le joeur n'a pas de carte correspondante, il pioche jusqu'a qu'il en ait une qui correspond
                if(!pioche.estVide() && joueurCourant.verifierMain(defausseTete)<=-1 && joueurCourant.getTour()){
                    joueurCourant.piocher(pioche);
                    System.out.println(joueurCourant+": je n'ai pas les cartes donc pioche une carte");
                }
                //si la pioche est vide on arrete son tour
                if(pioche.estVide())
                    joueurCourant.setTour(false);
            }
        }
        //s'il a posé un dix et que la pioche n'est pas vide, il rejoue
        if(defausse.getDerniereCarte().getHauteur()==Hauteur.DIX && !pioche.estVide()){
            System.out.println(joueurCourant+": je ai posé un DIX donc je rejoue");
            jouer(joueurCourant);
            joueurCourant.setTour(false);
        }
    }


    /**
     * méthode permettante de lancer le jeu si le nombre de joueurs et manches est correct
     * @param nbjoueurs le nombre de joueurs
     * @param nbmanches le nombre de manches
     */
    public void lancerJeu(int nbjoueurs, int nbmanches){
        //si le nombre de joueurs est supérieur à 2 et le nombre de manche supérieur à 0 on lance le jeu
        if(nbjoueurs>1 && nbjoueurs<11 && nbmanches>0){
            System.out.println("____________PREPARATION DE LA PIOCHE ET DE LA DEFAUSSE____________\n");
            pioche.getList().clear();
            defausse.getList().clear();
            joueurs.clear();
            this.pioche.remplir();
            this.pioche.melanger();
            // retourne une carte qui sera la défausse
            this.defausse.ajouter(pioche.retirerDernierCarte());
            nbPiocheEtDefausse();
            afficherPioche();
            afficherDefausse();
            //création des joueurs
            for(int i = 1; i<= nbjoueurs; i++){
                ajouterJoueur(new Joueur("JOUEUR " + i));
            }
            //distribuer les cartes aux joueurs
            distribuer();
            cartePrecendent = defausse.getCarte(0);
            jeuAChangeSens=false;
            //lancement des manches
            commencerPartie(nbmanches);
        }
        else{
            System.out.println("le nombre de joueurs est inferieur à 2 ou le nombre de manches est inferieur à 1");
        }
    }

    /**
     * une méthode permettante de distribuer les cartes au début du jeu
     */
    public void distribuer(){
        //on distribue 5 cartes pour chaque joueur
        for (int i = 0; i < 5; i++) {
            for(int j=0;j<this.joueurs.size();j++) {
                if(!pioche.estVide())
                getJoueur(j).piocher(this.pioche);
            }
        }
        afficherJoueurs();
    }
    /**
     * méthode permettante de faire jouer les joueurs et gérer les manches puis le résultat du jeu à la fin
     * @param nbmanches le nombre de manches
     */
    public void commencerPartie(int nbmanches){
        System.out.println("____________LANCEMENT DU JEU____________\n");
        for(int manche=1;manche <= nbmanches;manche++) {
            System.out.println("\n############# MANCHE " + manche + " \\(OoO)/ ############\n");
            int nbPioche = 0;
            //tant qu'on a pas joué 2 fois une pioche(jouer 2 fois une pioche, ce qui est 1 manche)
            while (nbPioche < 2) {
                int i = 0;
                System.out.println("____________LES MAINS DES JOUEURS____________\n");
                afficherJoueurs();
                afficherDefausse();
                while (i<joueurs.size() || !pioche.estVide()) {
                    if(i>joueurs.size()-1)
                        i=0;
                    //si on a changé de sens on récupère l'indice du joueur courant dans la liste car la liste est modifiée
                    if(jeuAChangeSens && joueurs.size()>2){
                        i = indiceJoueur+1;
                        jeuAChangeSens=false;
                    }
                    Joueur joueur = getJoueur(i);
                    //le joueur joue sont tour
                    System.out.println("\n____________ TOUR DE " + joueur + " °(O_O)°____________\n");
                    jouer(joueur);
                    //si la pioche est vide ou l'un des joueurs a la main vide
                    //le compteur s'incrémente pour permettre de devenir la defause la nouvelle pioche et remelanger la pioche
                    if(joueur.mainVide()||pioche.estVide()) {
                        nbPioche++;
                    }
                    nbPiocheEtDefausse();
                    afficherDefausse();
                    i++;
                }
                Carte c = defausse.retirerDernierCarte();
                pioche.ajouterTout(defausse);
                pioche.melanger();
                defausse.ajouter(c);
            }
            System.out.println("\n############# FIN DE LA MANCHE "+ manche + " #############\n");
            //affiche le(s) gagnat(s) de la manche
            afficherPoints(" DE MANCHE " + manche);
            //La fin de la manche puis on lance la partie suivante
            if(manche<nbmanches)
                System.out.println("____________RELANCEMENT DU JEU____________");
            //on relance à zéro la manche suivante avec des nouvelles cartes
            pioche.getList().clear();
            defausse.getList().clear();
            pioche.remplir();
            pioche.melanger();
            defausse.ajouter(pioche.retirerDernierCarte());
        }
        //les manches sont finies on compte les points et affiche les résultats
        System.out.println("\n############# FIN DU JEU #############\n");
        System.out.println("____________LES CARTES RESTANTES AUX JOUEURS____________\n");
        afficherJoueurs();
        //appliquer les pénalités si les joueurs ont des cartes restantes
        appliquerPenalites();
        //afficher le gagnant
        afficherPoints(" DU JEU");
    }

    /**
     * méthode pour afficher les points
     * @param s message en plus à affihcer
     */
    public void afficherPoints(String s){
        for(int i=0;i<joueurs.size();i++)
            System.out.println(getJoueur(i)+ " a " + getJoueur(i).getPoints() + " points.");
        System.out.println("LE(S) GAGNANT(S) "+ s + ": "+chercherGagnants() + " d_(^_^)_b");
    }

    /**
     * méthode pour appliquer les pénalités pour tous les joueurs qui ont des cartes restantes
     */
    public void appliquerPenalites(){
        System.out.println("\n____________COMPTAGE DES POINTS AVEC LES CARTES RESTANTES AUX MAINS DES JOUEURS____________\n");
        for(Joueur j: joueurs){
            if(!j.mainVide()){
                j.compterPenalites();
            }
        }
    }

    /**
     * méthode pour chercher le gagnant
     * @return retourne le(s) nom(s) de(s) gagnant(s)
     */
    public String chercherGagnant(){
        String nom ="";
        int points = 0;
        for(int i=0;i<joueurs.size();i++){
            if(getJoueur(i).getPoints() > points && !nom.equals(getJoueur(i).getNom())){
                points = getJoueur(i).getPoints();
                nom = getJoueur(i).getNom();
            }
        }
        return nom;
    }

    /**
     * méthode pour chercher les gagnants
     * @return retourne le(s) nom(s) de(s) gagnant(s)
     */
    public String chercherGagnants(){
        String nom =chercherGagnant();
        for(int i=0;i<joueurs.size();i++){
            if(getJoueur(i).getPoints() == (getJoueur(getJoueurIndice(nom))).getPoints() && !nom.equals(getJoueur(i).getNom())){
                nom = nom + getJoueur(i).getNom();
            }
        }
        return nom;
    }

    /**
     * méthode pour afficher pioche
     */
    public void afficherPioche(){
        System.out.println(pioche);
    }

    /**
     * méthode pour afficher défausse
     */
    public void afficherDefausse(){
        System.out.println(defausse);
    }

    /**
     * méthode pour afficher les joueurs avec leurs mains
     */
    public void afficherJoueurs(){
        for (Joueur joueur : this.joueurs) {
            System.out.println(joueur.getCartesJoueur());
        }
    }

    /**
     * méthode pour afficher le nombre de carte dans la pioche et la défausse
     */
    public void nbPiocheEtDefausse(){
        System.out.println("PIOCHE a " + this.pioche.getTaille()+ " carte(s) " + " et DEFAUSSE a " + this.defausse.getTaille() + " carte(s)");
    }
}