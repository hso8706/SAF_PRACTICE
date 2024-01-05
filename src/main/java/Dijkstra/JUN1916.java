package Dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN1916 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Node implements Comparable<Node> {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight- o.weight;
        }
    }
    static int N, M,A,B;
    static ArrayList<Node>[] adjList;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(dijkstra());;
    }

    private static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(A, 0));
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];
        dist[A] = 0;

        while(!pq.isEmpty()){
            Node c = pq.poll();
            if(visited[c.node]) continue;
            visited[c.node] = true;

            for(Node next: adjList[c.node]){
                if(!visited[next.node] && dist[next.node] > dist[c.node] + next.weight){
                    dist[next.node] = dist[c.node] + next.weight;
                    pq.add(new Node(next.node, dist[next.node]));
                }
            }
        }
        return dist[B];

    }

    private static void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        adjList = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, weight));
        }
        st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }
}
