package aSAF.algorithmPlus02_230407;

import java.io.*;
import java.util.StringTokenizer;

public class SW2383_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 점심 식사시간
    - NxN 방(4~10)
    - P: 사람(1~10), S: 계단 입구(2개)
    - 고려해야할 시간: 계단 입구까지 가는 시간 + 계단 입구 도착 후 계단을 내려가는 시간
        - 계단 입구로 이동하는 시간: 맨해튼 거리
        - 계단 입구에서 내려가는 시간
            - 분 당 한 칸
            - 계단은 한 번에 3명까지 내려갈 수 있음
            - 계단 길이(K)로 제공 => 계단 모두 내려가는데 K 분 걸림
    - 목표 : 모든 사람들이 계단을 전부 내려가 이동을 완료하는 최소 시간

    - 고려
        - 1. 어떤 사람부터 이동해야하는가
        - 2. 어느 계단으로 어떤 사람들(그룹)이 이동해야하는가
    ### 해결
    1. 사람 이동 배열을 만들어서 저장하고 풀기
        - 사람별로 2가지 경우의 수가 존재 => 총 20개(인덱스 1부터 시작, 총 배열 길이 21)
        - true, false 부분 집합으로 내려갈 계단 그룹 구하기
        -
     */
    static int T, N; // T: tc, N: map size
    static int[][] map; // 0: 빈 공간, 1: 사람, 2~: 계단 입구 및 계단 길이
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#"+tc+" ");
            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            fillTheMap();
        }
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
