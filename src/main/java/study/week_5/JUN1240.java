package study.week_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN1240 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    static int N, M;
    static ArrayList<Node>[] adjList;
    public static void main(String[] args) throws IOException {

        init();
        findDistance();
    }

    private static void findDistance() throws IOException {

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            dfs(from, to, 0, new boolean[N+1]);
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int cur, int to, int dist, boolean[] visited) throws IOException {
        if(cur == to){
            bw.write(dist+"\n");
            return;
        }

        visited[cur] = true;
        for(Node next : adjList[cur]){
            if(!visited[next.node]){
                dfs(next.node, to, dist+next.weight, visited);
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
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }
    }
}
