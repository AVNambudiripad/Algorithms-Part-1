import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] arr;
    private int count;
    private int size;
    public RandomizedQueue()
    {
        arr= (Item[]) new Object[2];
        count=0;
        size=2;
    }
    public boolean isEmpty()
    {
        return (count==0);
    }
    public int size()
    {
        return count;
    }
    public void enqueue(Item item)
    {
        if (item==null)
        throw new IllegalArgumentException();
        arr[count++]=item;
        if (count==size)
        resize(size*2);
    }
    public Item dequeue()
    {
        if (count==0)
        throw new NoSuchElementException();
        int i=StdRandom.uniform(size);
        while (arr[i]==null)
        i=StdRandom.uniform(size);
        Item i2=arr[i];
        arr[i]=null;
        count--;
        if (count==size/4)
        resize(size/2);
        return i2;
    }
    public Item sample()
    {
        if (count==0)
        throw new NoSuchElementException();
        int i=StdRandom.uniform(size);
        while (arr[i]==null)
        i=StdRandom.uniform(size);
        return arr[i];
    }
    public Iterator<Item> iterator()
    {
        return new RQIterator();
    }
    private class RQIterator implements Iterator<Item>
    {
        private Item[] ar;
        private int t;
        RQIterator()
        {
            ar= (Item[]) new Object[count];
            for (int i=0,j=0;i<size;i++)
            {
                if (!(arr[i]==null))
                ar[j++]=arr[i];
            }
            StdRandom.shuffle(ar);
            t=0;
        }
        public boolean hasNext()
        {
            return t<count;
        }
        public Item next()
        {
            if (!hasNext())
            throw new NoSuchElementException();
            return ar[t++];
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
    private void resize(int capacity)
    {
        Item[] arr2;
        arr2 = (Item[]) new Object[capacity];
        for (int i=0,j=0;i<count;j++)
        {
            if (arr[j]!=null)
            arr2[i++]=arr[j];
        }
        arr=arr2;
        size=capacity;
    }


    public static void main(String[] args)
    {
        RandomizedQueue<Integer> obj=new  RandomizedQueue<>();
        StdOut.println(obj.isEmpty());
        obj.enqueue(5);
        obj.enqueue(3);
        obj.enqueue(4);
        obj.enqueue(2);
        obj.enqueue(1);
        StdOut.println(obj.sample()+" "+obj.sample()+" "+obj.sample()+" "+obj.sample()+" ");
        StdOut.println(obj.dequeue()+"  "+obj.dequeue());
        StdOut.println(obj.size());
        for (int i:obj)
        StdOut.print(i + " ");
        StdOut.println("\n\n\n");


        int n = 5;
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(712);
         rq.enqueue(937);
         StdOut.println(rq.size());        
         rq.enqueue(985);
         rq.enqueue(830);
         rq.enqueue(589);
         StdOut.println(rq.size());
         StdOut.println(rq.isEmpty());    
    }
}