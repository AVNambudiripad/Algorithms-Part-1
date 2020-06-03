import java.util.Random;
public class MergeRec   //MERGE SORT RECURSIVE
{
    MergeRec() {}
    private static boolean isSorted(Comparable[] a,int l,int h)
    {
        for (int i=l;i<=h;i++)
        {
            if (less(a[i],a[i+1]))
            return false;
        }
        return true;
    }
    private static void merge(Comparable[] a,Comparable[] aux,int l,int mid,int h)
    {
        assert isSorted(a, l, mid);
        assert isSorted(a,mid+1,h);
        for (int i=l;i<=h;i++)
        aux[i]=a[i];
        int i=l,j=mid+1;
        for (int k=l;k<=h;k++)
        {
            if (i > mid)  a[k]=aux[j++];
            else if (j > h) a[k]=aux[i++];
            else
            {
                if (less(aux[i],aux[j]))
                a[k]=aux[i++];
                else
                a[k]=aux[j++];
            }
        }
    }
    private static void sort(Comparable[] a,Comparable[] aux,int l,int h)
    {
        if(h-l < 8)
        {
            InsertSort.sort(a);
            return;
        }
        int mid=(l+h)/2;
        sort(a,aux,l,mid);
        sort(a,aux,mid+1,h);
        if (!less(a[mid],a[mid+1]))
        merge(a,aux,l,mid,h);
        assert isSorted(a, l, h);
    }
    public static void sort(Comparable[] a)
    {
        Comparable[] aux=new Comparable[a.length];
        sort(a,aux,0,a.length-1);
    }


    private static boolean less(Comparable x,Comparable y)
    {
        return x.compareTo(y) < 0;
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
            if ((i%30+1)==0)
            System.out.println(a[i]);
            else
            System.out.print(a[i]+" ");
        }
        System.out.println("\n\n");
    }


    public static void main(String[] args)
    {
        int N=200;
        Random r=new Random();
        Integer[] a=new Integer[N];
        for (int i=0;i<N;i++)
        a[i]=r.nextInt()%1000;
        print(a);
        sort(a);
        print(a);
    }
}