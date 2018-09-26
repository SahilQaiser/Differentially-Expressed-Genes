package sq1;
import java.io.*;
import java.util.*;

public class SAM {
    public static void main(String[] args) throws IOException {
        Double normal[][] = Helper.getMatrix(args[0]);
        Double tumor[][]=Helper.getMatrix(args[1]);
        //System.out.println("Normal Gene Expressions Values : ");
        //Helper.printMatrix(normal);
        //System.out.println("Tumor Gene Expressions Values : ");
        //Helper.printMatrix(tumor);
        Double Xi[]=Helper.getRowMean(normal);        
        Double Yi[]=Helper.getRowMean(tumor);
        System.out.println("Enter Filter Value : ");
        Double filter=(new Scanner(System.in).nextDouble());
        Helper.printMatrixFiltered(FoldChange.compute(Xi, Yi), filter);
        //Helper.printMatrix(FoldChange.compute(Xi, Yi));
    }
}

