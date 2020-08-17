import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }
        size = n;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkValid(row, col);

        opened[row - 1][col - 1] = true;
        if (row == 1) {
            qf.union(getQFIndex(row, col), top);
        }
        if (row == size) {
            qf.union(getQFIndex(row, col), bottom);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row - 1, col));
        }
        if (row < size && isOpen(row + 1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row + 1, col));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (0 < row && row <= size && 0 < col && col <= size) {
            return qf.find(top) == qf.find(getQFIndex(row, col));
            // return qf.connected(top, getQFIndex(row , col));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return -1;
    }

    // does the system percolate?
    public boolean percolates() {
        // return qf.connected(top, bottom);
        return qf.find(top) == qf.find(bottom);
    }

    private void checkValid(int row, int col) {
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("row or col out of bounds");
        }

        // return i > 0
        // && j > 0
        // && i <= n
        // && j <= n;
    }

    private int getQFIndex(int i, int j) {
        return size * (i - 1) + j;
    }


    // test client (optional)
    public static void main(String[] args) {

    }

}