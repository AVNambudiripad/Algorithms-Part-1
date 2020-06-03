import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
public class Permutation
{
    public static void main(String[] args)
    {
        RandomizedQueue<String> obj=new RandomizedQueue<>();
        int k=Integer.parseInt(args[0]);
        while(!StdIn.isEmpty())
        {
            obj.enqueue(StdIn.readString());
        }
        int c=0;
        for (String i:obj)
        {
            if (c<k)
            {
                StdOut.println(i);
                c++;
            }
            else
            break;
        }
    }
}