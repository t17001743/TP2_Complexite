package Mini_projet_1;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/*public class Clause {

    public static final int FALSE = -1;
    public static final int UNKNOWN = 0;
    public static final int TRUE = 1;

    public ArrayList<int[]> clauses = new ArrayList<int[]>();
    public ArrayList<int[]> queryClauses = new ArrayList<int[]>();

    public void addClause(int[] clause) {
        clauses.add((int[]) clause.clone());
    }

    public void clearClauses() {
        clauses.clear();
    }

    public void addQueryClause(int[] clause) {
        queryClauses.add((int[]) clause.clone());
    }

    public void clearQueryClauses() {
        queryClauses.clear();
    }

    public boolean makeQuery()
    {
        try {
            int maxVar = 0;
            ArrayList<int[]> allClauses = new ArrayList<int[]>(clauses);
            allClauses.addAll(queryClauses);
            for (int[] clause: allClauses)
                for (int literal: clause)
                    maxVar = Math.max(Math.abs(literal), maxVar);
            PrintStream out = new PrintStream(new File("formule1.cnf"));
            out.println("p cnf " + maxVar + " " + allClauses.size());
            for (int[] clause: allClauses) {
                for (int literal: clause)
                    out.print(literal + " ");
                out.println("0");
            }
            out.close();
            Process process = Runtime.getRuntime().exec("affectationVariables.cnf");
            Scanner sc = new Scanner(process.getInputStream());
            sc.findWithinHorizon("RESULT:", 0);
            String result = sc.next();
            sc.close();
            process.waitFor();
            return result.equals("SAT");
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int testLiteral(int literal) {
        int result = UNKNOWN;
        clearQueryClauses();
        int[] clauseT = {literal};
        addQueryClause(clauseT);
        if (!makeQuery())
            result = FALSE;
        else {
            clearQueryClauses();
            int[] clauseF = {-literal};
            addQueryClause(clauseF);
            if (!makeQuery())
                result = TRUE;
        }
        clearQueryClauses();
        return result;
    }
}*/
