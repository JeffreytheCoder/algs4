public class UnionFind {

  private int[] id;
  
  public UnionFind(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] += i;
    }
  }

  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }

  public void union(int p, int q) {
    for (int i = 0; i < id.length; i++) {
      if (id[i] == id[p]) id[i] = id[q];
    }
  }

  public int find(int p) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < id.length; i++) {
      if (id[i] == id[p] && i > max) max = i;
    }
    return max;
  }

  public static void main(String[] args) {
    UnionFind unionFind = new UnionFind(10);
    unionFind.union(1, 2);
    unionFind.union(3, 4);
    unionFind.union(4, 5);
    System.out.println(unionFind.connected(3, 5));
    unionFind.union(5, 6);
    System.out.println(unionFind.find(3));
  }
}
