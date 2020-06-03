import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
public class Board {

    private short[] arr;           //arr[i][j] = arr[j+i*n]
    private short n;
    private short[] posHole;         //Stores where the hole is
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
        n=(short)tiles.length;
        posHole=new short[2];
        arr=new short[n*n];
        for (short i=0;i<n;i++)
        for (short j=0;j<n;j++)
        {
            if (tiles[i][j]==0)
            {
                posHole[0]=i;
                posHole[1]=j;
            }
            arr[j+i*n]=(short)tiles[i][j];
        }
    }
                                           
    // string representation of this board
    public String toString()
    {
        String s=""+n+"\n";
        for (short i=0;i<n;i++)
        {
            for (short j=0;j<n;j++)
            {
                s=s+arr[j+i*n]+" ";
            }
            s=s+"\n";
        }
        return s;
    }

    // board dimension n
    public int dimension()
    {
        return n;
    }

    // number of tiles out of place
    public int hamming()
    {
        int ham=0;
        for (short i=0;i<n;i++)
        {
            for(short j=0;j<n;j++)
            {
                if (arr[j+i*n]==0)  continue;
                if (arr[j+i*n]!=(j+i*n+1))
                ham++;
            }
        }
        return ham;
    }
    private int manD(short ele,short i,short j)           //ele's current position is (i,j). It's manhattan distance is...
    {
        int correctI=(ele-1)/n;
        int correctJ=ele-n*correctI-1;
        //System.out.println("ele is "+ele+" i="+i+" j="+j+" cI="+correctI+" cJ="+correctJ);
        return Math.abs(i-correctI)+Math.abs(j-correctJ);
    }
    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    {
        int man=0;
        for (short i=0;i<n;i++)
        {
            for (short j=0;j<n;j++)
            {
                if (arr[j+i*n]!=0)
                man+=manD(arr[j+i*n],i,j);
            }
        }
        return man;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        return hamming()==0;
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass()!= this.getClass()) return false;
        Board temp=(Board)y;
        if (temp.dimension()!=this.dimension()) return false;
        return Arrays.equals(this.arr, temp.arr);
    }
    private int[][] shortToInt()   //Have to be used as API demands int[][] array
    {
        int[][] a1=new int[n][n];
        for (short i=0;i<n;i++)
        {
            for (short j=0;j<n;j++)
            {
                a1[i][j]=arr[j+i*n];
            }
        }
        return a1;
    }
    private void swapHoleAndElement(int i,int j)  //i,j is index of element to be swapped with
    {
        short t=arr[j+i*n];
        arr[j+i*n]=0;
        arr[posHole[1]+n*posHole[0]]=t;
        posHole[0]=(short)i;
        posHole[1]=(short)j;
    }
    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        Queue<Board> q=new LinkedList<>();
        if (posHole[0]!=0)
        {
            Board b=new Board(shortToInt());
            b.swapHoleAndElement(posHole[0]-1, posHole[1]);
            q.add(b);
        }
        if (posHole[0]!=n-1)
        {
            Board b=new Board(shortToInt());
            b.swapHoleAndElement(posHole[0]+1, posHole[1]);
            q.add(b);
        }
        if (posHole[1]!=0)
        {
            Board b=new Board(shortToInt());
            b.swapHoleAndElement(posHole[0], posHole[1]-1);
            q.add(b);
        }
        if (posHole[1]!=n-1)
        {
            Board b=new Board(shortToInt());
            b.swapHoleAndElement(posHole[0], posHole[1]+1);
            q.add(b);
        }
        return q;
    }
    private void swapElements(int i1,int j1,int i2,int j2)
    {
        short temp=arr[j1+i1*n];
        arr[j1+i1*n]=arr[j2+i2*n];
        arr[j2+i2*n]=temp;
    }
    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        Board b=new Board(shortToInt());
        if (posHole[0]==0)
        {
            b.swapElements(1, 0, 1, 1);
            return b;
        }
        else
        {
            b.swapElements(0, 0, 0, 1);
            return b;
        }
    }

    // unit testing (not graded)
    public static void main(String[] args)
    {
        int[][] arra={{1,0,3},{4,2,5},{7,8,6}};
        Board b=new Board(arra);
        System.out.print(b.toString());
        System.out.println(b.manhattan());
        System.out.println(b.hamming());
        Iterable<Board> q=b.neighbors();
        for (Board iter:q)
        {
            System.out.print("\n"+iter.toString());
        }
        Board b2=b.twin();
        System.out.print("\n"+b2.toString()); 
    }

}