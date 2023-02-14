package com.tree_01_230214;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN16926_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, rotN;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rotN = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(bf.readLine());
            for (int m = 0; m < M; m++) {
                board[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        // 회전 사각형의 개수 : min(N,M)/2만큼 => 반복문 생성
        // 회전 사각형의 개수 반복문의 변수가 i라면, 회전 사각형의 첫 인덱스는 [i][i]
    }
}
