import java.util.Scanner;

public class Runner {

    public static void main2(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            int N = scanner.nextInt();
            QuickFindUF uf = new QuickFindUF(N);
            while (!StdIn.isEmpty()) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                    StdOut.println(p + " " + q);
                }
            }
        } catch (Exception e) { 
            scanner.close();
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
        // a ctrl + D in macOS
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println("done..");
    }
}