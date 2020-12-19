package Mini_projet_1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //fichier .cnf contenant la formule
        String formule = "formule1.cnf";
        //fichier .cnf contenant les affectations des variables de la formule
        String affectations = "affectationVariables1.cnf";

        int rows = 20;  //nombre de clauses (estimation par défaut par rapport à la taille du fichier)
        int columns = 20;  //nombre de variables (estimation par défaut par rapport à la taille du fichier)

        System.out.println("---------------Vérificateur déterministe pour SAT---------------");
        System.out.println();


        //***********************************************Fichier contenant la formule***********************************************

        Object[] clauses = new Object[rows-1];   //il y a nombre de lignes - 1 objets, en ne comptant pas la 1ère ligne

        String[][] data1 = new String[rows][columns];   //tableau des données avec "rows" lignes et "columns" colonnes
        Scanner lectureFichierFormule = new Scanner(new FileReader("src/Mini_projet_1/" + formule));

        int i = 0;
        System.out.println("Données de " + formule + " : ");

        // parcourir le fichier et le stocker dans le tableau de données
        while (lectureFichierFormule.hasNextLine()) {
            String ligne = lectureFichierFormule.nextLine();
            data1[i] = ligne.split(" "); //transforme par exemple "a b c" en {"a","b","c"};
            System.out.println(Arrays.toString(data1[i]));
            i++;
        }
        System.out.println();

        String[] variables = new String[Integer.parseInt(data1[0][2])];  //tableau contenant les variables de la formule
        int nbVariables = Integer.parseInt(data1[0][2]);
        String nbClauses = data1[0][3];

        /*System.out.print("Formule CNF : (");
        for(int j=0; j < Integer.parseInt(nbClauses); j++){
            for(int k=1; k < variables.length; k++){
                System.out.print(data1[j][k]);
            }
        }
        System.out.println(")");
        System.out.println();*/


        //***********************************************Fichier d'affectations des variables***********************************************

        String[][] data2 = new String[1][nbVariables];   //tableau des données avec une seule ligne et un nombre de colonnes égal au nombre de variables
        Scanner lectureFichierAffectations = new Scanner(new FileReader("src/Mini_projet_1/" + affectations));

        System.out.println("Données de " + affectations + " : ");

        //stocker le fichier dans le tableau de données
        String ligne = lectureFichierAffectations.nextLine();
        data2[0] = ligne.split(" "); //transforme par exemple "a b c" en {"a","b","c"};
        System.out.println(Arrays.toString(data2[0]));
        System.out.println();

        int TRUE = 1, FALSE = 0;

        //parcours du tableau des affectations
        for(int k=0; k < nbVariables; k++) {
            //si le vrai littéral a un "-" devant, il est égal à 0
            if(data2[0][k].startsWith("-")) data2[0][k] = String.valueOf(FALSE);
            //sinon, il est égal à 1
            else data2[0][k] = String.valueOf(TRUE);
            System.out.print("x" + (k+1) + " = " + data2[0][k] + ", ");
        }
        System.out.println();

    }
}
