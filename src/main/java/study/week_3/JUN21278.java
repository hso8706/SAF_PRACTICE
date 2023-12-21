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

public class JUN21278 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 2개 설정 후 개별 다익스트라 (x)
    => 다익스트라로 초기화 후 2개 선별
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
    static int N, M;
    static ArrayList<Node>[] adjList;
    static int[][] dist;
    static int INF = Integer.MAX_VALUE;
    static int[] nums;
    static int[] selected;
    static int A, B, W; //출력값
    public static void main(String[] args) throws IOException {

        //초기화
        init();
        //조합으로 2개의 숫자 뽑기
        selectTwo(0,1);
        System.out.println(A+" "+B+" "+W);
    }
    //combination
    private static void selectTwo(int cnt, int start) {
        if(cnt==2){
//            System.out.println(selected[0] + ", " + selected[1]);
            //각각의 최고 거리 배열 순회
            int sum = 0; //왕복 거리 누적
            for (int i = 1; i < N+1; i++) {
                if(i == selected[0] && i == selected[1]) continue; //뽑힌 지점
                int minValue = Math.min(dist[selected[0]][i], dist[selected[1]][i]); //뽑힌 지점에서 건물까지 거리 중 가까운 것
                sum += minValue*2; //왕복 거리 누적
            }
            if(sum < W){ //누적 거리가 W보다 작은 경우, 같은 경우를 제외하여 조건 충족 ==> sum==0인 경우를 제외했었는데 그러면 18/19 됨
                //뽑힌 순서 => 자동으로 작은 순
                A = selected[0];
                B = selected[1];
                W = sum;
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            selected[cnt] = nums[i];
            selectTwo(cnt+1, i+1);
        }
    }

    private static void dijkstra(int start, int idx) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 다익스트라 - PQ
        boolean[] visited = new boolean[N+1];
        pq.offer(new Node(start,0)); // 시작 지점
        dist[idx][start] = 0; // 시작 지점 최소 거리 0

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(visited[current.node]) continue; // 방문 처리 된 곳 패스
            visited[current.node] = true; // 현재 지점만 방문 처리

            for(Node next : adjList[current.node]){
                if(!visited[next.node] && dist[idx][next.node] > dist[idx][current.node] + next.weight){
                    dist[idx][next.node] = dist[idx][current.node] + next.weight;
                    pq.add(new Node(next.node, dist[idx][next.node]));
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, 1));
            adjList[b].add(new Node(a, 1));
        }

        nums = new int[N+1];
        selected = new int[2];
        for (int i = 1; i < N+1; i++) {
            nums[i] = i;
        }
        A=B=0;//지점
        W= Integer.MAX_VALUE;//최소 거리

        //추가 dist 초기화
        dist = new int[N+1][N+1]; //뽑은 2개의 점 각각의 최소 거리
        for (int i = 1; i < N+1; i++) {
            Arrays.fill(dist[i], INF);
            dijkstra(i, i);
//            System.out.println(Arrays.toString(dist[i]));
        }
    }

}
