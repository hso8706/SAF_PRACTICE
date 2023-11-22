package DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN1520 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] dm = new int[]{-1,0,1,0};
    static int[] dn = new int[]{0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();

        System.out.println(dfs(0,0));
    }

    private static int dfs(int m, int n) {
        //memorization, 핵심
        if(dp[m][n] != -1){
            return dp[m][n];
        }

        if(m == M-1 && n == N-1){
            return 1;
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nm = m + dm[i];
            int nn = n + dn[i];
            if(nm<0 || nn<0 || nm>=M || nn>=N) continue;
            if(map[m][n] > map[nm][nn]){
                result += dfs(nm, nn);
            }
        }
        dp[m][n] = result;
        return result;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
    }
}
