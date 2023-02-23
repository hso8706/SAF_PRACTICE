package aSAF.graph02_230223;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1987_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int R, C; //R: 세로, C: 가로
    static int[][] board; //R x C
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < C; j++) {

            }
        }
    }
}
