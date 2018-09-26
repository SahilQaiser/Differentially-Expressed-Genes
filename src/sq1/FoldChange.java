package sq1;
public class FoldChange {
    Double A[][], B[][];
    FoldChange(Double [][]a, Double [][]b)
    {
        A=a;
        B=b;
    }
    //Calculate FoldChange
    Double[] compute(Double a[], Double b[])
    {
        Double fc[]=new Double[a.length-1];
        for(int i=0; i<a.length-1; i++)
        {
            fc[i]=Helper.max(a[i],b[i])/Helper.min(a[i],b[i]);  // (Max(B,A)/Min(B,A)
        }
        return fc;
    }
}
