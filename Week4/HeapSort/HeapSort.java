//The functions sink, greater, exch,print,leftChild and rightChild have been copied from MaxHeap

public class HeapSort<T extends Comparable<? super T>>
{
    HeapSort() {}
    private boolean greater(T a, T b)
    {
        return (a.compareTo(b)) > 0;
    }
    private void exch(T[] a,int i,int j)
    {
        T temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    private int leftChild(int index)       //Returns left child
    {
        return (1+2*index);
    }
    private int rightChild(int index)         //Returns right child
    {
        return (2*(1+index));
    }
    private void sink(T[] arr,int index,int count)
    {
        boolean b=true;
        while (b && leftChild(index)<count )
        {
            if (rightChild(index) < count)
            {
                if (greater(arr[leftChild(index)],arr[rightChild(index)]))
                {
                    if (greater(arr[leftChild(index)],arr[index]))
                    {
                        exch(arr,leftChild(index),index);
                        index=leftChild(index);
                    }
                    else   b=false;
                }
                else
                {
                    if (greater(arr[rightChild(index)],arr[index]))
                    {
                        exch(arr,rightChild(index),index);
                        index=rightChild(index);
                    }
                    else   b=false;
                }
            }
            else
            {
                if (greater(arr[leftChild(index)],arr[index]))
                {
                    exch(arr,leftChild(index),index);
                    index=leftChild(index);
                }
                else   b=false;
            }
        }
    }
    public void sort(T[] arr)
    {
        for (int i=arr.length/2;i>=0;i--)
        sink(arr,i,arr.length);
        for (int i=arr.length-1;i>0;i--)
        {
            exch(arr,0,i);
            sink(arr,0,i);
        }
    }

    public void print(T[] arr,int count)
    {
        System.out.println();
        for (int i=0;i<count;i++)
        {
            System.out.print(arr[i] +" ");
        }
        System.out.println("\n");
    }
    public static void main(String[] args)
    {
        Integer[] a1={3,2,9,10,1,2,7,11,-1};
        String[] a2={"Dog","Cat","Apple","Pen","Music","Notes","Film"};
        HeapSort<Integer> obj1=new HeapSort<>();
        HeapSort<String> obj2=new HeapSort<>();
        obj1.sort(a1);
        obj2.sort(a2);
        obj1.print(a1,a1.length);
        obj2.print(a2,a2.length);
    }
}