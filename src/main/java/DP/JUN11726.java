package DP;

import java.io.*;
import java.util.*;

public class JUN11726 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] dp;
    static int n;
    public static void main(String[] args) throws IOException {
        dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < 1001; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%10_007;
        }
        n = Integer.parseInt(bf.readLine());
        bw.write(dp[n]+"");
        bw.flush();
        bw.close();
    }

}
