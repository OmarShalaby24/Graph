import java.util.Scanner;

public class Part1 {

    void part1() {

        Scanner scan = new Scanner(System.in);
        int x, y;
        String[] arr;

        System.out.println("Please enter the number of vertices:");
        String v = scan.nextLine();
        int V = Integer.parseInt(v);

        System.out.println("Please enter the number of edges:");
        String e = scan.nextLine();
        int E = Integer.parseInt(e);

        Graph g = new Graph(V);
        System.out.println("Please enter edges in the form (u v):");

        for (int i = 0; i < E; i++) {
            String link = scan.nextLine();
            arr = link.split(" ");
            x = Integer.parseInt(arr[0]);
            y = Integer.parseInt(arr[1]);
            g.addEdge(x, y);
        }

        System.out.println("Please enter starting vertex:");
        String s = scan.nextLine();
        int S = Integer.parseInt(s);
        System.out.println("Please enter value K:");
        String k = scan.nextLine();
        int K = Integer.parseInt(k);
        double start = System.nanoTime();
        g.BFS(S, V, K);
        double end = System.nanoTime();
        double time = end - start;
        System.out.println("Time Taken = " + time / 1000000 + " milliseconds");

    }
}
