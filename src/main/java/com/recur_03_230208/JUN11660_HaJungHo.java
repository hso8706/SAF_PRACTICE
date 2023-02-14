package com.recur_03_230208;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN11660_HaJungHo {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //입력 1.
        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        //입력 2.
        int M = Integer.parseInt(st.nextToken());
        //입력 3.
        int[][] intArr = new int[N][N];
        for (int i = 0; i <N; i++) {
            intArr[i] = Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        //누적 합 할당 배열
        int[][] sumOfRangeRaw = new int[N][N];
        int[][] sumOfRange = new int[N][N];
        // 가로합
        for (int i = 0; i < N; i++) {
            sumOfRangeRaw[i][0] += intArr[i][0];
            for (int j = 1; j < N; j++) {
                sumOfRangeRaw[i][j] += intArr[i][j] + sumOfRangeRaw[i][j-1];
            }
        }
        // 세로합, 최종합 배열
        for (int j = 0; j < N; j++) {
            sumOfRange[0][j] += sumOfRangeRaw[0][j];
            for (int i = 1; i < N; i++) {
                sumOfRange[i][j] += sumOfRangeRaw[i][j] + sumOfRange[i-1][j];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            if(x1 == 1 && y1 == 1){
                bw.write(sumOfRange[x2-1][y2-1] + "\n");
            }
            else if(x1 == 1 && y1>1){
                bw.write((sumOfRange[x2-1][y2-1] - sumOfRange[x2-1][y1-1-1]) + "\n");
            }
            else if(y1 == 1 && x1 > 1){
                bw.write((sumOfRange[x2-1][y2-1] - sumOfRange[x1-1-1][y2-1]) + "\n");
            }
            else if(x1>1 && y1>1){
                bw.write((sumOfRange[x2-1][y2-1] - sumOfRange[x1-1-1][y2-1] - sumOfRange[x2-1][y1-1-1] + sumOfRange[x1-1-1][y1-1-1]) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
