package aSAF.DynamicProgramming2_230329;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1010_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 다리 놓기

    ### 문제 해결
    1. 조합으로 해결하기 => 시간 초과
    2. 1번 값을 저장하는 방식으로 해결(DP)
        - 조합을 풀어내는 방법 중 DP로 해결하는 것이 나름 웰노운 메서드인가보다.
        - 조합 공식
            - nCr = (n-1)C(r-1) + (n-1)Cr // 공식 1
                - (n-1)C(r-1) : 1개를 미리 골랐을 경우
                - (n-1)C : 1개를 미리 고르지 않았을 경우
            - nC0 = nCn = 1 // 공식 2

     */
    static int T, N, M;
    static int totalCnt;
    static int[][] bridge;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine()); // 이 여러번 tc를 구하게 하는 포인트가 dp로 풀도록 만드는 포인트 같음
        for (int tc = 0; tc < T; tc++) {
            bridge = new int[30][30];
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            totalCnt = 0;
//            combination(0 ,0); // 일반 조합
            bw.write(dpComb(M, N) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int dpComb(int n, int r) {
        if(bridge[n][r] > 0) return bridge[n][r]; // 이미 저장된 경우의 수, 조합 공식1로 인해 쪼개지던 와중에 더 안쪼개져도 되는 경우가 도달하는 곳
        else if (n==r || r==0) return bridge[n][r] = 1; // 조합 성질을 이용하여 재귀 호출을 종료하는 부분, 아래 코드의 공식1로 쪼개다보면 공식2가 돔.
        return bridge[n][r] = dpComb(n-1, r-1) + dpComb(n-1, r); // 조합 공식1
    }

    //일반 조합
//    private static void combination(int cnt, int start) {
//        if(cnt == N){
//            totalCnt++;
//            return;
//        }
//
//        for (int i = start; i < M; i++) {
//            combination(cnt +1, i+1);
//        }
//    }
}
