package aSAF.DynamicProgramming3_230330;

import java.io.*;
import java.util.StringTokenizer;

public class JUN12865_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 평범한 배낭
    - 제한된 무게에서 가치를 최대로 했을때의 가치값 출력
    - 클래식한 문제같음. 검색하더라도 정리 잘 해둘 것.

    ### greedy => Fraction Knapsack
    1. 값이 비싼 물건부터 채우기
    2. 무게가 가벼운 순으로 채우기
    3. 무게 당 값이 높은 순서로 채우기

    ### DP => 0/1 Knapsack
    - table
        - 가로행 : 0 ~ 제한 한도까지
        - 세로행 : 물건 인덱스
    - 값 채우기
        - 반복문을 순회하며 해당 순간의 제한 한도까지
     */
    static int N, K; //N: 물품의 수, K: 무게 제한
    static int[][] things; // 0: 물건의 무게, 1: 물건의 가치
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        K = Integer.parseInt(bf.readLine());
        things = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            things[i][0] = weight;
            things[i][1] = value;
        }
    }
}
