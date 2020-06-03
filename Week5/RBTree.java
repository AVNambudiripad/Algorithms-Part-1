
public class RBTree<Key extends Comparable<? super Key>,Value>
{
    private class Node
    {
        Key k;
        Value v;
        Node left;
        Node right;
        boolean color;  //true=RED, false=BLACK
        Node(Key key,Value value,boolean b)
        {
            k=key;
            v=value;
            left=null;
            right=null;
            color=b;
        }
    }
    private Node root;
    RBTree()
    {
        root=null;
    }
    private boolean isRed(Node n)
    {
        if (n==null)
        return false;
        return n.color;
    }
    public Value get(Key key)
    {
        return get(root,key);
    }
    private Value get(Node node,Key key)
    {
        if (node==null) return null;
        int comp=key.compareTo(node.k);
        if (comp==0)
        return node.v;
        else if (comp>0)
        return get(node.right,key);
        else
        return get(node.left,key);
    } 
    private Node rotateLeft(Node h)
    {
        assert isRed(h.right);
        h.right.color=h.color;
        h.color=true;
        Node temp=h.right;
        h.right=temp.left;
        temp.left=h;
        return temp;
    }
    private Node rotateRight(Node h)
    {
        assert isRed(h.left);
        h.left.color=h.color;
        h.color=true;
        Node temp=h.left;
        h.left=temp.right;
        temp.right=h;
        return temp;
    }
    private void flipColors(Node h)
    {
        assert (h.left.color == h.right.color);
        assert h.left.color!=h.color;
        h.left.color=!h.left.color;
        h.right.color=!h.right.color;
        h.color=!h.color;
    }
    public void insert(Key key,Value value)
    {
        if (root==null)
        {
            root=new Node(key,value,false);
            return;
        }
        root=insert(root,key,value);
        root.color=false;                                           //Root must have black link parent
    }
    private Node insert(Node node,Key key,Value value)
    {
        if (node==null)
        return new Node(key,value,true);
        int comp=key.compareTo(node.k);
        if (comp == 0)                                              //Just update the value
        node.v=value;
        else if (comp<0)
        node.left=insert(node.left,key,value);
        else
        node.right=insert(node.right,key,value);

        node=balance(node);

        return node;
    }

    private Node balance(Node h)
    {
        //PS - The below order is very important!!  rotateLeft, then rotateRight, then flipColors (also else-if SHOULD NOT be used)
        assert h!=null;
        if (isRed(h.right))                                            h=rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))                       h=rotateRight(h);
        if (isRed(h.left) && isRed(h.right))                           flipColors(h);
        return h;  
    }
    public void print(Node n)
    {
        if (n==null) return;
        print(n.left);
        System.out.println(n.k+"    "+n.v);
        print(n.right);
    }
    public static void main(String[] args)
    {
        RBTree<Character,String> obj=new RBTree<>();
        obj.insert('z',"Assass");
        obj.insert('e',"Big Foot");
        obj.insert('q',"Kool Aid");
        obj.insert('1',"COsaxasx");
        obj.insert('5',"Pwediwpei");
        obj.insert('l',"Days Gone");
        obj.print(obj.root);
    }
}