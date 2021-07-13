import java.util.*;

public class Part3 {

    int cities;
    int routes;
    int M, source, destination;
    int transit = 0;
    Vertex target = null;

    int[] lowestCost;
    LinkedList<Vertex>[] graph;
    ArrayList<Integer> visited;
    PriorityQueue<Vertex> priorityQueue;

    public void createGraph() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter amount M:");
        M = scan.nextInt();

        System.out.println("Please enter number of cities:");
        cities = scan.nextInt();

        graph = new LinkedList[cities];

        System.out.println("Please enter number of routes:");
        routes = scan.nextInt();

        System.out.println("Please enter source, destination, time and cost for each route:");

        for (int i = 0; i < routes; i++) {
            source = scan.nextInt() - 1;
            if (graph[source] == null) {
                graph[source] = new LinkedList<>();
            }
            graph[source].add(new Vertex(scan.nextInt() - 1, scan.nextInt(), scan.nextInt()));
        }

        System.out.println("Please enter source city:");
        source = scan.nextInt() - 1;

        System.out.println("Please enter destination city:");
        destination = scan.nextInt() - 1;

        double start = System.nanoTime();
        Dijkstra();
        double end = System.nanoTime();
        double time = end - start;

        printPath(target);

        System.out.println("Time Taken = " + time / 1000000 + " milliseconds");


    }

    public void Dijkstra() {

        lowestCost = new int[cities];
        visited = new ArrayList<>();
        priorityQueue = new PriorityQueue<>(comparator);

        for (int i = 0; i < cities; i++) {
            lowestCost[i] = Integer.MAX_VALUE;
        }

        lowestCost[source] = 0;
        priorityQueue.add(new Vertex(source, 0, 0, null));

        while (visited.size() != cities && !priorityQueue.isEmpty() && !visited.contains(destination)) {

            Vertex minCostCity = priorityQueue.remove();
            visited.add(minCostCity.city);

            if (minCostCity.city == destination) {
                target = minCostCity;
            }

            if (graph[minCostCity.city] != null) {
                findNeighbours(minCostCity.city, minCostCity);
            }
        }
    }


    private void findNeighbours(int minCostCity, Vertex parent) {

        for (int i = 0; i < graph[minCostCity].size(); i++) {
            Vertex vertex = graph[minCostCity].get(i);

            if (!visited.contains(vertex.city)) {
                int edgeCost = vertex.cost;
                int relaxedEdge = lowestCost[minCostCity] + edgeCost;

                if (relaxedEdge < lowestCost[vertex.city]) {
                    lowestCost[vertex.city] = relaxedEdge;
                    priorityQueue.add(new Vertex(vertex.city, vertex.time + parent.time, lowestCost[vertex.city], parent));
                }
            }
        }
    }


    public void printPath(Vertex target) {

        if (target == null) {
            System.out.println("No path found!");

        } else if (target.parent != null) {
            transit++;
            printPath(target.parent);
            System.out.print(" --> " + (target.city + 1));

            if (target.city == destination) {
                System.out.println("\nTotal time = " + ((transit - 1) + target.time) + " hours");
                System.out.println("Total cost = " + ((((transit - 1) + target.time) * M) + target.cost) + "$");
            }

        } else {
            System.out.print("The route with the minimum cost is: ");
            System.out.print(target.city + 1);
        }
    }


    public class Vertex {

        int city;
        int time;
        int cost;
        Vertex parent;

        public Vertex(int city, int time, int cost) {
            this.city = city;
            this.time = time;
            this.cost = cost * time;
        }

        public Vertex(int city, int time, int cost, Vertex parent) {
            this.city = city;
            this.time = time;
            this.cost = cost;
            this.parent = parent;
        }

    }

    Comparator<Vertex> comparator = new Comparator<Vertex>() {
        @Override
        public int compare(Vertex cityCost1, Vertex cityCost2) {
            if (cityCost1.cost < cityCost2.cost)
                return -1;
            if (cityCost1.cost > cityCost2.cost)
                return 1;
            return 0;
        }
    };

}