import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>
{
    private Node head;
    private Node tail;
    private int count;
    private class Node
    {
        Item data;
        Node next;
        Node prev;
        Node(Item item)
        {  
            data = item;
            next = null;
            prev = null;
        }
    }
    public Deque()
    {
        head=null;
        tail=null;
        count=0;
    }
    public boolean isEmpty()
    {
        return head==null;
    }
    public int size()
    {
        return count;
    }
    public void addFirst(Item item)
    {
        if (item==null)
        throw new IllegalArgumentException();
        count++;
        if (head==null)
        {
            head=new Node(item);
            tail=head;
        }
        else
        {
            Node obj=new Node(item);
            obj.next=head;
            head.prev=obj;
            head=obj;
        }
    }
    public void addLast(Item item)
    {
        if (item==null)
        addFirst(item);
        count++;
        if (head==null)
        {
            head=new Node(item);
            tail=head;
        }
        else
        {
            Node obj=new Node(item);
            obj.prev=tail;
            tail.next=obj;
            tail=obj;
        }
    }
    public Item removeFirst()
    {
        if (head==null)
        throw new NoSuchElementException();
        count--;
        if (count==0)
        {
            Item i=head.data;
            head=null;
            tail=null;
            return i;
        }
        else
        {
            Item i = head.data;
            head=head.next;
            head.prev=null;
            return i;
        }
    }
    public Item removeLast()
    {
        if (head==null)
        throw new NoSuchElementException();
        count--;
        if (count==0)
        {
            Item i=head.data;
            head=null;
            tail=null;
            return i;
        }
        else
        {
            Item i=tail.data;
            tail=tail.prev;
            tail.next=null;
            return i;
        }
    }
    public Iterator<Item> iterator()
    {
        return new dequeIterator();
    }
    private class dequeIterator implements Iterator<Item>
    {
        private Node current=head;
        public boolean hasNext()
        {
            return (current!=null);
        }
        public Item next()
        {
            if (!hasNext())
            throw new NoSuchElementException();
            Item i =current.data;
            current=current.next;
            return i;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args)
    {
        Deque<Integer> obj=new Deque<>();
        StdOut.println(obj.isEmpty());
        obj.addFirst(1);
        obj.addLast(3);
        obj.addFirst(2);
        obj.addFirst(10);
        obj.addLast(13);
        StdOut.println(obj.size());
        StdOut.println(obj.removeLast());
        StdOut.println(obj.removeFirst());
        for (int i : obj)
        StdOut.print(i+"  ");
        StdOut.print("\n\n\n");


        Deque<Integer> obj2=new Deque<Integer>();
        obj2.addFirst(1);
        obj2.removeFirst();
    }
}