package DP;

import java.io.*;
import java.util.*;

public class JUN9095 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T, n;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        dp = new int[12];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < 12; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(bf.readLine());
            bw.write(dp[n]+"\n");
        }
        bw.flush();
        bw.close();

    }
}
