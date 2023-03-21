package solved;

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
        - 1번 집의 색칠은 초기값으로 설정하고, 2번 집부터 점화식을 세운다
        - n번 집의 점화식은 n번 집과 다른 색인 n-1번집 경우의 수 중 최소 비용값을 누적한 값이다. 말이 어렵지만, 그냥 이전 값 중 최소값 가져오기
     */
    static int N; // 집의 수
    static int[][] colorCost; // 0:빨, 1:초, 2:파
    static int minCost;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        colorCost = new int[N][3];
        //1. 집의 수 만큼 채색 비용값 배열 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                colorCost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //2. 점화식 사용(1번 집은 제외)
        for (int i = 1; i < N; i++) {
            colorCost[i][0] += Math.min(colorCost[i-1][1], colorCost[i-1][2]); // 이전 집 중 서로 다른 두 색의 비용중 최소
            colorCost[i][1] += Math.min(colorCost[i-1][0], colorCost[i-1][2]);
            colorCost[i][2] += Math.min(colorCost[i-1][0], colorCost[i-1][1]);
        }

        minCost = Math.min(Math.min(colorCost[N-1][0], colorCost[N-1][1]), colorCost[N-1][2]); // 세 경우 중 최소 값

        bw.write(minCost + "");
        bw.flush();
        bw.close();
    }
}
