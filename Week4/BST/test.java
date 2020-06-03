import java.util.Iterator;

public class test {
    public static void main(String[] args)
    {
        ST<String,Double> obj=new ST<>();
        obj.put("C",11.34);
        obj.put("F",0.314);
        obj.put("D",2.3145);
        obj.put("B",3.14159265);
        obj.put("E",2.718281828);
        System.out.println(obj.get("B"));
        System.out.println(obj.get("Z"));
        System.out.println(obj.size());
        System.out.println(obj.rank("D"));
        System.out.println(obj.rank("B"));
        System.out.println(obj.rank("Z"));
        System.out.println(obj.rank("A"));
        System.out.println(obj.floor("I"));
        System.out.println(obj.ceiling("A"));
        Iterable<String> i1=obj.keysInorder();
        Iterable<String> i2=obj.keysLevelorder();
        for (String i:i1)
        System.out.print(i+"  ");
        System.out.println(); 
        for (String i:i2)
        System.out.print(i+"  ");
        System.out.println();

        obj.delete("D");
        i1=obj.keysInorder();
        for (String i:i1)
        System.out.print(i+"  ");
        System.out.println(); 
    }
}