package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class JUN1932 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] triangle;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 초기화
        init();
        // 정상을 시작으로 DP 로직 진행
        dp[1][0] = triangle[1][0]; // 초기값 설정
        dynamicProgramming();
        int maxSum = 0;
        // 마지막 행에서 최대값 찾기
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, dp[n][i]);
        }
        System.out.println(maxSum);
    }

    private static void dynamicProgramming() {
        // 다이나믹 프로그래밍으로 최대값 계산
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 대각선 왼쪽 위에서 오는 경우
                if (j - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + triangle[i][j]);
                }
                // 대각선 오른쪽 위에서 오는 경우
                if (j < i - 1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + triangle[i][j]);
                }
            }
        }
    }

    private static void init() throws IOException {
        // 시작 번호: 1
        n = Integer.parseInt(bf.readLine());
        triangle = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
