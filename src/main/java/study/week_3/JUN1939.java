package study.week_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1939 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Node {
        int node;
        long weight;

        public Node(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    static ArrayList<Node>[] adjList;
    static long[] dp;
    static int N, M;
    static int A, B;
    static long result;
    public static void main(String[] args) throws IOException {

        init();

        boolean[] visited = new boolean[N+1];
        visited[A] = true;
        for(Node next : adjList[A]){
            visited[next.node] = true;
            dfs(next.node, B, visited, next.weight);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[A]);
    }

    private static long dfs(int current, int end, boolean[] visited, long maxW) {
        if(dp[current] != Long.MIN_VALUE) return dp[current];
        if(current == end){
            return maxW;
        }

        for(Node next: adjList[current]){
            if(!visited[next.node] && maxW <= next.weight){
                visited[next.node] = true;
                dp[current] = Math.max(dp[current], dfs(next.node, end, visited, maxW));
                visited[next.node] = false;
            }
        }
        return maxW;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        dp = new long[N+1];
        Arrays.fill(dp, Long.MIN_VALUE);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }
        st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        result = Long.MIN_VALUE;
    }
}
