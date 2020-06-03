public class ShellSort
{
    private static <T extends Comparable<? super T>> void h_sort(T[] a,int len,int h)
    {
        for (int i=h;i<len;i++)
        {
            for (int j=i;j>=h && less(a[j],a[j-h]);j-=h)
            {
                exch(a,j,j-h);
            }
        }
    }
    public static <T extends Comparable<? super T>> void sort(T[] a)
    {
        int n=a.length;
        int h=1;
        while ((3*h+1)>n)
        h=3*h+1;
        while (h>=1)
        {
            h_sort(a,n,h);
            h/=3;
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
        ShellSort.sort(s);
        ShellSort.sort(a);
        print(s);
        print(a);
    }
}