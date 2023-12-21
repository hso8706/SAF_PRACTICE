package study.week_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN14938 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    # 다익스트라
    - 양방향 통행 가능 => 다익스트라 불가능(?)
    - 다익스트라로 미리 전처리하기
     */
    static class Node implements Comparable<Node>{
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int n, m ,r;
    static ArrayList<Node>[] adjList;
    static int[][] dist; // 시작 지점, 도착 지점
    static int INF = Integer.MAX_VALUE;
    static int[] items;
    static int maxValue;
    public static void main(String[] args) throws IOException {

        init();
        dijkstra(); // 시작-도착 미리 전처리, NlogN
        output(); // 모든 노드를 순회하며 최대 item 개수 갱신, N^2 => 100_000 가능
    }

    private static void output() throws IOException {
        for (int i = 1; i < n+1; i++) {
            int sum = 0;
            for (int j = 1; j < n+1; j++) {
                if(dist[i][j] <= m) sum += items[j];
            }
            maxValue = Math.max(maxValue, sum);
        }
        bw.write(maxValue+"");
        bw.flush();
        bw.close();
    }

    private static void dijkstra() {
        for (int i = 1; i < n+1; i++) { // 모든 시작 지점
            int start = i;

            PriorityQueue<Node> pq = new PriorityQueue<>(); // 다익스트라 - PQ
            boolean[] selected = new boolean[n+1];
            pq.offer(new Node(start,0)); // 시작 지점
            dist[start][start] = 0; // 시작 지점 최소 거리 0

            while(!pq.isEmpty()){
                Node current = pq.poll();
                if(selected[current.node]) continue; // 방문 처리 된 곳 패스
                selected[current.node] = true; // 현재 지점만 방문 처리

                for(Node next : adjList[current.node]){
                    if(!selected[next.node] && dist[start][next.node] > dist[start][current.node] + next.weight){
                        dist[start][next.node] = dist[start][current.node] + next.weight;
                        pq.add(new Node(next.node, dist[start][next.node]));
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[n+1];
        dist = new int[n+1][n+1];
        items = new int[n+1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
            items[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, weight));
            adjList[end].add(new Node(start, weight));
        }
        maxValue = Integer.MIN_VALUE;
    }
}
