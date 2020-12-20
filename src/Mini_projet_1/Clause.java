package Mini_projet_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Clause {

    //private String[] variables;  //tableau de variables pour chaque clause
    private String fichierFormule, fichierAffectation;
    private ArrayList<Boolean> tabValeurs;  //new ArrayList<Boolean>();
    private ArrayList<ArrayList<Integer>> tabClauses; //new ArrayList<ArrayList<Integer>>();
    private int nbVariables, nbClauses;

    public Clause(){
    }

    public Clause(String fichierFormule, String fichierAffectation) throws Exception {
        this.fichierFormule = fichierFormule;
        this.fichierAffectation = fichierAffectation;
        //lire fichiers + mémoriser tableaux et variables
        this.readFileFormule();
        this.readFileAffectation();
    }

    public boolean getValX(int i) throws Exception {
        if(1 <= i && i <= nbVariables) return tabValeurs.get(i);
        if(1 <= -i && -i <= nbVariables) return !tabValeurs.get(-i);
        throw new Exception("Mauvais indice de valeur");
    }

    public void setValX(int i) throws Exception{
        System.out.println("val x : " + i);
        if(1 <= i && i <= nbVariables) tabValeurs.set(i, true);
        else if (1 <= -i && -i <= nbVariables) tabValeurs.set(-i, false);
        else throw new Exception("Mauvais indice de valeur");
        System.out.println("val x : " + i);
    }

    public void readFileFormule() throws Exception {
        Scanner lectureFichierFormule = new Scanner(new FileReader("src/Mini_projet_1/" + fichierFormule));

        System.out.println("Données de " + fichierFormule + " : ");

        // parcourir le fichier et le stocker dans le tableau de données
        if(lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            String[] parts = ligne.split(" ");

            if(!parts[0].equals("p") || !parts[1].equals("cnf")){
                throw new Exception("Le fichier .cnf est incorrect.");
            }
            nbVariables = Integer.parseInt(parts[2]);
            nbClauses = Integer.parseInt(parts[3]);

            tabValeurs = new ArrayList<Boolean>(nbVariables + 1);
            tabClauses = new ArrayList<ArrayList<Integer>>(nbClauses + 1);
        }
        else throw new Exception("Fichier trop court");

        while (lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            String[] parts = ligne.split(" ");

            ArrayList<Integer> ligneClause = new ArrayList<Integer>();

            for(String part : parts) {
                ligneClause.add(Integer.parseInt(part));
            }
            System.out.println("toto");
            if(ligneClause.size() > 0){
                tabClauses.add(ligneClause);
            }
            System.out.println("titi");

        }
    }

    public void readFileAffectation() throws Exception {
        Scanner lectureFichierAffectation = new Scanner(new FileReader("src/Mini_projet_1/" + fichierAffectation));

        System.out.println("Données de " + fichierAffectation + " : ");

        if(lectureFichierAffectation.hasNextLine()) {
            String ligne = lectureFichierAffectation.nextLine();
            String[] parts = ligne.split(" ");

            for(String part : parts) {
                int k = Integer.parseInt(part);
                setValX(k);
            }
        }
    }

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
