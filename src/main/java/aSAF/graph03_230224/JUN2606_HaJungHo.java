package aSAF.graph03_230224;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN2606_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // N: 컴퓨터 수(정점 수), M: 네트워크 수(간선 수)
    static ArrayList<Integer>[] computers;
    static int cnt;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        computers = new ArrayList[N+1]; // 인덱스 1부터 시작
        for (int i = 0; i < N+1; i++) {
            computers[i] = new ArrayList<>();
        }
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            computers[from].add(to);
            computers[to].add(from);
        }

        dfs(1, new boolean[N+1]);
        System.out.println(cnt-1);
    }

    private static void dfs(int current, boolean[] visited) {
        visited[current] = true;
        cnt++;

        for (int vertex : computers[current]) {
            if(!visited[vertex]) {
                dfs(vertex, visited);
            }
        }
    }
}
