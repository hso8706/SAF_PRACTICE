package DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN1260 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    # 초기화
    - N,M,V 초기화
    - ArrayList[] 을 통해서 양방향 그래프 초기화 => 양방향 그래프
    - 각 리스트는 오름차순으로 정렬 => 숫자가 작은 순부터 탐색

    # BFS
    - 방문 배열 초기화
    - bfs 진행

    # DFS
    - 방문 배열 초기화
    - dfs 진행
     */
    static int N, M, V;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {

        // 초기화
        init();
        // dfs 탐색 로직
        dfs(V, new boolean[N+1]);
        bw.write("\n");
        // bfs 탐색 로직
        bfs(V);
        bw.flush();
        bw.close();
    }

    private static void dfs(int v, boolean[] visited) throws IOException {
        visited[v] = true;
        bw.write(v+ " ");
        for (int next : adjList[v]){
            if(!visited[next]){
                dfs(next, visited);
            }
        }
    }

    private static void bfs(int v) throws IOException {
        boolean[] visited = new boolean[N+1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(v);
        visited[v] = true;

        while(!deque.isEmpty()){
            int nv = deque.poll();
            bw.write(nv + " ");
            for (int next : adjList[nv]){
                if(!visited[next]){
                    visited[next] = true;
                    deque.offer(next);
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }
    }

}
