import java.util.Random;
public class Shuffle
{
    public static <T> void shuffle(T[] a)
    {
        Random obj=new Random();
        for (int i=1;i<a.length;i++)
        {
            int r=obj.nextInt(i+1);
            if (r!=i)
            exch(a,r,i);
        }
    }
    private static <T>void exch(T[] a,int i,int j)
    {
        T temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    private static <T>void print(T[] a)
    {
        for (int i=0;i<a.length;i++)
        System.out.print(a[i]+" ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        Integer[] a={1,2,3,4,5,6,7,8,9,10};
        Shuffle.shuffle(a);
        Shuffle.print(a);
        Shuffle.shuffle(a);
        Shuffle.print(a);
        Shuffle.shuffle(a);
        Shuffle.print(a);
        Shuffle.shuffle(a);
        Shuffle.print(a);
    }
}