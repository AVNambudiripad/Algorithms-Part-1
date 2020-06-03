public class MaxHeap<T extends Comparable<? super T>>
{
    private T[] arr;
    private int size;                  //Size of arr[]
    private int count;                 //The number of elements in arr[]
    MaxHeap()
    {
        size=2;
        count=0;
        arr=(T[])  new Comparable[2];
    }
    MaxHeap(T[] data)
    {
        size=data.length*2;
        count=0;
        arr=(T[]) new Comparable[size];
        for (int i=0;i<data.length;i++)
        insert(data[i]);
    }
    private void resize(int newSize)
    {
        T[] arr2;
        arr2=(T[]) new Comparable[newSize];
        for (int i=0;i<count;i++)
        arr2[i]=arr[i];
        arr=arr2;
    }
    public boolean isEmpty()
    {
        return count==0;
    }
    private int parent(int index)    //Returns parent index
    {
        return (index-1)/2;
    }
    private int leftChild(int index)       //Returns left child
    {
        return (1+2*index);
    }
    private int rightChild(int index)         //Returns right child
    {
        return (2*(1+index));
    }
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
    private void swim(int index)
    {
        if (index==0)
        return;
        while (index>0 && greater(arr[index] , arr[parent(index)] ))
        {
            exch(arr,index,parent(index));
            index=parent(index);            
        }
    }
    public void insert(T data)
    {
        arr[count++]=data;
        swim(count-1);
        if (count==size)
        resize(size*2);
    }
    private void sink(int index)
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
    public T remove()
    {
        if (isEmpty())  return null;
        exch(arr,count-1,0);
        T temp=arr[count-1];
        count--;
        sink(0);
        if (count == (size/4))
        resize(size/2);
        return temp;
    }

    public void print()
    {
        System.out.println();
        int temp=0;
        for (int i=0;i<count;i++)
        {
            System.out.print(arr[i] +" ");
            if (i==temp)
            {
                temp=rightChild(i);
                System.out.println();
            }
        }
        System.out.println("\n");
    }


    public static void main(String[] args)
    {
        Integer[] a={3,2,9,10,1,2,7,11,-1};
        MaxHeap<Integer> obj=new MaxHeap<>(a);
        obj.print();
        obj.insert(4);
        obj.insert(-3);
        obj.print();
        System.out.println(obj.remove());
        System.out.println(obj.remove());
        System.out.println(obj.remove());
        obj.print();
    }

}