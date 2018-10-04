package sq1;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Double normal[][] = Helper.getMatrix(args[0]);
        Double tumor[][]=Helper.getMatrix(args[1]);
        //FoldChange fold = new FoldChange(normal,tumor);
        //SAM sam = new SAM(normal,tumor);
        //System.out.println("SAM\t\t\tFoldChange_Median");
        //Helper.printMatrix(sam.compute(),fold.computeByMedian());
        TTest ttest = new TTest(normal,tumor);
        Helper.putMatrix(ttest.computeSimple(), args[2]);
        //System.out.println("TTest_Simple\t\t\tTTest_Shrinkage");
        //Helper.printMatrix(ttest.computeSimple(),ttest.computeShrinkage());
    }
}

