package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW2115_벌꿀채취_solved1 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 고려 사항
        - 일꾼의 위치
        - 어떤 벌통이 최대값을 가져오는지
    - step
        - 1단계 : 두 명의 시작 위치를 어떻게 선택할 지
            - 모든 가능한 위치에서 2개의 위치를 선택 ( 순서 X )
            - 선택 후 규칙에 위반되는 것은 버린다.
        - 2단계 : 각각의 위치에서 최대 벌꿀 채취는 어떻게 할 것인가
            - 부분 집합 형태로 최대 벌꿀 채취값을 구한다.
     */

    static int T, N, M, C, max;
    static int[][] map;
    static int[] tgt; // src 로부터 2개의 시작점을 뽑는 조합을 생각할 때, 선택된 2개의 위치가 저장되는 공간

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());

        for (int t = 1; t <= T ; t++) {
            bw.write("#" + t + " ");

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N]; // 위치를 뽑을땐 1열로 펼쳐져있다고 생각하고 뽑을 것.
            tgt = new int[2];

            max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            comb(0, 0); // tgt 뽑기(조합)
            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void comb(int srcIdx, int tgtIdx) {
        // 기저 조건1
        if(tgtIdx == 2) { // tgt 갯수만큼 선택 완료하면 종료
            // 경우의 수 완성된 순간
            // 두 위치가 겹치는 지 확인; M을 확인
            // 앞 자리 tgt[0] 부터 M 거리안에 tgt[1] 없으면 된다.
            if( tgt[0] < tgt[1] && tgt[1] <= tgt[0] + M -1) return; //겹치는 상황은 종료

            // 같은 행에 있는지 확인
            if( tgt[0] / N != (tgt[0] + M -1) / N ) return; // 겹치는 상황 && 같은 행에 존재하면 종료
            if( tgt[1] / N != (tgt[1] + M -1) / N ) return;

            // 벌꿀채취를 위해 선택된 tgt[0], tgt[1] 두 위치는 1단계 통과된 상태
            // 2단계 진행
            // 각자 위치에서 최대값을 가져온다. 그런 뒤 2개를 합하여 큰 값을 max 와 비교해서 선택
            max = Math.max( max, calcMax(tgt[0]) + calcMax(tgt[1])); //calcMax() : 최대 벌꿀 채취량을 반환하는 메서드

            return;
        }

        // 기저 조건2
        if( srcIdx == N*N ) return;// src 로부터 선택하는데 src 를 모두 다 고려한 상황

        tgt[tgtIdx] = srcIdx; // 좌표의 index 로 생각

        // 선택과 비선택
        comb( srcIdx+1, tgtIdx+1); // 현재 선택을 만족하고 tgt의 다음 선택을 고려
        comb( srcIdx+1, tgtIdx); // 현재 선택을 만족하지 못하고 tgt의 현재를 다시 고려

    }

    private static int calcMax(int idx) { // N*N 자리를 1열로 고려, idx 위치에서 연속적인 M 의 벌꿀 중 최대를 고려
        return dfs( idx, idx + M, 0, 0); // 부분 집합
    }

    // sum : 자리에서 채취하는 벌꿀, priceSum : 누적 채취하는 벌꿀
    private static int dfs(int srcIdx, int tgtIdx, int sum, int priceSum) {
        // 기저 조건
        if(srcIdx == tgtIdx) return priceSum;

        // 벌꿀 채취 로직
        int val = map[srcIdx/N][srcIdx%N];
        int currPriceSum = 0 ;
        if(sum + val <= C) {
            currPriceSum = priceSum + (int) Math.pow(val, 2);
        }

        return Math.max(
                dfs( srcIdx+1, tgtIdx, sum, priceSum), // 비선택
                dfs( srcIdx+1, tgtIdx, sum + val, currPriceSum) // 선택
        );
    }
}
