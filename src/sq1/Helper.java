package sq1;

import java.io.*;
import java.util.Objects;
import java.util.*;

public class Helper {
    static int getNumberOfLines(String f)                       //To get Number of Rows for out Matrix
    {
        int lines=0;
        try
            {
               FileReader       input = new FileReader(f);
               LineNumberReader count = new LineNumberReader(input);
               while (count.skip(Long.MAX_VALUE) > 0)
               {
                  //Blank
               }
               lines = count.getLineNumber();
            } catch(IOException e)
            {
                System.out.println(e);
            }
        
        return lines;
    }
    //Get Mean For all Rows
    static Double[] getRowMean(Double m[][])
    {
        Double mean[]=new Double[m.length];
        for(int i=0; i<m.length; i++)
        {
            mean[i]=getRowMean(m[i]);
        }
        return mean;
    }
    //Get Mean For single Row
    static Double getRowMean(Double m[])
    {
        Double mean=0.0;
        for(Double cell : m){
            mean=mean+cell;
        }
        return mean/m.length;
    }
    //Get Median
    static Double[] getRowMedian(Double m[][])
    {
        Double median[]=new Double[m.length];
        for(int i=0; i<m.length; i++)
        {
            median[i]=m[i][0];
            Double row[]=m[i];
            Arrays.sort(row);
            median[i]=row[(1+row.length)/2];            
        }
        return median;
    }
    
    
    //Get A 2d matrix for a File
    static Double[][] getMatrix(String f1) throws IOException
    {
        int i,j=0;
        FileReader fr=new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        String line,row[];
        String head=br.readLine();
        row=head.split(",");                                                //To Get Number of Columns
        Double matrix[][]=new Double[getNumberOfLines(f1)-1][row.length-1];
        while((line=br.readLine())!=null){
            row=line.split(",");
            i=1;
            while(i<row.length)
            {
                matrix[j][i-1]=Double.parseDouble(row[i]);
                i++;
            }
            j++;
        }
        return matrix;
    }
    //Write a 1d Matrix with Gene Number to File
    static void putMatrix(Double m[], String f) throws IOException
    {
        try (FileWriter fw = new FileWriter(new File(f))) {
            fw.write("Gene Number, Result1\n");
            for(int i=0; i<m.length; i++)
            {
                fw.write("g"+(i+1)+","+m[i]+"\n");
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //Write 2 1d Matrices with Gene Number to File
    static void putMatrix(Double m[], Double n[], String f) throws IOException
    {
        try (FileWriter fw = new FileWriter(new File(f))) {
            fw.write("Gene Number, Result1, Result2\n");
            for(int i=0; i<m.length; i++)
            {
                fw.write("g"+(i+1)+","+m[i]+","+n[i]+"\n");
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    // Print 2d Matrix with gene Number to File
    static void putMatrix(Double m[][], String f) throws IOException
    {
        try (FileWriter fw = new FileWriter(new File(f))) {
            fw.write("Gene Number\n");
            for(int i=0; i<m.length; i++)
            {
                fw.write("g"+(i+1)+",");
                for(int j=0; j<m[i].length; j++)
                {
                    fw.write(m[i][j]+",");
                }
                fw.write("\n");
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //functions to Print Matrices
    static void printMatrix(Double m[][])           //Print a 2d matrix
    {
        int i;
        for(i=0; i<m.length; i++)
        {
            for(Double j:m[i])
            {
                System.out.print(j+"  ");
            }
            System.out.println();
        }
    }
    static void printMatrix(Double m[])             //Print a 1d matrix
    {
        for(Double j:m)
            {
                System.out.println(j);
            }
    }
    static void printMatrix(Double m[], Double n[]) //Print 2 1d Matrices as 2 Columns
    {
        for(int i=0; i<m.length; i++)
            {
                System.out.println(m[i]+"\t\t\t"+n[i]);
            }
    }
    static void printMatrixFiltered(Double m[], Double filter) //Print 1d Matrix's Values which are greater than filter
    {
        for(int i=0; i<m.length; i++)
            {
                if(m[i]>filter){
                    System.out.println("Gene "+(i+1)+", FoldChange : "+m[i]);
                }
            }
    }
    //Returning Max and Min out of 2
    static Double max(Double a, Double b)
    {
        Double x=(a>b)?(a):(b);
        return x;
    }
    static Double min(Double a, Double b)
    {
        Double x=(a>b)?(b):(a);
        return x;
    }
    
    //Get Rank of Elements of an array
    static Double[] rankArray(Double m[])
    {
        Double R[]=new Double[m.length];
        int r,s;
        for(int i=0; i<m.length; i++)
        {
            r=s=1;
            for(int j=0; j<m.length; j++)
            {
                if(i!=j && m[i]>m[j])
                {
                    r++;
                } else if(i!=j && Objects.equals(m[i], m[j]))
                {
                    s++;
                }
            }
            R[i]=(Double.valueOf(r)+(s-1)*0.5);
        }
        return R;
    }
    
    //Standard Deviation For 2 Arrays
    static Double s(int i,Double [][]A, Double[][] B) //Standard Deviation
    {
        Double x,XA,XB,a;
        XB=XA=0.0;
        for(int j=0; j<A[0].length; j++)
        {
            Double t1=A[i][j]-getRowMean(A[i]);
            XA+=(t1*t1);
        }
        for(int j=0; j<B[0].length; j++)
        {
            Double t2=B[i][j]-getRowMean(B[i]);
            XB+=(t2*t2);
        }
        Double lenA=new Double(A[0].length);
        Double lenB=new Double(B[0].length);
        a=((1/lenA)+(1/lenB))/(lenA+lenB-2);
        x=a*(XA+XB);
        x=Math.sqrt(x);
        return x;
    }
    //Variation For an Array s=(x-x`)^2/n
    static Double var(int i,Double [][]A) //Standard Deviation
    {
        Double x,XA;
        XA=0.0;
        for(int j=0; j<A[0].length; j++)
        {
            Double t1=A[i][j]-getRowMean(A[i]);
            XA+=(t1*t1);
        }
        Double lenA=new Double(A[0].length);
        x=(XA)/lenA;
        return x;
    }
    
    //Percentile... S0
    static Double s0(Double [][]A,Double [][]B)
    {
        Double x1[]=new Double[A.length];
        Double x2[][]=new Double[2][A.length];
        for(int i=0; i<A.length; i++)
        {
            x1[i]=s(i,A,B);
            x2[0]=x1;
        }
        x1=rankArray(x1);
        x2[1]=x1;
        int y=getIndex(x2,(5*A.length/100));        //5th Percentile for s(i)
        return x2[0][y];
    }
    //GetIndex for S0
    static int getIndex(Double [][]A,int y)
    {
        int x=-1;
        Double index=new Double(y);
        for(int i=0; i<A[1].length; i++)
        {
            if(Objects.equals(A[1][i], index))
            {
                x=i;
            }
        }
        return x;
    }
}
