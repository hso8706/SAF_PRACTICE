package solution;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class JUN1260 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, V; //N: 정점 개수, M: 간선 개수, V: 시작 정점
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1]; // 인덱스 1번부터 시작
        for (int i = 1; i < N+1; i++) {
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
            Collections.sort(adjList[i]);
        }

        dfs(V, new boolean[N+1]);
        bw.flush();
        bw.close();
    }

    private static void dfs(int current, boolean[] visited) throws IOException {
        visited[current] = true;
        bw.write(current + " ");

        for (int vertex: adjList[current]){
            if(!visited[vertex]){
                dfs(vertex, visited);
            }
        }
    }
}
