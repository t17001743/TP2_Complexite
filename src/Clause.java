import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Clause {

    private String fichierFormule, fichierAffectation;  //les deux fichiers .cnf
    private ArrayList<Boolean> tabValeurs;  //liste des valeurs affectées aux variables
    private ArrayList<ArrayList<Integer>> tabClauses;  //liste des clauses de la formule
    private int nbVariables, nbClauses;  //nombre de variables et nombre de clauses de la formule


    /** Constructeur initialisant les paramètres et lisant les deux fichiers .cnf en stockant les données dans des ArrayList
     * @param fichierFormule le fichier .cnf contenant la formule
     * @param fichierAffectation le fichier .cnf contenant l'affectation des variables de la formule
     * @throws Exception
     */
    public Clause(String fichierFormule, String fichierAffectation) throws Exception {
        this.fichierFormule = fichierFormule;
        this.fichierAffectation = fichierAffectation;
        this.tabValeurs = new ArrayList<Boolean>();
        this.tabClauses = new ArrayList<ArrayList<Integer>>();
        this.readFileFormule();  //lecture du fichier de la formule
        this.readFileAffectation();  //lecture du fichier des affectations
    }


    /**
     * @param i le numéro de la variable
     * @return la valeur de la variable xi
     * @throws Exception
     */
    public boolean getValX(int i) throws Exception {
        if(1 <= i && i <= nbVariables) return tabValeurs.get(i);
        if(1 <= -i && -i <= nbVariables) return !tabValeurs.get(-i);  //si la valeur a un "-" devant
        throw new Exception("Mauvais indice de valeur");
    }


    /**
     * Changer la valeur de la variable xi
     * @param i le numéro de la variable
     * @throws Exception
     */
    public void setValX(int i) throws Exception{
        if(1 <= i && i <= nbVariables) tabValeurs.set(i, true);
        else if (1 <= -i && -i <= nbVariables) tabValeurs.set(-i, false);  //si la valeur a un "-" devant
        else throw new Exception("Mauvais indice de valeur");
    }


    /**
     * Lecture du fichier contenant la formule et stockage dans une ArrayList
     * @throws Exception si le fichier .cnf n'est pas correctement écrit
     */
    public void readFileFormule() throws Exception {
        Scanner lectureFichierFormule = new Scanner(new FileReader(fichierFormule));

        //parcourir la première ligne du fichier et stocker ses données dans un tableau
        if(lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            String[] parts = ligne.split(" ");  //on stocke les données lues dans un tableau

            //erreur si le fichier .cnf n'est pas correctement écrit
            if(!parts[0].equals("p") || !parts[1].equals("cnf")){
                throw new Exception("Le fichier .cnf est incorrect.");
            }

            //le nombre de variables se trouve juste après le "p cnf" sur la première ligne du fichier
            nbVariables = Integer.parseInt(parts[2]);
            //le nombre de clauses de la formule est la dernière valeur de la première ligne
            nbClauses = Integer.parseInt(parts[3]);

            //on crée la liste des variables de la formule
            for (int i=0; i <= nbVariables; i++)
                tabValeurs.add(false);
        }
        else throw new Exception("Fichier trop court");

        //parcours des lignes du fichier pour remplir la liste des clauses
        while (lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            String[] parts = ligne.split(" ");  //on stocke les données lues dans un tableau

            ArrayList<Integer> ligneClause = new ArrayList<Integer>();

            //ajout de l'ensemble des variables appartenant à une même clause
            for(String part : parts) {
                ligneClause.add(Integer.parseInt(part));
            }

            //ajout de la clause lue à la liste de clauses de la formule
            if(ligneClause.size() > 0){
                tabClauses.add(ligneClause);
            }

        }
    }


    /**
     * Lecture du fichier contenant les affectations des variables et stockage dans une ArrayList
     */
    public void readFileAffectation() throws Exception {
        Scanner lectureFichierAffectation = new Scanner(new FileReader(fichierAffectation));

        //lecture des données du fichier d'affectations
        if(lectureFichierAffectation.hasNextLine()) {
            String ligne = lectureFichierAffectation.nextLine();
            String[] parts = ligne.split(" ");  //on stocke les données lues dans un tableau

            //on affecte chaque valeur lue à la variable correspondante dans sa formule
            for(String part : parts) {
                int k = Integer.parseInt(part);
                setValX(k);
            }
        }
    }


    /**
     *
     * @return un booléen représentant le résultat final du calcul de la clause avec les valeurs affectées aux variables de la formule
     * @throws Exception
     */
    public boolean evaluer() throws Exception {
        boolean resGlobal = true;   //le résultat final du calcul de la formule

        //pour chaque clause de la formule
        for(ArrayList<Integer> ligneClause : tabClauses){
            boolean resLigne = false;  //résultat du calcul de la clause

            for(int k : ligneClause){
                if(k == 0) break;
                //"OU" entre chaque membre d'une même clause
                resLigne = resLigne || getValX(k);
            }

            //"ET" entre chaque clause
            resGlobal = resGlobal && resLigne;
        }

        //renvoi du résultat final
        return resGlobal;
    }
}