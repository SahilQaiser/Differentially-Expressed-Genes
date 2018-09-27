package sq1;
public class FoldChange {
    Double A[][], B[][], Xi[], Yi[], Medx[], Medy[];    
    FoldChange(Double [][]a, Double [][]b)
    {
        A=a;
        B=b;
        Xi=Helper.getRowMean(A);
        Yi=Helper.getRowMean(B);
        Medx=Helper.getRowMedian(A);
        Medy=Helper.getRowMedian(B);
    }
   //Calculate FoldChange By Mean
    Double[] computeByMean()
    {
        Double fc[]=new Double[Xi.length];
        for(int i=0; i<Xi.length; i++)
        {
            fc[i]=Helper.max(Xi[i],Yi[i])/Helper.min(Xi[i],Yi[i]);  // (Max(B,A)/Min(B,A).. A and B are Means
        }
        return fc;
    }
    //Calculate FoldChange By Median
    Double[] computeByMedian()
    {
        Double fc[]=new Double[Xi.length];
        for(int i=0; i<Xi.length; i++)
        {
            fc[i]=Helper.max(Medx[i],Medy[i])/Helper.min(Medx[i],Medy[i]);  // (Max(B,A)/Min(B,A)... A and B are Medians
        }
        return fc;
    }
}
