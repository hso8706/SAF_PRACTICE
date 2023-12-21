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

public class JUN1719 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 가중치==시간
    - 무향 그래프인 듯 함
    - 가장 먼저 들려하는 집하장이 있음
     */
    static class Node implements Comparable<Node>{
        int node;
        int weight;
        int first;

        public Node(int node, int weight, int first) {
            this.node = node;
            this.weight = weight;
            this.first = first;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int n, m;
    static ArrayList<Node>[] adjList;
    static int[] dist; // 시작 지점마다 새로 갱신
    static int INF = Integer.MAX_VALUE;
    static int[][] path; // 출력
    public static void main(String[] args) throws IOException {

        init();
        findPath();
        output();
    }

    private static void output() throws IOException {
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(path[i][j] == 0) bw.write("- ");
                else bw.write(path[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void findPath() {
        for (int i = 1; i < n+1; i++) { //시작점 순회
            dist = new int[n+1];
            Arrays.fill(dist, INF);
            dijkstra(i);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        boolean[] selected = new boolean[n+1];
        dist[start] = 0;
        int cnt = 0;// 첫 번째 지점을 체크하기 위해 cnt 활용

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(selected[current.node]) continue;
            selected[current.node] = true;

            for(Node next : adjList[current.node]){
                if(!selected[next.node] && dist[next.node] > dist[current.node] + next.weight){
                    cnt++;
                    dist[next.node] = dist[current.node] + next.weight;
                    // depth == 1 의 시작 지점 저장하는 로직
                    if(current.node==start && cnt <= adjList[current.node].size()) current.first = next.node;
                    pq.offer(new Node(next.node, dist[next.node], current.first));
                    path[start][next.node] = current.first;
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, weight, 0));
            adjList[end].add(new Node(start, weight, 0));
        }
        path = new int[n+1][n+1];
    }
}
