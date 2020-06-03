public class InsertSort 
{
    public static <T extends Comparable<? super T>> void sort(T[] a)
    {
        int n=a.length;
        for (int i=1;i<n;i++)
        {
            for (int j=i;j>0 && less(a[j],a[j-1]);j--)
            {
                exch(a,j,j-1);
            }
        }
    }
    private static <T extends Comparable<? super T>> boolean less(T x, T y)
    {
        return x.compareTo(y)<0;
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
        System.out.print(a[i]+" ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        String[] s={"Apple","Quack","Year","Fear","Goku"};
        Integer[] a={10,2,-3,5,6,23,-2};
        InsertSort.sort(s);
        InsertSort.sort(a);
        print(s);
        print(a);
    }
}