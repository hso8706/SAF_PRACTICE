package solving;

import java.io.*;
import java.util.StringTokenizer;

public class JUN18111 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 마인크래프트
    - 지반 평탄화 작업을 목적으로함
    - 최소 시간과 땅 높이 출력 => 최소 시간인 경우 땅의 높이가 가장 큰 경우로 출력
       
    ### 문제 해결 포인트
    - 평탄화 기준 레벨을 지정 해야함.
        -
    - 보유한 블럭 개수 카운팅
        - 보유한 상태로 시작하는 경우(==보유한 상태가 되는 경우)
        - 없는 상태로 시작하는 경우(==없는 상태가 되는 경우)
    - 작업별 시간 카운팅
        - 블럭 제거 작업 : 2
        - 블럭 설치 작업 : 1
        => 설치로 처리하는 것이 우선
     */
    static int N, M, B; // N: 집터 세로 크기, M: 집터 가로 크기, B: 보유한 블럭 개수
    static int[][] map;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        createMap();
    }

    private static void createMap() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
