import java.util.Random;
public class MergeRNoCopy   //MERGE SORT RECURSIVE
{
    MergeRNoCopy() {}
    private static boolean isSorted(Comparable[] a,int l,int h)
    {
        for (int i=l;i<=h;i++)
        {
            if (less(a[i],a[i+1]))
            return false;
        }
        return true;
    }
    private static void merge(Comparable[] a,final Comparable[] aux,int l,int mid,int h)  //NOTE THE FINAL KEYWORD
    {
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
        if (h-l < 8)
        {
            InsertSort.sort(a);
            return;
        }
        int mid=(l+h)/2;
        sort(aux,a,l,mid);
        sort(aux,a,mid+1,h);
        merge(a,aux,l,mid,h);

        /*
        1.Understanding this - The auxillary array is simply a CONSTANT array with TWO HALVES such that both halves are sorted
        2.In the sort(a,aux,l,h) header, "a" refers to where we want to store the changed array and "aux" is the auxillary array
        3.In our analysis, we start by assuming that the two sort() recursive calls have executed with no error
        4.The above point will hold as long as the base condition is OK, and our merge is OK. Thats how all recursive algos work
        5.After the 2 sort() methods, 'aux' is now sorted in each of its halves (but 'a' is not - 'a' simply acted as the auxillary storage)
        6.Then, merge() uses 'aux' as the auxillary storage and uses it to make 'a' sorted.
        7."aux" must be initialised as a copy of "a", since we have to sort "aux" and we cannot sort an empty array
        */
    }
    public static void sort(Comparable[] a)
    {
        Comparable[] aux=new Comparable[a.length];
        for (int i=0;i<a.length;i++)
        aux[i]=a[i];
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
            if ((i+1)%30==0)
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