import java.util.Random;
public class qSelect
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
    private static Comparable selectHelp(Comparable[] a,int k,int l,int h)   //kth element select;
    {   
        int pivot=partition(a, l, h);
        if (pivot==k)
        return a[k];
        else if (pivot < k) return selectHelp(a,k,pivot+1,h);
        else  return selectHelp(a, k, l, pivot-1);
    }
    public static Comparable select(Comparable[] a,int k)
    {
        return selectHelp(a,k-1,0,a.length-1);
    }


    public static void main(String[] args)
    {
        Integer[] a={11,2,4,35,31,1,10,17,45};
        float median;
        if (a.length%2==0)
        {
            median= 0.5f* ((int)select(a, a.length/2) + (int)select(a, 1+a.length/2));
        }
        else median = (int)select(a,(1+a.length)/2);
        System.out.println("The median is  "+median);
    }
}