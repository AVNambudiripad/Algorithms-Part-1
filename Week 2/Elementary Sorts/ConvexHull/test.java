import java.util.Scanner;
public class test 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n");
        int n=sc.nextInt();
        Point[] arr=new Point[n];
        for (int i=0;i<n;i++)
        {
            Point obj=new Point();
            obj.x=sc.nextDouble();
            obj.y=sc.nextDouble();
            arr[i]=obj;
        }
        ConvexHull obj=new ConvexHull(arr);
        System.out.println("\n\n\n");
        obj.ConvexHullPoints();
    }   
}