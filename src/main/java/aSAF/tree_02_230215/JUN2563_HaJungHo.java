package aSAF.tree_02_230215;

import java.io.*;
import java.util.StringTokenizer;

public class JUN2563_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N;
    static int[][] board = new int[100][100];
    static boolean[][] isSelected = new boolean[100][100];
    static int cnt;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for (int j = y; j < y+10; j++) {
                for (int k = x; k < x + 10; k++) {
                    if(!isSelected[j][k]){
                        board[j][k] = 1;
                        isSelected[j][k] = true;
                    }
                }
            }
        }
        for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                if(board[j][k] == 1) cnt++;
            }
        }
        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
    }
}
