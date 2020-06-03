public class Point    //Actually, It should Be POINT VECTOR
{
    public double x;
    public double y;
    Point(double i,double j)
    {
        x=i;
        y=j;
    }
    Point()  {}
    public static Point subt(Point p1,Point p2)         //Vector from p1 to p2 is p2-p1
    {
        Point p=new Point();
        p.x=p2.x-p1.x;
        p.y=p2.y-p1.y;
        return p;
    }
    public static double theta(Point p1,Point p2)        //Angle that a line from p1 to p2 makes with x-axis (0 to 2pi)
    {
        if (p1==p2)
        return 0;
        Point p=subt(p1,p2);
        //Use dot product formula to find angle with x-axis   or i-cap
        double theta = Math.toDegrees(Math.acos(p.x/Math.sqrt(p.x*p.x+p.y*p.y)));
        if (p.y < 0)
        theta=360 - theta;
        return theta;
    }
    public static int Turn(Point p1,Point p2,Point p3)  //If p1-p2-p3 anti-clockwise, return 1, if straight line, return 0, if clockwise, return -1
    {
        double a1=theta(p1,p2);
        double a2=theta(p2,p3);
        //System.out.println(a1+"     "+a2);
        if (a2 == a1)
        return 0;
        else if (a2 > a1)
        return 1;
        else
        return -1;
    }
}
