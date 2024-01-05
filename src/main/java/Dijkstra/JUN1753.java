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

public class JUN1753 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int V, E, K;
    static ArrayList<Node>[] adjList;
    static int[] shortest;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        init();
        dijkstra();
        output();
    }

    private static void output() throws IOException {
        for (int i = 1; i < V+1; i++) {
            if(shortest[i] == INF) bw.write("INF\n");
            else bw.write(shortest[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 다익스트라 - PQ
        boolean[] visited = new boolean[V+1];
        pq.offer(new Node(K,0)); // 시작 지점
        shortest[K] = 0; // 시작 지점 최소 거리 0

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(visited[current.end]) continue; // 방문 처리 된 곳 패스
            visited[current.end] = true; // 현재 지점만 방문 처리
            //이는 뒤로 다시 돌아올 수 없는 다익스트라의 조건 때문에 성립 가능한 방문 조건

            for(Node next : adjList[current.end]){
                if(shortest[next.end] > shortest[current.end] + next.weight){
                    shortest[next.end] = shortest[current.end] + next.weight;
                    pq.add(new Node(next.end, shortest[next.end]));
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(bf.readLine());
        adjList = new ArrayList[V+1];
        shortest = new int[V+1];
        for (int i = 1; i < V+1; i++) {
            adjList[i] = new ArrayList();
        }
        Arrays.fill(shortest, INF);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, weight));
        }
    }
}
