package study.week_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN20543 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    # 조건
    - map
        - 너비: N*N, 0 시작
        - 고도: 초기 고도 = 0
    - 폭발 범위
        - 고도 1씩 낮춤
        - 중심 기준 M*M
        - 범위는 map 을 벗어나게 던지지 않음 + 밖에 던지지도 않음
        - 겹치는 곳, 개수에 따라 중복 폭발
    - 폭발
        - 3일 뒤 폭발 + 그 자리에 남음
    - 입력
        - N, M
        - 첫번째 폭발이 마무리 된 map
    - 출력
        - 폭탄 위치 찾기
        - map 형식으로 출력

    # 풀이
    - 로직1
        - 2차 반복문 순회
        - 시작 지점 기준 오른쪽 아래 좌표(+M/2,+M/2)에 폭탄 인지
        - 폭탄 기준으로 M*M 범위 고도 되돌리기
    - 시간 복잡도1
        - 2000*2000 = 4000_000 : 4백만
        - 실제로 (N-M+1)*(N-M+1) 탐색: 완탐 괜찮을듯
            - 시간 초과 뜨네
            - (N-M+1)*(N-M+1)*(M*M): 안되네. 뭔가 해야한다. 1000*1000*1000*1000
    - 로직2
        - 2차 반복문 순회
        - 시작 지점 기준 오른쪽 아래 좌표(+M/2,+M/2)에 폭탄 설치
        - M만큼 폭탄 누적: 덱에 추가
        - 덱의 총합 구하기: sum
        - map[i][j]-sum => bomb[
    - 시간 복잡도2
        - (N-M+1)*(N-M+1)
     */
    static int N, M;
    static int[][] map;
    static int[][] bomb;
    static Deque<Integer> accum;
    static int sum;
    public static void main(String[] args) throws IOException {

        init();
        findBombLocation();
        output();
    }

    private static void output() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(bomb[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void findBombLocation() {

        for (int i = 0; i <= (N-M); i++) {
            for (int j = 0; j <= (N-M); j++) {
                if(map[i][j] == 0) continue;//0이면 패스
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        bomb = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
