/* Une classe pour tester le jeu de cartes les 8 américains */

public class HuitAmericains {
    public static void main(String[] args){
        Jeu jeu = new Jeu();

        /*test avec 2 joueurs et 1 manche*/
        System.out.println("################################ AVEC 2 JOUEURS ##########################################");
        jeu.lancerJeu(2,1);
        System.out.println("################################ AVEC 4 JOUEURS ##########################################");
        /*testavec 4 joueurs et 3 manches*/
        jeu.lancerJeu(4,3);
    }
}
