package aSAF.stringAlgorithm_230404;

import java.io.*;
import java.util.StringTokenizer;

public class SW5643_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 키순서
    - 플로이드 워샬
        - 다시 공부하면서 정리해 볼 것.
     */
    static int T, N, M; // T: tc, N: 학생 수(접점), M: 키 비교 횟수(간선)
    static boolean[][] check; // 연결 여부(키재기) 확인 배열
    static int known; // 자신의 키가 몇 번째인지 알 수 있는 학생 수

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#" + tc + " ");
            N = Integer.parseInt(bf.readLine());
            M = Integer.parseInt(bf.readLine());
            check = new boolean[N+1][N+1];
            known = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());
                int shorter = Integer.parseInt(st.nextToken());
                int taller = Integer.parseInt(st.nextToken());
                check[shorter][taller] = true;
            }
            // 플로이드 워샬 알고리즘 => O(n^3)
            // 삼단논법 느낌이네; A가 B이고 B가 C이면, A는 C이다.
            // 연결 여부만 확인
            for (int k = 1; k < N+1; k++) {
                for (int i = 1; i < N+1; i++) {
                    for (int j = 0; j < N+1; j++) {
                        if (check[i][k] && check[k][j]){
                            check[i][j] = true;
                        }
                    }
                }
            }
            //본인과 연결된 학생 수 카운트, 양방향으로 모두 확인
            for (int i = 1; i < N+1; i++) {
                int cnt = 0;
                for (int j = 1; j < N+1; j++) {
                    if(check[i][j] || check[j][i]){ //i 학생과 다른 학생의 연결 여부
                        cnt++;
                    }
                }
                if(cnt == N-1) known++;
            }
            bw.write(known + "\n");
        }
        bw.flush();
        bw.close();
    }
}
