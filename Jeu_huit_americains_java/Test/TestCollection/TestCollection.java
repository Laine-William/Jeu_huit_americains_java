/* Une énumération pour tester une collection */

public class TestCollection {
    public static void main(String[] args){
        TasDeCartes defausse  = new TasDeCartes("defausse");
        TasDeCartes pioche = new TasDeCartes("pioche");
        TasDeCartes deck = new TasDeCartes("deck");
        deck.remplir();
        //créer pioche à partir de deck
        deck.ajouterTout(pioche);
        //créer defausse à partir de deck
        defausse.ajouter(pioche.retirerDernierCarte());
        System.out.println ("deck de taille "+deck.getTaille() +" "+deck);
        System.out.println ("pioche de taille "+pioche.getTaille() +" "+pioche);
        System.out.println ("defausse de taille "+defausse.getTaille() +" "+defausse);
        pioche.melanger();
        //faire distribution à partir de pioche aux mains
        Joueur bob = new Joueur("Bob");
        Joueur franck = new Joueur("Franck");
        System.out.println ("taille pioche avant distribution est "+pioche.getTaille() );
        for(int i=0;i<5;i++){
            bob.piocher(pioche);
            franck.piocher(pioche);
        }
        System.out.println ("taille pioche apres distribution est "+pioche.getTaille() );

        System.out.print(bob.getMain() + "\n" + franck.getMain());
    }
}
