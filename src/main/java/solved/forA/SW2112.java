package solved.forA;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW2112 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 보호 필름
    - 두께(높이), 가로 크기를 갖는 보호 필름 단면
    - 필름 단면은 셀로 구성, 각 셀은 A or B의 특성을 지님
    - 필름 성능 검사 기준 K
    - 필름 성능 검사
        - 세로로 누름
        - 한 column 에 같은 특성을 지닌 셀이 K개 이상 연속되면 통과
    - 약품 투입
        - 약품 A, 약품 B 존재
        - row 별로 약품을 주입할 수 있으며, 주입한 약품의 특성으로 모든 셀이 변함
        - 약품 투입 횟수는 최소가 되도록한다.
    - 목표
        - 성능 검사를 통과시킬 수 있도록 하는 최소 약품 투입 횟수를 출력한다.


    - 해결 1.
        - 약품 투입 횟수를 0부터 증가시켜가며 모든 경우의 수(부분 집합)를 대입하여 파악 => 브루트 포스
        - 2가지의 약품 주입 상황을 모두 부분 집합으로 만들어 계산
        - => 답은 나오는데 시간 초과, 어느 정도의 걸러낼 로직을 만들거나 완전 탐색을 할 다른 방법을 찾아야 한다.
    - 해결 2.
        - 부분 집합을 구하는 과정에서 카운트와 재귀 호출되는 깊이 정보를 같이 제공
        - 기본적으로 깊이 정보가 D가 될 때 종료하되, 카운트가 minTimes 를 넘어서는 순간 종료하게 만들어서 재귀 메모리를 절약
     */
    static int T, D, W, K; //T: tc, D: 필름 두께(세로), W: 가로 크기(가로), K: 합격 기준
    static int[][] film; // 0: 특성A , 1: 특성B
    static int[][] temp;
    static int minTimes;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            bw.write("#" + tc + " ");
            st = new StringTokenizer(bf.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            temp = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            minTimes = Integer.MAX_VALUE;
            subSet(0, 0);
            bw.write(minTimes + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean performanceTest() {
        //column -> row 순으로 반복문 순회
        for (int w = 0; w < W; w++) { // 횡 이동
            int cnt = 1; // 연속된 특성 체크
            int current = temp[0][w];
            boolean colCheck = false;
            for (int d = 1; d < D; d++) { // 종 이동
                if (temp[d][w] == current) {
                    // 현재 마킹된 특성과 같을 경우 => cnt 증가
                    cnt++;
                    // 증가된 cnt 가 검증 기준(K)에 닿으면 해당 column 패스
                    if (cnt == K) {
                        colCheck = true;
                        break;
                    }
                } else {
                    // 현재 마킹된 특성과 다를 경우 cnt 초기화
                    cnt = 1;
                    current = temp[d][w];
                }
            }
            if (K == 1) return true; // TC 49/50 해결
            if (!colCheck) return false;
        }
        return true;
    }

    private static void subSet(int cnt, int depth) {
        if (cnt >= minTimes) return;
        if (depth == D) {
            if (performanceTest()) minTimes = cnt;
            return;
        }
        // no injection
        injectionN(depth);
        subSet(cnt, depth + 1);
        // A injection
        injectionA(depth);
        subSet(cnt + 1, depth + 1);
        // B injection
        injectionB(depth);
        subSet(cnt + 1, depth + 1);

    }

    private static void injectionN(int row) {
        for (int w = 0; w < W; w++) {
            temp[row][w] = film[row][w];
        }
    }

    private static void injectionA(int row) {
        for (int w = 0; w < W; w++) {
            temp[row][w] = 0;
        }
    }

    private static void injectionB(int row) {
        for (int w = 0; w < W; w++) {
            temp[row][w] = 1;
        }
    }

}
