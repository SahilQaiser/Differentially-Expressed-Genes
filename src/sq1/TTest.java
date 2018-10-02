package sq1;

public class TTest {
    Double A[][],B[][], Xi[],Yi[], SXi, SYi;
    TTest(Double [][]a, Double [][]b)
    {
        A=a;
        B=b;        
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
            Double SXY = Math.sqrt(SXi+SYi);        //(Sx1-Sx2)=Sqrt(S1^2/n1+S2^2/n2)
            ti[i]=(Xi-Yi)/SXY;                      //(X`1-X`2)/(Sx1-Sx2)
        }
        return ti;
    }
    Double[] computeShrinkage()
    {
        Double tk[]=new Double[A.length];
        Double varX[],varY[];
        varX=Helper.var(A);                         
        varY=Helper.var(B);                         
        for(int i=0; i<A.length; i++)
        {
            Double Xi=Helper.var(i, A);                 //X`k1
            Double Yi=Helper.var(i, B);                 //X`k2
            tk[i]=(Xi-Yi);                              //X`k1-X`k2
            Double vk1=VstarK(i,A);                     //V*k1/n1 
            Double vk2=VstarK(i,B);                     //V*k2/n2
            tk[i]=tk[i]/(Math.sqrt(vk1+vk2));           //(X`k1-X`k2)/sqrt(v*k1/n1+v*k2/n2)
        }
        return tk;
    }
    //Wk = (1/n * Summation((xik-x`k)^2))
    Double Wk(int k,Double M[][])
    {
        Double XI=0.0;
        for(int i=0; i<M[k].length; i++)
        {
            Double t=(M[k][i]-Helper.getRowMean(M[k]));
            XI+=(t*t);
        }
        return XI/M[k].length;
    }
    //Variance(Vk) = (n/(n-1)^3 * Summation(Wk-W`k)^2)
    Double Variance(int k,Double M[][])
    {
        Double variance=0.0;
        
        for(int i=0; i<M[k].length; i++)
        {
            Double wik=(M[k][i]-Wk(k,M));
            variance+=(wik*wik);
        }
        Double n=Double.valueOf(M[k].length);
        variance = (variance*n)/((n-1)*(n-1)*(n-1));
        return variance;
    }
    Double VstarK(int k, Double M[][]){             //Formula for Vk*/n
        Double vk=0.0;
        Double n=Double.valueOf(M[k].length);
        vk=Y(k,M)*(Helper.getRowMedian(k,M));   //Median For kth Row
        vk+=(1-Y(k,M))*Helper.var(k, M);        //(Y*Vmedian+(1-Y)*Var[k])
        return vk/n;
    }
    Double Vk(int i,Double M[][]){
        Double n=Double.valueOf(M[i].length);
        return (n/(n-1)*Wk(i,M));
    }
    //Summation (Variance(Vk))
    Double Y(int k, Double M[][])               //Formula for Lambda*
    {
        Double y=0.0;
        Double numerator=0.0;
        Double denominator=0.0;
        for(int i=0; i<M.length; i++)
        {
            numerator+=Variance(k,M);
            Double x=(Vk(i, M)-Helper.getRowMedian(k, M));
            denominator+=(x*x);
        }   
        y=numerator/denominator;
        return (Helper.min(1.0, y));            //Min[1,{Summation(Variance(Vk))/Summation((Vk-Vmedian)^2}]
    }
    
}
