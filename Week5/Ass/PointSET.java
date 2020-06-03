import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Queue;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
public class PointSET {
    private TreeSet<Point2D> tree;
    public PointSET()                               // construct an empty set of points 
    {
        tree=new TreeSet<>();
    }
    public boolean isEmpty()                      // is the set empty? 
    {
        return tree.isEmpty();
    }
    public int size()                         // number of points in the set 
    {
        return tree.size();
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p==null)   throw new IllegalArgumentException();
        tree.add(p);
    }
    public boolean contains(Point2D p)            // does the set contain point p? 
    {
        if (p==null)   throw new IllegalArgumentException();
        return tree.contains(p);
    }
    public void draw()                         // draw all points to standard draw 
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D i:tree)
        {
            StdDraw.point(i.x(), i.y());
        }
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary) 
    {
        if (rect==null)                throw new IllegalArgumentException();
        Queue<Point2D> q=new LinkedList<>();
        Point2D temp;
        {
            Point2D x=new Point2D(0,rect.ymin());
            temp=tree.ceiling(x);
        }
        while (temp!=null && temp.y()<=rect.ymax())
        {
            if (rect.contains(temp))
            q.add(temp);
            temp=tree.higher(temp);
        }
        return q;
    }
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p==null) throw new IllegalArgumentException();
        Point2D temp1=tree.ceiling(p),temp2=tree.floor(p);
        boolean b1=temp1!=null;
        boolean b2=temp2!=null;
        boolean alternate=true;                    //Used to alternate b/w looking at higher and lower
        double minD=Double.POSITIVE_INFINITY;
        Point2D minP=null;
        while(b1 || b2)
        {
            if (alternate && b1)
            {
                double d=p.distanceSquaredTo(temp1);
                if (d<minD)
                {
                    minD=d;
                    minP=temp1;
                }
                temp1=tree.higher(temp1);
                if (temp1==null || Math.pow(Math.abs(temp1.y()-p.y()),2)>minD)
                {
                    b1=false;
                }
            }
            else if (b2)
            {
                double d=p.distanceSquaredTo(temp2);
                if (d<minD)
                {
                    minD=d;
                    minP=temp2;
                }
                temp2=tree.lower(temp2);
                if (temp2==null || Math.pow(Math.abs(temp2.y()-p.y()),2)>minD)
                {
                    b2=false;
                }
            }
            alternate=!alternate;
        }
        return minP;
    }
    public static void main(String[] args)                  // unit testing of the methods (optional) 
    {
        String filename = args[0];
        In in = new In(filename);
        PointSET kdtree = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        kdtree.draw();

        Point2D obj=new Point2D(0.1, 0.1);
        Point2D p=kdtree.nearest(obj);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.setPenRadius(0.05);
        StdDraw.point(p.x(),p.y());
    }
 }