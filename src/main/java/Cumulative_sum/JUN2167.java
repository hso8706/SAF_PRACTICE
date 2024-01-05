package Cumulative_sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2167 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M, K;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        init();
        findSum();
    }

    private static void findSum() throws IOException {
        for (int n = 0; n < K; n++) {
            st = new StringTokenizer(bf.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int n1 = i; n1 <= x; n1++) {
                sum += dp[n1][y] - dp[n1][j-1];
            }
            bw.write(sum+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException{

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        dp = new int[N+1][M+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < M+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = map[i][j] + dp[i][j-1];
            }
        }
        K = Integer.parseInt(bf.readLine());
    }
}
