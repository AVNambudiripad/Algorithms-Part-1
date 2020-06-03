import java.util.Random;
public class i
{
    private static int compare(Integer nut,Character bolt)
    {
        char c = bolt;
        int t=c-'A';
        if (nut == t)        return 0;
        else if (nut > t)    return 1;
        else                 return -1;
    }
    private static void exch(Comparable[] a,int i,int j)
    {
        if (i==j)   return;
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
    private static int partitionN (Integer[] nuts, Character bolt,int l,int h)
    {
        //See GeeksForGeeks QuickSort
        int i=l,j=l;
        int equal_nut=l;
        while (i<=h)
        {
            if (compare(nuts[i],bolt) ==-1)
            {
                exch(nuts,i++,j++);
            }
            else if (compare(nuts[i],bolt) == 1)
            {
                i++;
            }
            else
            {
                exch(nuts,i++,j++);
                equal_nut=j-1;
            }
        }
        exch(nuts,equal_nut,j-1);
        return j-1;
    }
    private static int partitionS (Integer nut,Character[] bolts,int l,int h)
    {
        int i=l,j=l;
        int equal_bolt=l;
        while (i<=h)
        {
            if (compare(nut,bolts[i]) ==1)
            {
                exch(bolts,i++,j++);
            }
            else if (compare(nut,bolts[i]) == -1)
            {
                i++;
            }
            else
            {
                exch(bolts,i++,j++);
                equal_bolt=j-1;
            }
        }
        exch(bolts,equal_bolt,j-1);
        return j-1;
    }
    private static void sort(Integer[] nuts, Character[] bolts,int l,int h)
    {
        if (l>=h)    return;
        int pivot_nut=partitionN(nuts,bolts[l],l,h);
        int pivot_bolt=partitionS(nuts[pivot_nut],bolts,l,h);    //pivot_bolt = pivot_nut (As there are a one-to-one nut-bolt mapping)
        if (pivot_nut!=pivot_bolt)   System.out.println("WTF");
        sort(nuts,bolts,l,pivot_nut-1);
        sort(nuts,bolts,pivot_nut+1,h);
    }
    private static void sort(Integer[] nuts, Character[] bolts)
    {
        sort(nuts,bolts,0,nuts.length-1);
    }




    public static void main(String[] args)
    {
        Integer[] nuts = {0,1,2,3,4,5,6,7,8,9,10,11,12};
        Character[] bolts = {'A','B','C','D','E','F','G','H','I','J','K','L','M'};
        System.out.println(compare(nuts[3],bolts[3]));
        shuffle(nuts);
        shuffle(bolts);
        print(nuts);
        print(bolts);
        sort(nuts,bolts);

        print(nuts);
        print(bolts);

    }   
}