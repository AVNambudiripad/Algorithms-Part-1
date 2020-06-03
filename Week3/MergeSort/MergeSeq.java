import java.util.Random;
public class MergeSeq   //No recursion
{
    private static void merge(Comparable[] a,Comparable[] aux,int l,int mid,int h)
    {
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
    private static void sort(Comparable[] a,Comparable[] aux)
    {
        for (int i=1;i<a.length;i*=2)
        {
            for (int j=0;j<a.length;j+=i*2)
            {
                merge(a,aux,j,j+i-1,Math.min(j+i*2-1, a.length-1));
            }
        }
    }
    public static void sort(Comparable[] a)
    {
        Comparable[] aux=new Comparable[a.length];
        sort(a,aux);
    }


    private static boolean less(Comparable x,Comparable y)
    {
        return x.compareTo(y) < 0;
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
        int N=100;
        Random r=new Random();
        Integer[] a=new Integer[N];
        for (int i=0;i<N;i++)
        a[i]=r.nextInt()%1000;
        print(a);
        sort(a);
        print(a);
    }
}