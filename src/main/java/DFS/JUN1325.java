package DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN1325 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M;
    static ArrayList<Integer>[] network;
    static int[] dp;
//    static boolean[] visited;
    static boolean[] isStarter;
    static boolean[] isEnder;
    static int maxValue;
    static ArrayList<Integer> results;
    public static void main(String[] args) throws IOException {

        init();

        findVerifiedComputers();

        output();
    }

    private static void output() throws IOException {
        for (int i = 1; i < N+1; i++) {
            if(isEnder[i] && dp[i] == maxValue){
                bw.write(i+" ");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void findVerifiedComputers() throws IOException {
        for (int i = 1; i < N+1; i++) {//오름차순
            dp = new int[N+1];
            if(isStarter[i]){
//                int result = dfs(i);
                dfs(i,0);
            }
        }
    }

    private static void dfs(int i, int cnt) {
        if(dp[i] != 0) return;
        dp[i] = cnt;
        for (int n : network[i]) {
            dfs(n, cnt+1);
        }
        maxValue = Math.max(maxValue, dp[i]);
    }

    private static void init() throws IOException {

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isStarter = new boolean[N+1];
        isEnder = new boolean[N+1];
        network = new ArrayList[N+1];
//        visited = new boolean[N+1];
        maxValue = 0;
        for (int i = 1; i < N+1; i++) {
            network[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int giver = Integer.parseInt(st.nextToken());
            int taker = Integer.parseInt(st.nextToken());
            network[giver].add(taker);
            isStarter[giver] = true;
            isStarter[taker] = false;
            isEnder[giver] = false;
            isEnder[taker] = true;
        }
        results = new ArrayList<>();
    }

}
