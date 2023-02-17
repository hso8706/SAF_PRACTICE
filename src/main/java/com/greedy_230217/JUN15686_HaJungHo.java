package com.greedy_230217;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN15686_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
//    static int[][] city;
    static ArrayList<int[]> home, store; // 집, 치킨 가게 좌표를 받을 리스트
    static ArrayList<int[]> storeCombi; // 치킨 가게 중 폐업 안하고 살아남은 곳의 좌표가 저장된 리스트
    static int homeSize, storeSize; // home, store 의 길이
    static PriorityQueue<Integer> pq; // 최소값을 쉽게 뽑기 위한 pq

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        home = new ArrayList<>();
        store = new ArrayList<>();
        storeCombi = new ArrayList<>();
        pq = new PriorityQueue<>();

//        city = new int[N+1][N+1];
        //city 초기화 => 필요없을듯
        //집과 치킨 가게 좌표 찍고, home 과 store 리스트에 저장
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < N+1; j++) {
                int temp = Integer.parseInt(st.nextToken());
//                city[i][j] = temp;
                if(temp == 1) home.add(new int[]{i, j});
                if(temp == 2) store.add(new int[]{i, j});
            }
        }

        homeSize = home.size(); // 길이 초기화
        storeSize = store.size(); // 길이 초기화
        combi(0, 0); // 치킨 가게 폐점 안하고 살아남은 조합 뽑기
        bw.write(pq.poll() + "\n"); // 도시의 치킨 거리 == (각 집에서 모든 치킨 가게까지의 거리의 총합의 최소값...)
        bw.flush();
        bw.close();
    }

    private static void combi(int cnt, int start) throws IOException {
        if(cnt == M){
            // 치킨 가게 M개 뽑힌 상태
            // 집 좌표 모두 저장된 상태
            minDistance(); // 도시의 치킨 거리 구하는 메서드
            return;
        }
        for (int i = start; i < storeSize; i++) {
            storeCombi.add(store.get(i)); // 조합 리스트 저장
            combi(cnt + 1, i + 1);
            storeCombi.remove(store.get(i)); // 조합 리스트 제거
        }
    }

    private static void minDistance() throws IOException {
        int total = 0;

        // 각 집의 치킨 거리의 합 구하기
        for (int i = 0; i < homeSize; i++) {
            int[] distance = new int[storeCombi.size()];
            for (int j = 0; j < storeCombi.size(); j++) {
                distance[j] = Math.abs(home.get(i)[0] - storeCombi.get(j)[0]) + Math.abs(home.get(i)[1] - storeCombi.get(j)[1]);
            }
            Arrays.sort(distance);
            total += distance[0];
        }
        pq.offer(total);
    }

}
/*
1. 조합을 이용해서 치킨집을 선정, 경우의 수마다 최소 치킨 거리를 구하여 저장하고 그 중에서 최소값 선정
 */