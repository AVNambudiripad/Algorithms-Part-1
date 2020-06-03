import java.util.Random;
public class qSort3way
{
    private static boolean less (Comparable a,Comparable b)
    {
        return a.compareTo(b) < 0;
    }
    private static boolean equals(Comparable a, Comparable b)
    {
        return a.compareTo(b) ==0;
    }
    private static void exch(Comparable[] a,int i,int j)
    {
        Comparable temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    private static void print(Comparable[] a)
    {
        for (int i=0;i<a.length;i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    private static void shuffle(Comparable[] a)
    {
        int n=a.length;
        Random rand=new Random();
        for (int i=1;i<n;i++)
        {
            int r=rand.nextInt(i);
            exch(a,i,r);
        }
    }
    private static void sort(Comparable[] a,int l,int h)
    {
        //Pivot is a[lt]
        if (l>=h) return;
        
        int lt=l,i=l+1,gt=h;    // [l..lt-1] < pivot, [lt..gt] = pivot, [gt+1..h] > pivot
        while (i<=gt)
        {
            if (less(a[i],a[lt]))                { exch(a,lt++,i++);  }
            else if (equals(a[i],a[lt]))         { i++; }
            else                                 { exch(a,i,gt--);    }
        }

        sort(a,l,lt-1);
        sort(a,gt+1,h);
    }
    public static void sort(Comparable[] a)
    {
        shuffle(a);
        print(a);
        sort(a,0,a.length-1);
        print(a);
    }
    public static void main(String[] args)
    {
        Random r=new Random();
        Integer[] a=new Integer[50];
        for (int i=0;i<50;i++)
        a[i]=r.nextInt(10);
        sort(a);
    }
}