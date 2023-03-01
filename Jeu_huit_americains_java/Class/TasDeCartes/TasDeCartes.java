/* Une énumération pour créer un tas de cartes */

import java.util.Collections;

public class TasDeCartes extends CollectionCartes{
    /**
     * constructeur de tas de cartes
     * @param label le nom de tas de cartes
     */
    public TasDeCartes(String label) {
        super(label);
    }

    /**
     * mélanger les cartes
     */
    public void melanger () { Collections.shuffle (this.getList()); }

    /**
     * remplir le tas de cartes avec des cartes
     */
    public void remplir(){
        for (Hauteur hauteur : Hauteur.values ()) {
            for (Couleur couleur : Couleur.values ()) {
                this.getList().add(new Carte(hauteur,couleur));
            }
        }
    }
}
