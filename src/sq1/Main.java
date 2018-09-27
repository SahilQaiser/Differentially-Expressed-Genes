package sq1;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Double normal[][] = Helper.getMatrix(args[0]);
        Double tumor[][]=Helper.getMatrix(args[1]);
        System.out.println("Enter Filter Value : ");
        Double filter=(new Scanner(System.in).nextDouble());
        SAM sam = new SAM(normal,tumor);
        FoldChange fc = new FoldChange(normal,tumor);
        Double deg[]=sam.compute();
        Double degFC[]=fc.compute();
        System.out.println("Results from SAM:");
        Helper.printMatrixFiltered(deg, filter);
        System.out.println("Results from FoldChange:");
        Helper.printMatrixFiltered(degFC, filter);
        Helper.putMatrix(deg,degFC, "output.csv");
        
    }
}

