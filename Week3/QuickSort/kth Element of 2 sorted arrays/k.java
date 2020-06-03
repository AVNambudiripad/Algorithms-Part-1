public class k
{
    public static int kth(int[] a1,int[] a2,int l1,int h1,int l2,int h2,int k)
    {
        if (l1>h1)   return a2[l2+k];
        if (l2>h2)   return a1[l1+k];
        int mid1=(l1+h1)/2;
        int mid2=(l2+h2)/2;
        if (mid1+mid2+2 <= k+1)
        {
            if (a1[mid1] < a2[mid2])
            {
                return kth(a1,a2,mid1+1,h1,l2,h2,k-mid1-1);
            }
            else
            {
                return kth(a1,a2,l1,h1,mid2+1,h2,k-mid2-1);
            }
        }
        else
        {
            if (a1[mid1] < a2[mid2])
            {
                return kth(a1,a2,l1,h1,l2,mid2-1,k);
            }
            else
            {
                return kth(a1,a2,l1,mid1-1,l2,h2,k);
            }
        }
    }
    public static void main(String[] args)
    {
        //int[] arr1={3,5,7,12,26,38,41,56,63};
        //int[] arr2={1,2,4,6,19,24,25,30,49,66,101,231};
        int[] arr1={1,2,5};
        int[] arr2={3,4,7};
        for (int i=0;i<(arr1.length+arr2.length);i++)
        {
            System.out.println(kth(arr1,arr2,0,arr1.length-1,0,arr2.length-1,i));
        }
    }
}