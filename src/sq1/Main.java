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
        Double deg[]=sam.compute();
        Helper.putMatrix(deg, "output.csv");
        Helper.printMatrixFiltered(deg, filter);
        //Helper.printMatrix(deg);
    }
}

