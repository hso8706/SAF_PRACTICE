package solved;

import java.io.*;
import java.util.StringTokenizer;

public class JUN11053 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 가장 긴 증가하는 부분 수열
     */
    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        dp = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < N; j++) {
            if (dp[j] > max) max = dp[j];
        }
        bw.write(max + "\n");

        bw.flush();
        bw.close();
    }
}