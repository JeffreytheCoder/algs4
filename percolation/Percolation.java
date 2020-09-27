import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final WeightedQuickUnionUF qf;
  private boolean[][] opened;
  private static final int top = 0;
  private final int bottom;
  private final int size;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException();
    size = n;
    qf = new WeightedQuickUnionUF(size * size + 2);
    opened = new boolean[size][size];
    bottom = size * size + 1;
  }

  // convert col & row to qf index
  private int getQFIndex(int row, int col) {
    return size * (row - 1) + col;
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
    opened[row - 1][col - 1] = true;

    // points top/bottom row to a top/bottom virtual node
    if (row == 1) {
      qf.union(getQFIndex(row, col), top);
    }
    if (row == size) {
      qf.union(getQFIndex(row, col), bottom);
    }

    // unions with nearby block
    if (row > 1 && isOpen(row - 1, col)) {
      qf.union(getQFIndex(row, col), getQFIndex(row - 1, col));
    }
    if (row < size && isOpen(row + 1, col)) {
      qf.union(getQFIndex(row, col), getQFIndex(row + 1, col));
    }
    if (col > 1 && isOpen(row, col - 1)) {
      qf.union(getQFIndex(row, col), getQFIndex(row, col - 1));
    }
    if (col < size && isOpen(row, col + 1)) {
      qf.union(getQFIndex(row, col), getQFIndex(row, col + 1));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
    return opened[row - 1][col - 1];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
    return qf.find(top) == qf.find(getQFIndex(row, col));
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    int count = 0;
    for (boolean[] bs : opened) {
      for (boolean b : bs) {
        if (b) count++;
      }
    }
    return count;
  }

  // does the system percolate?
  public boolean percolates() {
    return qf.find(top) == qf.find(bottom);
  }

  // test client (optional)
  public static void main(String[] args) {
  }
}
