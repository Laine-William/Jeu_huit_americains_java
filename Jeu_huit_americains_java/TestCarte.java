/* Une classe pour tester une carte */

public class TestCarte {
    public static void main(String[] args){
        Carte c1 = new Carte(Hauteur.AS,Couleur.PIQUE);
        Carte c2 = new Carte(Hauteur.CINQ, Couleur.COEUR);
        Carte c3 = new Carte(Hauteur.CINQ, Couleur.TREFLE);
        Carte c4 = new Carte(Hauteur.HUIT, Couleur.CARREAU);

        System.out.println(c1);
        System.out.println(c3.equals(c2));
        System.out.println(c2.compareTo(c1));
        System.out.println(c2.compareTo(c3));
        System.out.println(c4.compareTo(c3));
    }
}
