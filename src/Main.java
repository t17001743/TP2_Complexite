public class Main {

    public static void main(String[] args) throws Exception {

        //début de l'exécution du programme
        long debut = System.nanoTime();

        String filePath = "./src/";  //chemin des fichiers
        Clause clause = new Clause(args[0], args[1]);

        //résultat du calcul de la formule avec l'affectation donnée en entrée
        boolean resultat = clause.evaluer();

        System.out.println();
        System.out.println("----------------------Vérificateur déterministe pour SAT----------------------");
        System.out.println();
        System.out.println("L'affectation satisfait-elle la formule ?");
        System.out.println();
        //si la formule est fausse, alors l'affectation des variables ne la vérifie pas
        if(!resultat) System.out.println("Réponse : NON");
        //sinon, la formule est vraie et donc l'affectation la vérifie
        else System.out.println("Réponse : OUI");
        System.out.println();

        //fin de l'exécution du programme
        long fin = System.nanoTime();
        //durée totale de calcul
        System.out.println("Temps d'exécution du programme : " + (fin - debut)/1000000 + " ms.");
        System.out.println();
    }
}
