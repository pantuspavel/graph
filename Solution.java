class Solution {
  private static class Node {
    int val;
    Node next;

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }

  private static class Graph {
    private Node[] adj;
    
    public Graph(int i) {
      adj = new Node[i];
    }
    
    public Node adjTo(int v) {
      return adj[v];
    }
    
    public int count() {
      return adj.length;
    }
    
    public void adj(int v, int w) {
      if (v >= adj.length || w >= adj.length)
        return;
      
      Node cv = adj[v];
      Node cw = adj[w];
      
      if (adjExists(v, cw) == false) {
        Node last = last(cw);
        Node n = new Node(v, null);
        if (last != null) {
          last(cw).next = n;
        } else {
          adj[w] = n;
        }
      }
      
      if (adjExists(w, cv) == false) {
        Node last = last(cv);
        Node n = new Node(w, null);
        if (last != null) {
          last(cv).next = n;
        } else {
          adj[v] = n;
        }
      }
    }
    
    private boolean adjExists(int val, Node c) {
      if (c == null) return false;
      if (c.val == val) return true;
      return adjExists(val, c.next);
    }
    
    private Node last(Node n) {
      if (n == null) return null;
      if (n.next == null) return n;
      else return last(n.next);
    }
    
    public void printOut() {
      for (int i = 0; i < adj.length; i++) {
        System.out.print(i + ": ");
        Node c = adj[i];
        while(c != null) {
          System.out.print(c.val + ", ");
          c = c.next;
        }
        System.out.println();
      }
      System.out.println("===");
    }
  }
  
  private static class PathsDFS {
    private Graph g;
    private boolean[] visited;
    private int[] cameFrom;
      
    PathsDFS(Graph g, int s) {
      this.g = g;
      visited = new boolean[g.count()];
      for (int i = 0; i < visited.length; i++) {
        visited[i] = false;
      }
      cameFrom = new int[g.count()];
    }
    
    boolean hasPathTo(int v) {
      return visited[v];
    }
    
    Iterable<Integer> pathTo(int v) {
      return null;
    }
    
    public void printout() {
      visited[0] = true;
      printoutEveryElement(0);
    }
    
    private void printoutEveryElement(int v) {
      Node c = g.adjTo(v);
      c = findUnvisitedNode(c);
      if (c != null) {
        cameFrom[c.val] = v;
        visited[c.val] = true;
        System.out.println(v + " -> " + c.val);
        printoutEveryElement(c.val);
      } else if (v != cameFrom[v]) {
        System.out.println(v + " -> " + cameFrom[v]);
        printoutEveryElement(cameFrom[v]);
      }
    }
    
    private Node findUnvisitedNode(Node n) {
      if (n == null) return null;
      if (visited[n.val] == false) return n;
      return findUnvisitedNode(n.next);
    }
  }
  
  private static class PathsBFS {
    private class BFSNode {
      int val;
      BFSNode next;
      
      public BFSNode(int val, BFSNode next) {
        this.val = val;
        this.next = next;
      }
    }
    
    private BFSNode dequeue() {
      if (first == null) return null;
      
      BFSNode c = first;

      if (first.next != null) {
        first = first.next;
      } else {
        first = null;
        last = null;
      }
      
      return c;
    }
    
    private void enqueue(int val) {
      BFSNode n = new BFSNode(val, null);
      
      if (first == null) {
        first = n;
      }
      
      if (last != null) {
        last.next = n;
      }
      
      last = n;
    }
    
    private void printStack(BFSNode n) {
      if (n == null) {
        System.out.println();
        return;
      }
      System.out.print(" " + n.val + " ");
      printStack(n.next);
    }
    
    BFSNode first;
    BFSNode last;
    private Graph g;
    private int[] cameFrom;
    private boolean[] visited;
    
    PathsBFS(Graph g) {
      this.g = g;
      visited = new boolean[g.count()];
      for (int i = 0; i < visited.length; i++) {
        visited[i] = false;
      }
      cameFrom = new int[g.count()];
    }
    
    void printout() {
      enqueue(0);
      visited[0] = true;
      print();
    }
    
    void print() {
      BFSNode n = dequeue();
      if (n == null) return;

      int v = n.val;
      System.out.println(cameFrom[v] + " -> " + v);

      Node c = g.adjTo(v);
      while(c != null) {
        if (visited[c.val] == false) {
          enqueue(c.val);
          visited[c.val] = true;
          cameFrom[c.val] = v;
        }
        printStack(first);
        c = c.next;
      }
     
      print();
    }
  }
  
  public static void main(String[] args) {
    // DFS
    /*
    Graph g = new Graph(13);
    g.adj(0, 6);
    g.adj(0, 5);
    g.adj(0, 1);
    g.adj(0, 2);
    g.adj(6, 4);
    g.adj(4, 5);
    g.adj(5, 3);
    g.adj(5, 0);
    g.adj(3, 5);
    g.adj(3, 4);
    g.adj(7, 8);
    g.adj(8, 7);
    g.adj(9, 10);
    g.adj(10, 9);
    g.adj(9, 11);
    g.adj(9, 12);
    g.adj(12, 9);
    g.adj(12, 11);
    
    g.printOut();
    
    PathsDFS p = new PathsDFS(g, 0);
    p.printout();
    */
    //
    
    Graph g = new Graph(6);
    g.adj(0, 2);
    g.adj(0, 1);
    g.adj(0, 5);
    g.adj(1, 0);
    g.adj(1, 2);
    g.adj(2, 0);
    g.adj(2, 1);
    g.adj(2, 3);
    g.adj(2, 4);
    g.adj(3, 5);
    g.adj(3, 2);
    g.adj(3, 4);
    g.adj(4, 2);
    g.adj(4, 3);
    g.adj(5, 0);
    g.adj(5, 3);
    
    PathsBFS p = new PathsBFS(g);
    p.printout();
  }
}
