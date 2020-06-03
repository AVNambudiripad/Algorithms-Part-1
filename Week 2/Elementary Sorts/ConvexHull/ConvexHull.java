import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
public class ConvexHull
{
    //Stack of Points 
    private Node last;
    private class Node
    {
        Point data;
        Node prev;
        Node(Point p)
        {
            data=p;
            prev=null;
        }
    }
    private void push(Point p)
    {
        if (last==null) //Empty Stack
        {
            last=new Node(p);
        }
        else
        {
            Node obj=new Node(p);
            obj.prev=last;
            last=obj;
        }
    }
    private Point pop()
    {
        if (last==null)           return null;
        Point p=last.data;
        last=last.prev;
        return p;
    }
    private void print(Node n)    //Recursive function to print points from first to last
    {
        if (n==null)
        return;
        print(n.prev);
        System.out.print(n.data.x+"  "+n.data.y+"\n");
    }

    //Stack Code Ends

    //Code for sorting points by theta
    class SortByTheta implements Comparator<PointAndAngle>
    {
        public int compare(PointAndAngle p1,PointAndAngle p2)
        {
            if (p1.angle < p2.angle)
            return -1;
            else if (p1.angle > p2.angle)
            return 1;
            return 0;
        }
    }

    PointAndAngle[] arr;
    ConvexHull(Point[] d)
    {
        last=null;

        Point minY=d[0];
        for (int i=1;i<d.length;i++)
        if (d[i].y < minY.y)
        minY=d[i];
        arr=new PointAndAngle[d.length];
        for (int i=0;i<arr.length;i++)
        {
            PointAndAngle obj=new PointAndAngle();
            obj.p=d[i];
            obj.angle=Point.theta(minY, d[i]);
            arr[i]=obj;
        }
        Arrays.sort(arr,new SortByTheta());
        //print(arr);
    }
    public void ConvexHullPoints()
    {
        int i=0;   //i is the ith element of arr[] being considered
        ArrayList<Integer> al=new ArrayList<>();   //List of points in the Hull
        push(arr[0].p);     //  The first point is simply minY
        push(arr[1].p);     //  The second point (i.e - the smallest polar angle form minY) MUST be added. From Geometry
        al.add(0);
        al.add(1);
        i=2;
        while (i<arr.length)
        {
            int turn = Point.Turn(arr[al.get(al.size()-2)].p , arr[al.get(al.size()-1)].p  , arr[i].p);
            //System.out.print("Now, i = "+i+"  turn = "+turn+"  ");
            //System.out.println(al);
            if (turn == 0 )    //Straight line
            {
                i++;
            }
            else if (turn == 1)         //Left turn
            {
                al.add(i);
                push(arr[i].p);
                i++;
            }
            else      //Right turn
            {
                pop();
                al.remove(al.size()-1);
            }
        }
        print(last);
    }



    //For debugging 
    private void print(PointAndAngle[] arr)
    {
        for (int i=0;i<arr.length;i++)
        System.out.print(arr[i].p.x+"  "+arr[i].p.y+"  "+arr[i].angle+"\n");
    }
    private void print(ArrayList<Integer> al)
    {
        for(int i:al)
        System.out.print(i+"  ");
        System.out.println();
    }
}