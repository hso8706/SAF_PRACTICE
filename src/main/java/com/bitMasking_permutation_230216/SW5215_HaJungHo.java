package com.bitMasking_permutation_230216;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SW5215_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T, N, L; // T: 테스트 케이스, N: 재료의 수, L: 제한 칼로리
    static int[][] inputScoreKcal; // 0: 맛 점수, 1: 칼로리
    static int[][] subSet;
    static int subSetIndex;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#" + tc + " ");
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            inputScoreKcal = new int[N][2]; // 재료 개수 x (맛, 칼로리)
            subSet = new int[(int)Math.pow(2, N)][2];
            isSelected = new boolean[N];
            subSetIndex = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                inputScoreKcal[i][0] = Integer.parseInt(st.nextToken());
                inputScoreKcal[i][1] = Integer.parseInt(st.nextToken());
            }

            makeSubSet(0);
            Arrays.sort(subSet, new SubsetComparator());
            for (int i = 0; i < (int)Math.pow(2, N); i++) {
                if(subSet[i][1] <= L) {
                    bw.write(subSet[i][0] + "\n");
                    break;
                }
            }
        }
        bw.flush();
        bw.close();
    }

    private static void makeSubSet(int cnt) {
        if(cnt == N){
            ++subSetIndex;
            for (int i = 0; i < N; i++) {
                if(isSelected[i]){
                    subSet[subSetIndex][0] += inputScoreKcal[i][0];
                    subSet[subSetIndex][1] += inputScoreKcal[i][1];
                }
            }
            return;
        }

        isSelected[cnt] = true;
        makeSubSet(cnt + 1);
        isSelected[cnt] = false;
        makeSubSet(cnt + 1);
    }
    static class SubsetComparator implements Comparator<int[]>{

        @Override
        public int compare(int[] o1, int[] o2) {
            return o2[0] - o1[0];
        }
    }
}


