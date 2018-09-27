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
        Double degFC1[]=fc.computeByMean();
        Double degFC2[]=fc.computeByMedian();
        System.out.println("Results from SAM:");
        Helper.printMatrixFiltered(deg, filter);
        System.out.println("Results from FoldChange [Mean] :");
        Helper.printMatrixFiltered(degFC1, filter);
        System.out.println("Results from FoldChange [Median] :");
        Helper.printMatrixFiltered(degFC2, filter);
        Helper.putMatrix(deg,degFC1, args[2]);
    }
}

