import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private int[][] arr;
    private WeightedQuickUnionUF obj;
    private int count;
    private int n;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        this.n=n;
        if (n<=0)
        throw new IllegalArgumentException();
        arr=new int[n][n];   // 0 means block, 1 means open, 2 means open+connect to top, 4 means connect to bottom
        count=0;
        obj=new WeightedQuickUnionUF(n*n+2);   //(i,j) = i*col+j    n*n= top virtual element, n*n+1= bottom virtual
    }

    //check if (i,j) is valid or not
    private boolean check(int i,int j)
    {
        if (i<1 || i>n || j<1 || j>n)
        return false;
        return true;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if (row<1 || row > n)
        throw new IllegalArgumentException();
        if (col<1 || col > n)
        throw new IllegalArgumentException();

        if (arr[row-1][col-1]>0)    //Already Open
        return;

        arr[row-1][col-1]=1;
        count++;

        int st1=0,st2=0,st3=0,st4=0;

        if (check(row,col+1) && arr[row-1][col]>0)
        {
            obj.union((col-1+(row-1)*n)  ,  (col+(row-1)*n));
            st1=arr[row-1][col];
        }
        if (check(row,col-1) && arr[row-1][col-2]>0)
        {
            obj.union((col-1+(row-1)*n)  ,  (col-2+(row-1)*n));
            st2=arr[row-1][col-2];
        }
        if (check(row+1,col) && arr[row][col-1]>0)
        {
            obj.union((col-1+(row-1)*n)  ,  (col-1+(row)*n));
            st3=arr[row][col-1];
        }
        if (check(row-1,col) && arr[row-2][col-1]>0)
        {   
            obj.union((col-1+(row-1)*n)  ,  (col-1+(row-2)*n));
            st4=arr[row-2][col-1];
        }
        if (row==1)
        {
            obj.union(col-1 , n*n);
            arr[row-1][col-1]=2;
        }
        if (row==n)
        {
            obj.union(col-1+(n-1)*n , n*n+1);
            arr[row-1][col-1]=3;
        }


        if (st1==2 || st2==2 || st3==2 || st4==2)
        arr[row-1][col-1]=2;

        if (arr[row-1][col-1]==2)
        {
            if (st1>0 && check(row,col+1))
            arr[row-1][col]=2;
            if (st2>0 && check(row,col-1))
            arr[row-1][col-2]=2;
            if (st3>0 && check(row+1,col))
            arr[row][col-1]=2;
            if (st4>0 && check(row-1,col))
            arr[row-2][col-1]=2;
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (row<1 || row > n)
        throw new IllegalArgumentException();
        if (col<1 || col > n)
        throw new IllegalArgumentException();

        return (arr[row-1][col-1]>0);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (row<1 || row > n)
        throw new IllegalArgumentException();
        if (col<1 || col > n)
        throw new IllegalArgumentException();
        
        return (arr[row-1][col-1]==2);
    }  

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return count;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return obj.connected(n*n,n*n+1);
    }
}