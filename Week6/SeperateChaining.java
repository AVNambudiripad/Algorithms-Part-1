import java.util.ArrayList;
import java.util.Random;
public class SeperateChaining<Key,Value>
{
    private ArrayList<Node> ar;
    private int M;
    SeperateChaining(int N)
    {
        M=N/5;
        ar=new ArrayList<Node>(M);
        for (int i=0;i<M;i++)
        ar.add(null);
    }
    public void put(Key key,Value value)
    {
        Node temp=ar.get(hash(key));
        if (temp==null)
        {
            temp=new Node(key,value);
            ar.set(hash(key),temp);
            return;
        }
        while (temp.next!=null)
        {
            if (temp.k.equals(key))
            {
                temp.v=value;
                return;
            }
            temp=temp.next;
        }
        temp.next=new Node(key,value);
    }
    public Value get(Key key)
    {
        Node n=ar.get(hash(key));
        while (n!=null)
        {
            if (n.k==key) return n.v;
            n=n.next;
        }
        return null;
    }
    public void delete(Key key)
    {
        Node n=ar.get(hash(key));
        if (n.k==key)
        {
            ar.set(hash(key),n.next);
            return;
        }
        while (n.next!=null)
        {
            if (n.next.k==key)
            {
                n.next=n.next.next;
                return;
            }
            n=n.next;
        }
    }
    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff)%(M);
    }
    private class Node
    {
        Key k;
        Value v;
        Node next;
        Node(Key key,Value value)
        {
            k=key;
            next=null;
            v=value;
        }
    }
    private void print (Node n)
    {
        if (n==null) return;
        System.out.print(" ("+n.k+" , "+n.v+") ");
        print(n.next);
    }
    public void print()
    {
        for (int i=0;i<ar.size();i++)
        {
            print(ar.get(i));
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        final int N=10;
        Random r=new Random();
        SeperateChaining<String,Double> obj=new SeperateChaining<>(N);
        System.out.println(obj.ar.size());

        obj.put("l4sFq4kc0l",r.nextDouble());
        obj.put("bvMMS1dvRE",r.nextDouble());
        obj.put("94kpttlwxl",r.nextDouble());
        obj.put("HkbrKTDaHN",r.nextDouble());
        obj.put("LmUsSqVnfk",r.nextDouble());
        obj.put("AUNTbJ7sHn",r.nextDouble());
        obj.put("41THEKioyv",r.nextDouble());
        obj.put("qo0Yl36mZm",r.nextDouble());
        obj.put("i616xvRCeD",r.nextDouble());
        obj.put("hoTMYP9EEa",r.nextDouble());
        obj.put("LmUsSqVnfk",r.nextDouble());
        obj.put("l4sFq4kc0l",r.nextDouble());

        obj.print();
        System.out.println();
        System.out.println();

        obj.delete("AUNTbJ7sHn");
        obj.delete("ads");
        obj.print();

        System.out.println();
        System.out.println();
        System.out.println(obj.get("41THEKioyv"));
    }
}