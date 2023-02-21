package com.backtTracking_230221;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1992_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[][] picture;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        picture = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < N; j++) {
                picture[i][j] = Integer.parseInt(temp[j]);
            }
        }

        quadTree(0, 0, N);
        bw.flush();
        bw.close();
    }

    private static void quadTree(int x, int y, int size) throws IOException {
        if (canZip(x, y, size)){
            bw.write(picture[x][y] + "");
            return;
        }

        int newSize = size / 2;

        bw.write("(" + "");

        quadTree(x, y, newSize);
        quadTree(x, y + newSize, newSize);
        quadTree(x + newSize, y, newSize);
        quadTree(x + newSize, y + newSize, newSize);

        bw.write(")" + "");
    }

    private static boolean canZip(int x, int y, int size) {
        int start = picture[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (picture[i][j] != start)
                    return false;
            }
        }
        return true;
    }
}
