package study.week_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN1956 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    # 로직
    - 다익스트라를 이용하여 표 제작
    - 표 제작 시, if == start 과 if != visited[next] 경우로 나눔
        - visited[current] = true
        - if!=visited[next] 일때만 다익스트라 비교 작업+pq 작업 실행
        - if==start 인 경우를 최우선 순위
            - dist == 0일 떄와, 아닐 때 구분
            - 0이면: 걍 넣고 최소값 갱신
            - 아니면: 다익스트라 비교 작업+최소값 갱신 작업
    # 오답
    ## 1
    - 방문 처리를 next가 아닌 current로 해야함 => 이유 고민
    ## 2
    - 사이크링 없는 경우 까먹음
     */
    static class Node implements Comparable<Node> {
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
    static int V, E;
    static ArrayList<Node>[] adjList;
    static int[][] dist;
    static int result;
    public static void main(String[] args) throws IOException {

        init();
        makeMatrix();
        output();
    }

    private static void output() throws IOException {
        if(result == Integer.MAX_VALUE) bw.write(-1+"");
        else bw.write(result+"");
        bw.flush();
        bw.close();
    }

    private static void makeMatrix() {
        for (int i = 1; i < V+1; i++) {
            dijkstra(i);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        boolean[] visited = new boolean[V+1];
        dist[start][start] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(visited[current.node]) continue;
            visited[current.node] = true; //일방 통행

            for(Node next: adjList[current.node]){
                //1: == start
                if(next.node == start){
                    if(dist[start][next.node] == 0){
                        dist[start][next.node] = dist[start][current.node] + next.weight;//비교 작업
                        result = Math.min(result, dist[start][next.node]);//갱신 작업
                    }
                    else {
                        if(dist[start][next.node] > dist[start][current.node] + next.weight){
                            dist[start][next.node] = dist[start][current.node] + next.weight;//비교 작업
                            result = Math.min(result, dist[start][next.node]);//갱신 작업
                        }
                    }
                }
                //2: != visited
                if(dist[start][next.node] > dist[start][current.node] + next.weight){
                    dist[start][next.node] = dist[start][current.node] + next.weight;//갱신 작업
                    pq.add(new Node(next.node, dist[start][next.node])); // pq 작업
                }
            }
        }

    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[V+1];
        for (int i = 1; i < V+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight)); //일방 통행
        }

        dist = new int[V+1][V+1];
        for (int i = 1; i < V+1; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        result = Integer.MAX_VALUE;
    }
}
