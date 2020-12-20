package Mini_projet_1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Clause {

    private String fichierFormule, fichierAffectation;  //les deux fichiers .cnf
    private ArrayList<Boolean> tabValeurs;  //liste des valeurs affectées aux variables
    private ArrayList<ArrayList<Integer>> tabClauses;  //liste des clauses de la formule
    private int nbVariables, nbClauses;


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


    public boolean getValX(int i) throws Exception {
        if(1 <= i && i <= nbVariables) return tabValeurs.get(i);
        if(1 <= -i && -i <= nbVariables) return !tabValeurs.get(-i);
        throw new Exception("Mauvais indice de valeur");
    }


    public void setValX(int i) throws Exception{
        if(1 <= i && i <= nbVariables) tabValeurs.set(i, true);
        else if (1 <= -i && -i <= nbVariables) tabValeurs.set(-i, false);
        else throw new Exception("Mauvais indice de valeur");
    }


    /**
     * Lecture du fichier contenant la formule et stockage dans une ArrayList
     * @throws Exception si le fichier .cnf n'est pas correctement écrit
     */
    public void readFileFormule() throws Exception {
        Scanner lectureFichierFormule = new Scanner(new FileReader(fichierFormule));

        // parcourir le fichier et le stocker dans le tableau de données
        if(lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            String[] parts = ligne.split(" ");

            if(!parts[0].equals("p") || !parts[1].equals("cnf")){
                throw new Exception("Le fichier .cnf est incorrect.");
            }
            nbVariables = Integer.parseInt(parts[2]);
            nbClauses = Integer.parseInt(parts[3]);

            // crée la liste des variables
            for (int i=0; i <= nbVariables; i++)
                tabValeurs.add(false);

            // pour tabClauses pas besoin, on va faire des add()
        }
        else throw new Exception("Fichier trop court");

        while (lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            String[] parts = ligne.split(" ");

            ArrayList<Integer> ligneClause = new ArrayList<Integer>();

            for(String part : parts) {
                ligneClause.add(Integer.parseInt(part));
            }
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

        if(lectureFichierAffectation.hasNextLine()) {
            String ligne = lectureFichierAffectation.nextLine();
            String[] parts = ligne.split(" ");

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
        boolean resGlobal = true;

        for(ArrayList<Integer> ligneClause : tabClauses){
            boolean resLigne = false;

            for(int k : ligneClause){
                if(k == 0) break;
                resLigne = resLigne || getValX(k);
            }

            resGlobal = resGlobal && resLigne;
        }

        return resGlobal;
    }
}