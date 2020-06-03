import java.util.Comparator;
import java.util.LinkedList;
import java.util.Deque;
import edu.princeton.cs.algs4.MinPQ;
public class Solver {

    private int movesSolution;
    private SearchNode solution;

    private class SearchNode
    {
        Board b;
        int numberOfMoves;
        SearchNode prev;
        int manpriority;
        SearchNode(Board obj,int moves,SearchNode sn)
        {
            b=obj;
            numberOfMoves=moves;
            prev=sn;
            manpriority=numberOfMoves+b.manhattan();
        }
    }
    private class ManPriority implements Comparator<SearchNode>  //Manhattan Priority Queue
    {
        public int compare(SearchNode s1,SearchNode s2)
        {
            if (s1.manpriority==s2.manpriority) return 0;
            else if (s1.manpriority>s2.manpriority) return 1;
            else return -1;
        }
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if (initial==null)   throw new IllegalArgumentException();

        MinPQ<SearchNode> org;
        MinPQ<SearchNode> twn;
        org=new MinPQ<>(new ManPriority());   //org - original board
        twn=new MinPQ<>(new ManPriority());   //twin board with one switched element
        SearchNode first=new SearchNode(initial,0,null);
        SearchNode firstTwin=new SearchNode(initial.twin(),0, null);
        org.insert(first);
        twn.insert(firstTwin);
        solution=null;

        boolean orgOrTwn=true;
        boolean b=true;
        while (true)
        {
            if (b)
            {
                SearchNode sn = org.delMin();
                if (sn.b.isGoal())
                {
                    solution=sn;
                    break;
                }
                Iterable<Board> q=sn.b.neighbors();
                for (Board iter:q)
                {
                    if (sn.prev==null || !iter.equals(sn.prev.b))
                    { 
                        SearchNode s=new SearchNode(iter, sn.numberOfMoves+1, sn);
                        org.insert(s);
                    }
                }
            }
            else
            {
                SearchNode sn = twn.delMin();
                if (sn.b.isGoal())
                {
                    orgOrTwn=false;
                    break;
                }
                Iterable<Board> q=sn.b.neighbors();
                for (Board iter:q)
                {
                    if (sn.prev==null || !iter.equals(sn.prev.b))
                    { 
                        SearchNode s=new SearchNode(iter, sn.numberOfMoves+1, sn);
                        twn.insert(s);
                    }
                }
            }
            b=!b;
        }

        if (!orgOrTwn) movesSolution=-1;  //No solution
        else
        {
            movesSolution=solution.numberOfMoves;
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable()
    {
        return (movesSolution!=-1);
    }

    // min number of moves to solve initial board
    public int moves()
    {
        return movesSolution;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution()
    {
        if (!isSolvable()) return null;
        Deque<Board> q=new LinkedList<>();
        q.addFirst(solution.b);
        SearchNode s=solution.prev;
        while (s!=null)
        {
            q.addFirst(s.b);
            s=s.prev;
        }
        return q;
    }

    // test client (see below) 
    public static void main(String[] args)
    {
        int[][] arr={{5,8,7},{1,4,6},{3,0,2}};
        Board bor=new Board(arr);
        Solver sol=new Solver(bor);
        System.out.println(sol.isSolvable());
        System.out.println(sol.moves());
        Iterable<Board> sl=sol.solution();
        for (Board iter:sl)
        {
            System.out.println(iter.toString());
        }
    }

}