import java.util.Random;
public class quickS
{
    private static boolean less (Comparable a,Comparable b)
    {
        return a.compareTo(b) < 0;
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
    private static int partition(Comparable[] a,int l,int h)
    {
        Comparable pivot=a[l];
        int i=l;
        int j=h+1;
        while (j>=i)
        {
            i++;
            while(i<=h && less(a[i],pivot))    {i++;}
            j--;
            while(less(pivot,a[j]))    {j--;}
            if (j>=i)
            exch(a,i,j);
        }
        exch(a,l,j);
        return j;
    }
    private static void quickSort(Comparable[] a,int l,int h)
    {
        if (l>=h) return;
        int pivot_index=partition(a, l, h);
        quickSort(a, l, pivot_index-1);
        quickSort(a, pivot_index+1, h);
    }
    public static void sort(Comparable[] a)
    {
        shuffle(a);
        print(a);
        quickSort(a,0,a.length-1);
        print(a);
    }



    public static void main(String[] args)
    {
        Integer[] arr={0,0,0,0,0,0,0,1,1,1,1,1};
        sort(arr);
    }
}