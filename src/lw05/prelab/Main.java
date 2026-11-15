package lw05.prelab;

import java.io.InputStream;
import java.util.*;

public class Main {

    static int size;
    static String[] names;
    static int[][] graph;

    public static void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    public static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }

        return order;
    }

    static List<Integer> dfsOrder = new ArrayList<>();

    public static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder.add(current);

        for (int i = 0; i < size; i++) {
            if (graph[current][i] != 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    public static int findTheNextNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int nextNode = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                nextNode = i;
            }
        }

        return nextNode;
    }

    public static void route(int[] prev, int destination) {
        if (prev[destination] != -1) {
            route(prev, prev[destination]);
            System.out.print(" -> ");
        }

        System.out.print(names[destination]);
    }

    public static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        dist[start] = 0;

        for (int i = 0; i < size; i++) {
            int current = findTheNextNode(dist, visited);

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (int j = 0; j < size; j++) {
                if (graph[current][j] != 0 && !visited[j]) {
                    int newDist = dist[current] + graph[current][j];

                    if (newDist < dist[j]) {
                        dist[j] = newDist;
                        prev[j] = current;
                    }
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");

        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from "
                + names[start] + " to " + names[destination] + " ===");

        route(prev, destination);
        System.out.println();

        System.out.println("Total time: " + dist[destination] + " min");
    }
        public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        size = sc.nextInt();
        names = new String[size];

        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }

        graph = new int[size][size];

        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            addEdge(u, v, weight);
        }

        int start = sc.nextInt();
        int destination = sc.nextInt();
        sc.close();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        System.out.println("=== DFS from " + names[start] + " ===");
        dfsOrder.clear();
        dfs(start, new HashSet<>());
        printPath(dfsOrder);

        dijkstraAlg(start, destination);
    }
} 