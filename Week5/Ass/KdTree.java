import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Queue;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
public class KdTree {
    private int count;
    private class Node
    {
        Point2D p;
        Node left;
        Node right;
        boolean axis;                              //true = Vertical, false = Horizontal
        RectHV r;
        Node(Point2D point,boolean bool,RectHV rect)
        {
            p=point;
            axis=bool;
            left=null;
            right=null;
            r=rect;
        }
    }
    private Node root;
    public KdTree()                               // construct an empty set of points 
    {
        root=null;
        count=0;
    }
    private int compare(Point2D p1,Point2D p2,boolean axis)
    {
        if (axis)
        {
            if (p1.x()>p2.x()) return 1;
            else if (p1.x()<p2.x())  return -1;
            else return p1.compareTo(p2);
        }
        else
        return p1.compareTo(p2);
    }
    public boolean isEmpty()                      // is the set empty? 
    {
        return root==null;
    }
    public int size()                         // number of points in the set 
    {
        return count;
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p==null)             throw new IllegalArgumentException();
        count++;
        if (root==null)
        {
            RectHV obj=new RectHV(0, 0, 1, 1);
            root=new Node(p,true,obj);
            return;
        }
        double xmin=0,xmax=1,ymin=0,ymax=1;
        root=insert(root,p,true,xmin,ymin,xmax,ymax);
    }
    private Node insert(Node n,Point2D p,boolean b,double xmin,double ymin,double xmax,double ymax)
    {
        if (n==null)
        {
            RectHV obj=new RectHV(xmin, ymin, xmax, ymax);
            return new Node(p,b,obj);
        }
        assert b==n.axis;
        int comp=compare(p,n.p,b);
        //System.out.println("For count="+count+"    comp="+comp);
        if (comp<0)
        {
            if (b)              n.left=insert(n.left,p,!b,xmin,ymin,n.p.x(),ymax);
            else                n.left=insert(n.left,p,!b,xmin,ymin,xmax,n.p.y());
        }
        else if (comp>0)
        {
            if (b)              n.right=insert(n.right,p,!b,n.p.x(),ymin,xmax,ymax);
            else                n.right=insert(n.right,p,!b,xmin,n.p.y(),xmax,ymax);
        }
        else  //Do not add the element
        {count--;}
        return n;
    }
    public boolean contains(Point2D p)            // does the set contain point p? 
    {
        if (p==null)  throw new IllegalArgumentException();
        return contains(root,p);
    }
    private boolean contains(Node n,Point2D p)
    {
        if (n==null)
        return false;
        int comp=compare(p,n.p,n.axis);
        if (comp==0) return true;
        else if (comp<0) return contains(n.left,p);
        else return contains(n.right, p);
    }
    public void draw()                         // draw all points to standard draw 
    {
        draw(root,0,0,1,1);
    }
    private void draw(Node n,double xmin,double ymin,double xmax,double ymax)  //NOT EFFICIENT
    {
        if (n==null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(n.p.x(),n.p.y());
        if (!n.axis)
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(xmin,n.p.y(),xmax,n.p.y());
            draw(n.left,xmin,ymin,xmax,n.p.y());
            draw(n.right,xmin,n.p.y(),xmax,ymax);
        }
        else
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(n.p.x(),ymin,n.p.x(),ymax);
            draw(n.left,xmin,ymin,n.p.x(),ymax);
            draw(n.right,n.p.x(),ymin,xmax,ymax);
        }
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary) 
    {
        if (rect==null)   throw new IllegalArgumentException();
        Queue<Point2D> q=new LinkedList<>();
        range(root,q,rect);
        return q;
    }
    private void range(Node n,Queue<Point2D> q,RectHV rect)
    {
        if (n==null)
        return;
        if (rect.contains(n.p))
        {
            q.add(n.p);
            range(n.left,q,rect);
            range(n.right,q,rect);
        }
        else
        {
            if (n.axis)
            {
                if (n.p.x() > rect.xmax())         range(n.left,q,rect);
                else if (n.p.x() < rect.xmin())      range(n.right,q,rect);
                else
                {
                    range(n.left,q,rect);
                    range(n.right,q,rect);
                }
            }
            else
            {
                if (n.p.y() > rect.ymax())         range(n.left,q,rect);
                else if (n.p.y() < rect.ymin())      range(n.right,q,rect);
                else
                {
                    range(n.left,q,rect);
                    range(n.right,q,rect);
                }
            }
        }
    }
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p==null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        double[] minR=new double[1];          //Beacuse Java doesn't have '&' operator
        minR[0]=p.distanceSquaredTo(root.p);  //minR is the minimum distance squared of the given point to any point in the set
        Point2D[] minP=new Point2D[1];
        minP[0]=root.p;
        return nearest(root,minR,minP,p);
    }
    private Point2D nearest(Node n,double[] minR,Point2D[] minP,Point2D p)
    {
        if (n==null) return null;
        if (n.r.distanceSquaredTo(p) > minR[0]) return null;
        double d=n.p.distanceSquaredTo(p);
        if (d<minR[0])
        {
            minR[0]=d;
            Point2D obj=new Point2D(n.p.x(), n.p.y());
            minP[0]=obj;
        }
        if (compare(p,n.p,n.axis) <0)
        {
            Point2D p1=nearest(n.left,minR,minP,p);
            Point2D p2=nearest(n.right,minR,minP,p);
            if (p1==null && p2==null)
            return minP[0];
            else if (p1==null)
            return p2;
            else if (p2==null)
            return p1;
            else
            {
                if (p1.distanceSquaredTo(p) < p2.distanceSquaredTo(p))
                return p1;
                else
                return p2;
            }
        }
        else
        {
            Point2D p1=nearest(n.right,minR,minP,p);
            Point2D p2=nearest(n.left,minR,minP,p);
            if (p1==null && p2==null)
            return minP[0];
            else if (p1==null)
            return p2;
            else if (p2==null)
            return p1;
            else
            {
                if (p1.distanceSquaredTo(p) < p2.distanceSquaredTo(p))
                return p1;
                else
                return p2;
            }
        }
    }
    /*
    private void print(RectHV r)
    {
        System.out.println(r.xmin()+"   "+r.ymin()+"   "+r.xmax()+"   "+r.ymax()+"   ");
    }  */
    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        kdtree.draw();
    } 
 }