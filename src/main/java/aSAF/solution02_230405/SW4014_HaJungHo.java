package aSAF.solution02_230405;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW4014_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 활주로 건설
    - 높이가 다른 절벽 지형 map (N x N)
    - 경사로 : 높이 = 1, 길이 = x;
    - 활주로 : 경사로를 포함하여 한 줄이 절벽이 아니게 된 경우
    - 활주로 설치가 가능한 모든 경우의 수 출력
     */
    static int T, N, X; // T: tc, N: map, X: 활주로 길이
    static int[][] map;
    static int maxHeight;
    static int count;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#"+tc+" ");
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            maxHeight = Integer.MIN_VALUE;
            map = new int[N][N];
            count = 0;

            fillTheMap();
            for (int i = 0; i < N; i++) {
                count += isPossibleRow(i);
                count += isPossibleCol(i);
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int isPossibleCol(int stCol) {
        maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxHeight = Math.max(maxHeight, map[i][stCol]);
        }

        int cnt = 1;
        if (map[N-1][stCol] != maxHeight && map[N-1][stCol]!=map[N-2][stCol]) return 0;
        for (int i = 0; i < N-1; i++) {
            if (map[i][stCol] == maxHeight) continue;
            if(map[i+1][stCol] == map[i][stCol]) cnt++;
            else {
                if (cnt < X) return 0;
                cnt = 1;
            }
//            if(i==N-2 && cnt<X) return 0;
        }

        if(cnt >= X) return 1;
        else return 0;
    }

    private static int isPossibleRow(int stRow) {
        maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxHeight = Math.max(maxHeight, map[stRow][i]);
        }

        int cnt = 1;
        if (map[stRow][N-1]!=maxHeight && map[stRow][N-1]!=map[stRow][N-2]) return 0;
        for (int i = 0; i < N-1; i++) {
            if (map[stRow][i] == maxHeight) continue;
            if(map[stRow][i+1] == map[stRow][i]) cnt++;
            else {
                if (cnt < X) return 0;
                cnt = 1;
            }
//            if(i==N-2 && cnt<X) return 0;
        }
        if(cnt >= X) return 1;
        else return 0;
    }

    private static void fillTheMap() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

/*
5번 : 15
10번 : 8
 */
