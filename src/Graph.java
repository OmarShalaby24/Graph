import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    LinkedList<Integer>[] mainList;
    int V;

    public Graph(int n) {
        V = n;
        mainList = new LinkedList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            mainList[i] = new LinkedList<>();
        }
    }

    void addEdge(int u, int v) {
        mainList[u].add(v);
        mainList[v].add(u);
    }


    void BFS(int S, int V, int K) {
        boolean[] isVisited = new boolean[V + 1];

        int tmpK = K;
        int tmpS = S;

        Queue<Integer> queue = new LinkedList<>();


        isVisited[S] = true;
        queue.add(S);
        queue.add(0);

        int count = 0;

        while (queue.size() != 0 && K != 0) {
            S = queue.poll();
            if (S != 0) {
                Iterator<Integer> i = mainList[S].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!isVisited[n]) {
                        isVisited[n] = true;
                        queue.add(n);
                    }
                }
            } else {
                if (K - 1 != 0)
                    queue.add(0);
                else
                    count = queue.size();
                K--;
            }
        }

        S = tmpS;
        K = tmpK;
        System.out.println("There are " + count + " people with " + K + " connections away starting from " + S);
    }
}
