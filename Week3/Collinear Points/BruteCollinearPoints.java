public class BruteCollinearPoints {
    private LineSegment[] arr;
    private int count;


    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
        throw new IllegalArgumentException();
        int n=points.length;
        arr=new LineSegment[n];
        count=0;
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
        for (int i=0;i<n;i++)
        {
            for (int j=i+1;j<n;j++)
            {
                for (int k=j+1;k<n;k++)
                {
                    for (int l=k+1;l<n;l++)
                    {
                        if (points[i]==null || points[j]==null || points[k]==null || points[l]==null)
                        throw new IllegalArgumentException();
                        //Checking for duplicate points
                        if (points[i].compareTo(points[j])==0 || points[i].compareTo(points[k])==0 || points[i].compareTo(points[l])==0 || points[j].compareTo(points[k])==0 || points[j].compareTo(points[l])==0 || points[k].compareTo(points[l])==0 )
                        throw new IllegalArgumentException();
                        double s1=points[i].slopeTo(points[j]);
                        if (points[i].slopeTo(points[k])==s1)
                        {
                            if (points[i].slopeTo(points[l]) == s1) //Now we have to find end-points of the line
                            {
                                Point min; Point max;
                                {
                                    if (points[i].compareTo(points[j]) <0 && points[i].compareTo(points[k]) < 0 && points[i].compareTo(points[l]) < 0)
                                    min=points[i];
                                    else if (points[j].compareTo(points[i]) <0 && points[j].compareTo(points[k]) < 0 && points[j].compareTo(points[l]) < 0)
                                    min=points[j];
                                    else if (points[k].compareTo(points[i]) <0 && points[k].compareTo(points[j]) < 0 && points[k].compareTo(points[l]) < 0)
                                    min=points[k];
                                    else
                                    min=points[l];
                                }
                                {
                                    if (points[i].compareTo(points[j]) >0 && points[i].compareTo(points[k]) > 0 && points[i].compareTo(points[l]) > 0)
                                    max=points[i];
                                    else if (points[j].compareTo(points[i]) >0 && points[j].compareTo(points[k]) > 0 && points[j].compareTo(points[l]) > 0)
                                    max=points[j];
                                    else if (points[k].compareTo(points[i]) >0 && points[k].compareTo(points[j]) > 0 && points[k].compareTo(points[l]) > 0)
                                    max=points[k];
                                    else
                                    max=points[l];
                                }
                                LineSegment obj=new LineSegment(min, max);
                                arr[count++]=obj;
                            }
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments()        // the number of line segments
    {
        return count;
    }
    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] obj=new LineSegment[count];
        for (int i=0;i<count;i++)
        {
            obj[i]=arr[i];
        }
        return obj;
    }
 }