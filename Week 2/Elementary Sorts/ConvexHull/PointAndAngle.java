public class PointAndAngle   //helper class that can bind a point with the polar angle it makes it minY Point
{
    Point p;
    double angle;
    PointAndAngle() {    }
    PointAndAngle(Point x,double y)
    {
        p=x;
        angle=y;
    }
}