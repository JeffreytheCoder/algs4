import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

  private final double[] thresholds;
  private final int trials;
  
  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    thresholds = new double[trials];
    this.trials = trials;

    // count percolation threshold for random trials
    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      double threshold = 0;
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        if (!percolation.isOpen(row, col)) {
          percolation.open(row, col);
          threshold++;
        }
      }
      thresholds[i] = threshold / (n * n);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(thresholds);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(thresholds);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
  }

  // test client (see below)
  public static void main(String[] args) {
    PercolationStats percolationStats = new PercolationStats(100, 1000);
    StdOut.println("mean                    = " + percolationStats.mean());
    StdOut.println("stddev                  = " + percolationStats.stddev());
    StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ',' + percolationStats.confidenceHi() + ']');
  }
}
