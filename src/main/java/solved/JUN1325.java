package solved;

import java.io.*;
import java.util.*;

public class JUN1325 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 효율적인 해킹
    - 한 번의 해킹으로 여러 개의 컴퓨터 해킹
    - 신뢰 관계 == 연결 방향
        - A가 B를 신뢰 == B에서 A로 진행 가능(단방향)
    - 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 번호를 출력
        - == 경로가 가장 긴 경우

    ### 문제 해결
    1. bfs
    => 실패
        - 최대 경로를 구해야하는데 bfs를 하면 최대 경로를 구할 수 없었음.
        => 이거 잘못된 생각이었음. 최대 경로가 아니라 최대한 많은 인접 접점이 연결되어 있었으면 된거였음
     */
    static int N, M; // N: 접점, M: 간선
    static ArrayList<Integer>[] adjList;
    static int[] maxArr;
    static int maxVertex;
    static boolean[] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1]; // 인덱스 1부터 시작
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        maxArr = new int[N + 1]; // 각 정점에서 시작했을때 가능한 최대 해킹 컴퓨터 수
        Arrays.fill(maxArr, Integer.MIN_VALUE);
        maxVertex = Integer.MIN_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[to].add(from);// 단방향
        }

        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            cnt = 0;
            bfs(i);
            maxArr[i] = cnt;
            maxVertex = Math.max(maxArr[i], maxVertex);
        }

        for (int i = 1; i < N + 1; i++) {
            if (maxArr[i] == maxVertex)
                bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int vertex : adjList[current]) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    queue.offer(vertex);
                    cnt++;
                }
            }
        }
    }

//    private static void dfs(int current) {
//        for(int vertex: adjList[current]){
//            if(!visited[vertex]){
//                visited[vertex] = true;
//                cnt++;
//                dfs(vertex);
//                visited[vertex] = false;
//            }
//        }
//    }
}
