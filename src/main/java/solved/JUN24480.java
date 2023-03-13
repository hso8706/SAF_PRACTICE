package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class JUN24480 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static ArrayList<Integer>[] adjList;
    static int N, M, R; // N: 정점, M: 간선, R: 정점
    static int cnt;
    static int[] output;
    /*
    무향 그래프
     */
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        output = new int[N+1];
        cnt = 1;
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }
        for (int i = 1; i < N+1; i++) {
            adjList[i].sort(Collections.reverseOrder());
        }
        boolean[] visited = new boolean[N+1];
        dfs(R, visited);
        for (int i = 1; i < N+1; i++) {
            bw.write(output[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int current, boolean[] visited) throws IOException {
        visited[current] = true;
        output[current] = cnt;
        cnt++;

        for(int vertex : adjList[current]){
            if(!visited[vertex]) {
                dfs(vertex, visited);
            }
        }
    }
}
