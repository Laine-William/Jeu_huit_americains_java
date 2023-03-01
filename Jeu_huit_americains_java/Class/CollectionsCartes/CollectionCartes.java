/* Une classe pour créer une collection de cartes */

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionCartes implements Iterable<Carte>{
    /**
     * nom de collection
     */
    private String nom;
    /**
     * la liste de cartes
     */
    private ArrayList<Carte> cartes;

    /**
     * constructeur pour créer la liste
     * @param nom nom de la liste
     */
	public CollectionCartes(String nom){
        this.nom = nom;
        this.cartes = new ArrayList<Carte>();
    }

    /**
     * vérifie si la liste est vide
     * @return boolean
     */
    public boolean estVide() {
        return cartes.isEmpty();
    }

    /**
     * retourne taille de la liste
     * @return int
     */
    public int getTaille() { return this.cartes.size(); }

    /**
     * retourne la liste des cartes
     * @return ArrayList<Carte>
     */
    public ArrayList<Carte> getList(){return this.cartes;}

    /**
     * récupère le nom de la liste
     * @return String
     */
    public String getNom() { return this.nom; }

    /**
     * récupère une carte dans la liste
     * @param i position de la carte
     * @return Carte
     */
    public Carte getCarte(int i) { return this.cartes.get(i); }

    /**
     * récupère la dernière carte dans la liste
     * @return Carte
     */
    public Carte getDerniereCarte() { return this.cartes.get(cartes.size()-1); }


    /**
     * ajouter une carte dans liste
     * @param c la carte à ajouter
     */
    public void ajouter(Carte c) { this.cartes.add(c);}

    /**
     * ajouter toutes les cartes de la liste dans coll
     * @param coll la liste qui prend les cartes
     */
    public void ajouterTout(CollectionCartes coll) {
        coll.getList().addAll(this.cartes);
    }

    /**
     * retirer une carte
     * @param i indice de la carte
     * @return Carte
     */
    public Carte retirer(int i)   { return this.cartes.remove(i); }

    /**
     * retirer la dernière carte
     * @return Carte
     */
    public Carte retirerDernierCarte(){
        return this.cartes.remove(cartes.size()-1);
    }

    /**
     * afficher les cartes
     * @return String
     */
    @Override
    public String toString () {
        String res = nom + " : " +
                this.cartes;
        return res;
    }

    /**
     * parcourir avec un iterator
     * @return Iterator<Carte>
     */
    @Override
    public Iterator<Carte> iterator() {
        Iterator<Carte> collectioncarte;
        return collectioncarte= this.cartes.iterator();
    }
}