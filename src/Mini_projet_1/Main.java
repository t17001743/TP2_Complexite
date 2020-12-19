package Mini_projet_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final int FALSE = -1;
    public static final int UNKNOWN = 0;
    public static final int TRUE = 1;

    public static void main(String[] args) throws IOException {

        //fichier .cnf contenant la formule
        String formule = "formule1.cnf";
        //fichier .cnf contenant les affectations des variables de la formule
        String affectations = "affectationVariables.cnf";

        int rows = 20;  //nombre de clauses (estimation par défaut par rapport à la taille du fichier)
        int columns = 20;  //nombre de variables (estimation par défaut par rapport à la taille du fichier)

        System.out.println("---------------Vérificateur déterministe pour SAT---------------");
        System.out.println();


        //***********************************************Fichier contenant la formule***********************************************

        Object[] clauses = new Object[rows-1];   //il y a nombre de lignes - 1 objets, en ne comptant pas la 1ère ligne

        String[][] data = new String[rows][columns];   //tableau des données avec "rows" lignes et "columns" colonnes (poids objet / valeur objet)
        Scanner lectureFichierFormule = new Scanner(new FileReader("src/Mini_projet_1/" + formule));

        int i = 0;
        System.out.println("Données de " + formule + " : ");

        // parcourir le fichier et le stocker dans le tableau de données
        while (lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            data[i] = ligne.split(" "); //transforme par exemple "a b c" en {"a","b","c"};
            System.out.println(Arrays.toString(data[i]));
            i++;
        }
        System.out.println();

        String[] variables = new String[Integer.parseInt(data[0][2])];  //tableau contenant les variables de la formule
        String nbClauses = data[0][3];

        System.out.print("Formule CNF : (");
        for(int j=0; j < Integer.parseInt(nbClauses); j++){
            for(int k=1; k < variables.length; k++){
                System.out.print(data[j][k]);
            }
        }
        System.out.println(")");
        System.out.println();


        //***********************************************Fichier d'affectations des variables***********************************************

        Scanner lectureFichierAffectations = new Scanner(new FileReader("src/Mini_projet_1/" + affectations));


    }
}
