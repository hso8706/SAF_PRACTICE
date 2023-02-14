package com.tree_01_230214;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SW9229_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int TC, N, M;
    static int[] weightOfSnack;
    static List<Integer> sumWeightArr;
    static int[] temp = new int[2];

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < TC + 1; tc++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken()); // 과자 종류 개수
            M = Integer.parseInt(st.nextToken()); // 과자 두 개의 합 제한(max)
            weightOfSnack = new int[N];
            sumWeightArr = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                weightOfSnack[i] = Integer.parseInt(st.nextToken());
            }
            combi(0, 0);
            Collections.sort(sumWeightArr, Collections.reverseOrder());
            bw.write("#"+ tc + " ");
            printResult();

        }
        bw.flush();
        bw.close();
    }

    private static void printResult() throws IOException {
        for (int i = 0; i < sumWeightArr.size(); i++) {
            if(sumWeightArr.get(i) <= M){
                bw.write(sumWeightArr.get(i) + "\n");
                return;
            }
        }
        bw.write(-1 + "\n");
    }

    private static void combi(int cnt, int start) {
        if(cnt == 2){
            sumWeightArr.add(temp[0] + temp[1]);
            return;
        }
        for (int i = start; i < N; i++) {
            temp[cnt] = weightOfSnack[i];
            combi(cnt + 1, i + 1);
        }
    }
}
