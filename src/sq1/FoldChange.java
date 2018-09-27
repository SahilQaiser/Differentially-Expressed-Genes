package sq1;
public class FoldChange {
    Double A[][], B[][], Xi[], Yi[];    
    FoldChange(Double [][]a, Double [][]b)
    {
        A=a;
        B=b;
        Xi=Helper.getRowMean(A);
        Yi=Helper.getRowMean(B);
    }
   //Calculate FoldChange
    Double[] compute()
    {
        Double fc[]=new Double[Xi.length];
        for(int i=0; i<Xi.length; i++)
        {
            fc[i]=Helper.max(Xi[i],Yi[i])/Helper.min(Xi[i],Yi[i]);  // (Max(B,A)/Min(B,A)
        }
        return fc;
    }
}
