import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class ST<Key extends Comparable<? super Key>, Value>   //Symbol Table using Binary Search Tree
{
    private class Node
    {
        Key k;
        Value v;
        Node left;
        Node right;
        int size;
        Node(Key key,Value value)
        {
            k=key;
            v=value;
            left=null;
            right=null;
            size=1;
        }
    }
    private Node root;
    public ST() {
        root=null;
    }
    private boolean less(Key k1,Key k2)
    {
        return k1.compareTo(k2) <0;
    }
    private void updateSize(Node n)
    {
        if (n==null)  return;
        if (n.left==null)
        {
            if (n.right==null)
            n.size=1;
            else
            n.size=1+n.right.size;
        }
        else
        {
            if (n.right==null)
            n.size=1+n.left.size;
            else
            n.size=1+n.left.size+n.right.size;
        }
    }
    public void put(Key key, Value value)
    {
        root=put(root,key,value);
    }
    private Node put(Node node,Key key,Value value)
    {
        if (node==null) return new Node(key,value);
        if (node.k.equals(key))
        node.v=value;
        else if (this.less(key,node.k))
        node.left=put(node.left,key,value);
        else
        node.right=put(node.right,key,value);
        updateSize(node);
        return node;
    }
    public Value get(Key key)
    {
        Node temp=root;
        while (true)
        {
            if (temp==null)    return null;
            else if (temp.k.equals(key))  return temp.v;
            else if (this.less(key,temp.k))   temp=temp.left;
            else temp=temp.right;
        }
    }
    public boolean contains(Key key)
    {
        Node temp=root;
        while (true)
        {
            if (temp==null)    return false;
            else if (temp.k.equals(key))  return true;
            else if (this.less(key,temp.k))   temp=temp.left;
            else temp=temp.right;
        }
    }
    public int size()
    {
        return root.size;
    }
    public int rank(Key key)       //How many entries are less than key.
    {
        return rank(root,key);
    }
    private int rank(Node n,Key key)
    {
        if (n==null) return 0;
        int comp=key.compareTo(n.k);
        if (comp==0)   //All nodes to the left are less than key, all to the right are larger
        {
            if (n.left==null) return 0;
            return n.left.size;
        }
        else if (comp < 0)
        {
            return rank(n.left,key);
        }
        else   //Node and left nodes are smaller. We then check the node larger than node
        {
            if (n.left==null) return 1+rank(n.right,key);
            return 1+n.left.size+rank(n.right,key);
        }
    }
    public Key floor(Key key)
    {
        return floor(root,key);   
    }
    private Key floor(Node node, Key key)
    {
        if (node==null)
        return null;
        else if (node.k.equals(key))
        return node.k;
        else if (this.less(key,node.k))
        return floor(node.left,key);
        else
        {
            Key t=floor(node.right,key);
            if (t==null)
            return node.k;
            else return t;
        }
    }
    public Key ceiling(Key key)
    {
        return ceiling(root,key);
    }
    private Key ceiling(Node node,Key key)
    {
        if (node==null)
        return null;
        else if (node.k.equals(key))
        return node.k;
        else if (this.less(key,node.k))
        {
            Key t=ceiling(node.left,key);
            if (t==null)
            return node.k;
            return t;
        }
        else
        return ceiling(node.right, key);
    }
    public Iterable<Key> keysInorder()  //Returns keys in sorted order
    {
        Queue<Key> q=new LinkedList<>();
        inorder(q,root);
        return q;
    }
    private void inorder(Queue<Key> q,Node n)
    {
        if (n==null)
        return;
        inorder(q, n.left);
        q.add(n.k);
        inorder(q, n.right);
    }
    public Iterable<Key> keysLevelorder()
    {
        Queue<Key> q=new LinkedList<>();
        if (root==null) return q;
        q.add(root.k);
        Queue<Node> qn=new LinkedList<>();
        qn.add(root);
        while(qn.size() > 0)
        {
            Queue<Node> qnCopy=new LinkedList<Node>(qn);
            for (Node i:qnCopy)
            {
                if (i.left!=null)
                {
                    q.add(i.left.k);
                    qn.add(i.left);
                }
                if (i.right!=null)
                {
                    q.add(i.right.k);
                    qn.add(i.right);
                }
            }
            for (int i=0;i<qnCopy.size();i++)
            qn.remove();
        }
        return q;
    }
    public Key min()
    {
        Node n=root;
        while (n.left!=null)
        n=n.left;
        return n.k;
    }
    public Key max()
    {
        Node n=root;
        while (n.right!=null)
        n=n.right;
        return n.k;
    }
    private Node deleteMin(Node n)
    {
        if (n.left==null) return n.right;
        n.left=deleteMin(n.left);
        updateSize(n);
        return n;
    }
    private Node min(final Node n)
    {
        Node temp=n;
        while (temp.left!=null)
        temp=temp.left;
        return temp;
    }
    private Node deleteMax(Node n)
    {
        if (n==null) return null;
        if (n.right.right==null)         //n.left= min element
        {
            n.right=n.right.left;
        }
        else
        n.right=deleteMax(n.right);
        updateSize(n);
        return n;
    }
    private Node max(final Node n)
    {
        Node temp=n;
        while (temp.right!=null)
        temp=temp.right;
        return temp;
    }
    public void delete(Key key)
    {
       root=delete(root,key);
    }
    private Node delete(Node n,Key key)
    {
        if (n==null) return null;
        int comp=key.compareTo(n.k);
        if (comp<0)
        n.left=delete(n.left, key);
        else if (comp>0)
        n.right=delete(n.right,key);
        else                //DELETE THIS
        {
            if (n.left==null && n.right==null)
            {
                return null;         //The preceding node will now have a null child
            }
            else if (n.left==null) //One child
            return n.right;
            else if (n.right==null)
            return n.left;

            //Two children Case
            Random r=new Random();
            int coin=r.nextInt(2);
            if (coin==0)   //Left
            {
                Node x=max(n.left);
                n.left=deleteMax(n.left);
                x.left=n.left;
                x.right=n.right;
                n=x;
            }
            else            //Right
            {
                Node x=min(n.right);
                n.right=deleteMin(n.right);
                x.left=n.left;
                x.right=n.right;
                n=x;
            }
        }
        updateSize(n);
        return n;
    }
}