package study.week_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN1277 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - N^2: 100_000 => 완탐, bfs, dfs 가능
        - dfs, 시간초과 실패 => 3제곱이 되나
    - 다익스트라로 모두 정렬 후 최소값 반환
        - 입력
            - N,W,M
            - N개의 Point 배열
            - 이어짐을 표현하는 boolean[]
        - 로직
            - 연결된 지점을 connected 로 설정
            - connected 에서 각 노드까지의 길이가 M 이하 길이가 되면 모두 연결하여 그래프로 만들기
            - 다익스트라를 사용하여 connected 에서 N 까지 최소 경로 구하기
            - connected 내에서 N 까지의 거리가 최소인 값 출력
     */

    static class Node implements Comparable<Node>{
        int node;
        double dWeight;

        public Node(int node, double dWeight) {
            this.node = node;
            this.dWeight = dWeight;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dWeight, o.dWeight);
        }
    }
    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, W;
    static double M;
    static boolean[][] connected;
    static double minLength;
    static Point[] points;
    static ArrayList<Node>[] adjList;
    static double[] dist;
    static double INF = Double.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(dijkstra());
    }

    private static void init() throws IOException {

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(bf.readLine());
        points = new Point[N+1];
        connected = new boolean[N+1][N+1];
        adjList = new ArrayList[N+1];
        dist = new double[N+1];
        Arrays.fill(dist, INF);
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connected[from][to] = true;
            connected[to][from] = true;
        }
        minLength = Double.MAX_VALUE;
        makeGraph();
    }

    private static int dijkstra() {
        int start = 1;

        PriorityQueue<Node> pq = new PriorityQueue<>(); // 다익스트라 - PQ
        boolean[] selected = new boolean[N+1];
        pq.offer(new Node(start,0)); // 시작 지점
        dist[start] = 0; // 시작 지점 최소 거리 0

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(selected[current.node]) continue;
            selected[current.node] = true; // 현재 지점만 방문 처리
            for(Node next : adjList[current.node]){
                if(selected[next.node]) continue;
                if(connected[current.node][next.node]) {
                    dist[next.node] = 0;
                    pq.add(new Node(next.node, current.dWeight));
                    break;
                }
                if(dist[next.node] > dist[current.node] + next.dWeight){
                    dist[next.node] = dist[current.node] +next.dWeight;
                    pq.add(new Node(next.node, dist[next.node]));
                }
            }
        }
        return (int)(dist[N]*1000);
    }

    private static void makeGraph() {

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if(i==j) continue;
                double distance = Math.sqrt(Math.pow(points[i].x-points[j].x,2)+Math.pow(points[i].y-points[j].y,2));
                if(distance <= M){
                    adjList[i].add(new Node(j, distance));
                }
            }
        }
    }

}