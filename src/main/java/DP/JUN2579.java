package DP;

import java.io.*;
import java.util.*;
public class JUN2579 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] dp;
    static int n;
    static int[] steps;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(bf.readLine());
        steps = new int[301];
        for (int i = 1; i < n+1; i++) {
            steps[i] = Integer.parseInt(bf.readLine());
        }
        dp = new int[301];
        dp[1] = steps[1];
        dp[2] = steps[1] + steps[2];
        dp[3] = Math.max(steps[1] + steps[3], steps[2] + steps[3]);
        //마지막 올라가는 계단 수를 한 칸, 혹은 두 칸 내에서 선택하게 만듬
        //Max({마지막 한 칸 전}, {마지막 두 칸 전}) + 마지막 칸
        for (int i = 4; i < 301; i++) {
            dp[i] = Math.max(dp[i-3] + steps[i-1], dp[i-2]) + steps[i];
        }
        bw.write(dp[n]+"");
        bw.flush();
        bw.close();

    }

}
