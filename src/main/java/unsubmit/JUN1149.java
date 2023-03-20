package unsubmit;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN1149 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### RGB 거리
    - 선분(직선)으로 된 길에 1~N번 집 : 배열
    - 집 색 : 빨, 초, 파 중 하나
    - 인접 집끼린 다른 색

    ### 문제 해결
    - 재귀를 활용해서 집에 칠해질 색 조합을 구해야할 듯 => 재귀로 경우의 수 하나씩 구하고 값 계산
        - queue 도 되겠는디 => 안될듯
        - 재귀 => 재귀도 실패할 예정. 시간을 안봤네 => 역시나 시간 초과
    - 동적 계획법
        - 인터넷 서칭함
        - 근데 그리디하게 푸는 느낌이 있는데, 어떻게 반례가 없을 수가 있는걸까?
     */
    static int N; // 집의 수
    static int[][] colorCost; // 0:빨, 1:초, 2:파
    static int[][] result; // 경우의 수가 담길 배열
    static int minCost;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        colorCost = new int[N][3];
        result = new int[N][3];
        minCost = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                colorCost[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        bw.write(minCost + "");
        bw.flush();
        bw.close();
    }
}
