import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
public class FastCollinearPoints {
    private LineSegment[] arr;
    private int count;
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   {
        if (points==null)
        throw new IllegalArgumentException();
        int n=points.length;
        count=0;
        arr=new LineSegment[n];
        if (n<4)   //4 collinear points are impossible
        {
            for (int i=0;i<n;i++)
            {
                if (points[i]==null )   throw new IllegalArgumentException();
                for (int j=0;j<i;j++)
                if (points[j].compareTo(points[i])==0)   throw new IllegalArgumentException();
            }
            return;
        }
        //Checking for null values
        for (int i=0;i<n;i++)
        if (points[i]==null)
        throw new IllegalArgumentException();

        for (int i=0;i<n-3;i++)     //CHANGE THIS LATER BHOSADIKE
        {
            Arrays.sort(points,i+1,n,points[i].slopeOrder());
            //print(points,points[i]);
            int j=i+1;
            if (points[i]==null)  throw new IllegalArgumentException();
            if (points[i].compareTo(points[j])==0)  throw new IllegalArgumentException();
            
            int local_count=2;
            double s=points[i].slopeTo(points[j]);
            Point min=points[i];
            Point max=points[i];
            if (min.compareTo(points[j]) >0)  min=points[j];
            if (max.compareTo(points[j]) <0)  max=points[j]; 
            for (j=i+2;j<n;j++)
            {
                /*
                System.out.println("Now,  j= "+j+"  local_counter = "+local_count+"   s="+s);
                print(min);
                print(max);
                System.out.println("\n\n");  */


                if (points[i].compareTo(points[j])==0 ) throw new IllegalArgumentException();
                if (points[i].slopeTo(points[j])==s)
                {
                    local_count++;
                    if (min.compareTo(points[j]) >0)  min=points[j];
                    if (max.compareTo(points[j]) <0)  max=points[j];  
                }
                else
                {
                    if (local_count>=4)
                    {
                        //System.out.println("THIS IS BEING ADDED TO LINE SEGMENT ");
                        //print(min);
                        //print(max);
                        LineSegment obj=new LineSegment(min, max);
                        arr[count++]=obj;
                    }
                    s=points[i].slopeTo(points[j]);
                    local_count=2;
                    min=points[i];
                    max=points[i];
                    if (min.compareTo(points[j]) >0)  min=points[j];
                    if (max.compareTo(points[j]) <0)  max=points[j];
                }
            }
            if (local_count>=4)
                {
                    //System.out.println("THIS IS BEING ADDED TO LINE SEGMENT ");
                    //print(min);
                    //print(max);
                    LineSegment obj=new LineSegment(min, max);
                    arr[count++]=obj;
                }
        }
        
   }
   public           int numberOfSegments()        // the number of line segments
   {
       return count;
   }
   public LineSegment[] segments()                // the line segments
   {
       LineSegment[] obj=new LineSegment[count];
       for (int i=0;i<count;i++)
       obj[i]=arr[i];
       return obj;
   }



    /*
   private static void print(Point[] d)
   {
       for (int i=0;i<d.length;i++)
       {
           System.out.println("("+d[i].getX()+" , "+d[i].getY()+")");
       }
       System.out.println("\n\n");
   }
   private static void print(Point[] d,Point p)
   {
       for (int i=0;i<d.length;i++)
       {
           System.out.println("("+d[i].getX()+" , "+d[i].getY()+")    Slope = "+p.slopeTo(d[i]));
       }
       System.out.println("\n\n");
    }
    private static void print(Point p)
    {
        System.out.println("("+p.getX()+" , "+p.getY()+")");
    }
    public static void main(String[] args)
    {
        int n=StdIn.readInt();
        Point[] a=new Point[n];
        for (int i=0;i<n;i++)
        {
            int x=StdIn.readInt();
            int y=StdIn.readInt();
            Point obj=new Point(x,y);
            a[i]=obj;
        }
        print(a);
        FastCollinearPoints obj=new FastCollinearPoints(a);
    }
    */
}