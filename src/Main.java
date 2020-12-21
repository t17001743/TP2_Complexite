public class Main {

    public static void main(String[] args) throws Exception {

        String filePath = "./src/";  //chemin des fichiers
        Clause clause = new Clause("formule1.cnf", "affectationVariables1.cnf");

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
    }
}
