package study.week_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN15681 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 무방향 => 조회할 때 방문 처리 필요
    - 조건 확인 => 기본 bfs 로 풀면 시간 및 메모리 초과 발생
    - dfs 사용해서 tree 그리고, tree 그리는 중에 DP로 subtree 개수 저장
     */
    static int N, R, Q;
    static ArrayList<Integer>[] temp;
    static ArrayList<Integer>[] trees;
    static int[] dp;
    public static void main(String[] args) throws IOException {

        init();
        findSubTree();

    }

    private static void findSubTree() throws IOException {
        for (int i = 0; i < Q; i++) {
            int start = Integer.parseInt(bf.readLine());
            bw.write(dp[start]+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        temp = new ArrayList[N+1];
        trees = new ArrayList[N+1];
        dp = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            dp[i] = 1;
            temp[i] = new ArrayList<>();
            trees[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            temp[parent].add(child);
            temp[child].add(parent);
        }
        boolean[] visited = new boolean[N+1];
        visited[R] = true;
        makeTree(R, R, visited);
    }

    private static void makeTree(int cNode, int bNode, boolean[] visited) {

        for(int next : temp[cNode]){
            if(!visited[next]){
                visited[next] = true;
                trees[cNode].add(next);
                makeTree(next, cNode, visited);
                dp[cNode] += dp[next];
            }
        }
    }
}