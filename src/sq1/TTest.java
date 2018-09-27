package sq1;

public class TTest {
    Double A[][],B[][], Xi[],Yi[], SXi, SYi;
    TTest(Double [][]a, Double [][]b)
    {
        A=a;
        B=b;
        Xi=Helper.getRowMean(A); 
        Yi=Helper.getRowMean(B); 
        
    }
    Double[] computeSimple()
    {
        Double ti[]=new Double[A.length];
        Double Xi, Yi, SXi, SYi;
        for(int i=0; i<A.length; i++)
        {
            Xi=Helper.getRowMean(A[i]);
            Yi=Helper.getRowMean(B[i]);
            SXi=Helper.var(i, A);
            SYi=Helper.var(i, B);
            Double SXY = Math.sqrt(SXi+SYi);
            ti[i]=(Xi-Yi)/SXY;
        }
        return ti;
    }
}
