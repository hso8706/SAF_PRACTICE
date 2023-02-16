package com.bitMasking_permutation_230216;

// 두 명의 손님, 최대한 비슷한 음식 두 개
// N개의 식재료, N/2로 배분하여 두 개의 음식 만들기
// 식재료 궁합, 식재료 i, 식재료 j, 1 <= i, j <= N, i != J
// 4 <= N <= 16
// 1 <= Sij <= 20000

import java.io.*;
import java.util.*;

public class SW4012_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T, N; //T: 테스트 케이스, N: 식재료 개수
    static int[][] synergyTable; //식재료 시너지 테이블
    static PriorityQueue<Integer> differenceTaste;
    static boolean[] ingredients;
    static int[] firstCom;
    static int[] secondCom;
    static int firstTasteCombination;
    static int secondTasteCombination;
    public static void main(String[] args) throws IOException {
         T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#" + tc + " ");
            N = Integer.parseInt(bf.readLine());
            differenceTaste = new PriorityQueue<>();
            ingredients = new boolean[N];
            synergyTable = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    synergyTable[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            findBestFood();
        }
    }

    private static void findBestFood() {
        firstCom = new int[N/2];
        secondCom = new int[N/2];
        firstCombi(0, 0);
        System.out.println(differenceTaste.poll());
    }

    private static void firstCombi(int cnt, int start) {
        if(cnt == N/2){
            for (int i = 0; i < N/2; i++) {
                firstTasteCombination += firstCom[i];
            }
            secondCombi(0, 0);
            return;
        }
        for (int i = 0; i < N; i++) {
            firstCom[cnt] = i;
            ingredients[i] = true;
            firstCombi(cnt + 1, i + 1);
            ingredients[i] = false;
            firstTasteCombination = 0;
        }
    }

    private static void secondCombi(int cnt, int start) {
        if(cnt == N/2){
            for (int i = 0; i < N/2; i++) {
                secondTasteCombination += secondCom[i];
            }
            differenceTaste.offer(Math.abs(firstTasteCombination - secondTasteCombination));
            return;
        }
        for (int i = 0; i < N; i++) {
            if(ingredients[i]) continue;
            secondCom[cnt] = i;
            secondCombi(cnt + 1, i +1);
            secondTasteCombination = 0;
        }
    }
}

/*
1
4
0 5 3 8
4 0 4 1
2 5 0 3
7 2 3 0
 */