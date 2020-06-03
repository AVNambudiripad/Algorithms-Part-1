import java.lang.Math;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {

    private int N;
    private int T;
    private double frac[];
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        N=n;
        T=trials;
        frac=new double[T];
        for (int i=0;i<T;i++)
        {
            Percolation pobj=new Percolation(N);
            do
            {
                int r=1+StdRandom.uniform(N);
                int c=1+StdRandom.uniform(N);
                pobj.open(r,c);
                
            }while(!pobj.percolates());
            frac[i]=(1.0*pobj.numberOfOpenSites())/(N*N);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(frac);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(frac);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return (mean()-(1.96*stddev()/Math.sqrt(T)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return (mean()+(1.96*stddev()/Math.sqrt(T)));
    }

   // test client (see below)
   public static void main(String[] args)
   {
        int n=Integer.parseInt(args[0]);
        int t=Integer.parseInt(args[1]);
        PercolationStats obj=new PercolationStats(n, t);
        StdOut.print("mean                    = "+obj.mean()+"\n");
        StdOut.print("stddev                  = "+obj.stddev()+"\n");
        StdOut.print("95% confidence interval = ["+obj.confidenceLo()+", "+obj.confidenceHi()+"\n");
   }

}