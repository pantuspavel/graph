class Solution {
  private static class Graph {
    private class Node {
      int val;
      Node next;
      
      public Node(int val, Node next) {
        this.val = val;
        this.next = next;
      }
    }
    private Node[] adj;
    
    public Graph(int i) {
      adj = new Node[i];
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
  
  public static void main(String[] args) {   
  }
}
