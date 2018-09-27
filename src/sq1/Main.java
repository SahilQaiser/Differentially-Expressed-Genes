package sq1;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Double normal[][] = Helper.getMatrix(args[0]);
        Double tumor[][]=Helper.getMatrix(args[1]);
        //System.out.println("Enter Filter Value : ");
        //Double filter=(new Scanner(System.in).nextDouble());
        //SAM sam = new SAM(normal,tumor);
        //Double deg[]=sam.compute();
        //Helper.putMatrix(deg, "output.csv");
        //Helper.printMatrixFiltered(deg, filter);
        //Helper.printMatrix(deg);
        test(normal,tumor); 
    }
    static void test(Double normal[][], Double tumor[][])
    {
        int i=0;
        Double Xi=Helper.getRowMean(normal[i]);
        System.out.println("Xi : "+Xi);
        Double Yi=Helper.getRowMean(tumor[i]);
        System.out.println("Yi : "+Yi);
        Double Di=(Xi-Yi);
        Double Si=Helper.s(i, normal, tumor);
        System.out.println("Di : "+Di);
        System.out.println("Si : "+Si);
        Double S0=Helper.s0(normal, tumor);
        System.out.println("S0 : "+S0);
        System.out.println(Di/(Si+S0));
    }
}

