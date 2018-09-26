package sq1;
public class SAM {
    Double A[][],B[][];
    SAM(Double a[][], Double b[][])
    {
        A=a;
        B=b;
    } 
    public Double[] compute()
    {
        Double[] d=new Double[A.length];
        for(int i=0; i<A.length; i++)
        {
            d[i]=Helper.getRowMean(A[i]);           //Xi
            d[i]=d[i]-Helper.getRowMean(B[i]);      //Xi-Yi
            d[i]=d[i]/s(i);                         //Xi-Yi/s(i)
            d[i]=d[i]/s0();                         //Xi-Yi/s(i)*s0
        }
        return d;
    }
    //Percentile... S0
    Double s0()
    {
        Double x[]=new Double[A.length];
        for(int i=0; i<A.length; i++)
        {
            x[i]=s(i);
        }
        x=Helper.rankArray(x);
        int y=5*A.length/100;           //5th Percentile
        return x[y];
    }
    Double s(int i) //Statndard Deviation
    {
        Double x,XA,XB;
        XB=XA=0.0;
        for(int j=0; j<A[0].length; j++)
        {
            Double t1=A[i][j]-Helper.getRowMean(A[i]);
            XA+=(t1*t1);
        }
        for(int j=0; j<B[0].length; j++)
        {
            Double t2=B[i][j]-Helper.getRowMean(B[i]);
            XB+=(t2*t2);
        }
        x=a()*(XA+XB);
        x=Math.sqrt(x);
        return x;
    }
    Double a() //For Standard deviation
    {
        Double x;
        Double lenA=new Double(A[0].length);
        Double lenB=new Double(B[0].length);
        x=((1/lenA)+(1/lenB))/(lenA+lenB-2);
        return (x);
    }
}
