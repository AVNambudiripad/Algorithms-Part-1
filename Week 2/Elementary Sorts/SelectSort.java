public class SelectSort
{
    public static  <T extends Comparable<? super T>> void sort(T[] a)
    {
        int n=a.length;
        for (int i=0;i<n;i++)
        {
            int min=i;
            for (int j=i;j<n;j++)
            {
                if (less(a[j],a[min]))
                min=j;
            }
            if (min!=i)
            exch(a,i,min);
        }
    }
    private static <T extends Comparable<? super T>> boolean less(T x,T y)
    {
        return (x.compareTo(y) < 0);
    }
    private static void exch(Comparable[] a,int i,int j)
    {
        Comparable obj1=a[i];
        a[i]=a[j];
        a[j]=obj1;
    }
    public static void print(Comparable[] a)
    {
        for (int i=0;i<a.length;i++)
        System.out.print(a[i]+"  ");
        System.out.println();
    } 
    public static void main(String[] args)
    {
        String[] s={"Apple","Quack","Year","Fear","Goku"};
        Integer[] a={10,2,-3,5,6,23,-2};
        SelectSort.sort(s);
        SelectSort.sort(a);
        print(s);
        print(a);
    }
}