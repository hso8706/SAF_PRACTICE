package aSAF.DynamicProgramming3_230330;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
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
    static int[][] dp; // 경우의 수 저장 배열

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        things = new int[N+1][2];
        dp = new int[N + 1][K + 1];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            things[i][0] = weight;
            things[i][1] = value;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < K + 1; j++) {
                // 물건을 넣을 수 있는 경우 : 물건을 넣었을 때의 경우와 안 넣었을 때의 경우 중 무엇이 최대 가치인지 비교해서 넣어야함.
                // 물건을 넣는 경우에 주의점 : 물건을 넣고 남은 무게만큼 또 넣을 수 있는 다른 방법이 있다면 추가로 물건을 넣어야함
                if (j >= things[i][0]) {
                    dp[i][j] = Math.max((things[i][1]+dp[i-1][j-things[i][0]]), (dp[i - 1][j])); // 1: i번째 물건을 넣은 경우, 2: 안 넣은 경우
                }
                // 물건을 넣을 수 없는 경우 : 물건을 넣지 않았을 경우의 값을 그대로 가져온다
                else {
                    dp[i][j] = dp[i-1][j];
                }
                pq.offer(dp[i][j]);
            }
        }
        bw.write(pq.poll() + "");
        bw.flush();
        bw.close();
    }
}