package solving;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN9663_NQueen {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### N-Queen
    - 백트래킹 대표 문제
    - N x N 체스판
    - N 개의 퀸
    - 1 <= N < 15
    - 서로 공격할 수 없게 위치하기

    ### 목표
    - N개의 퀸을 서로 공격하지 못하게 놓을 수 있는 경우의 수를 출력

    ### 해결1
    - 재귀 사용
        - 이중 반복문으로 위치 선정
        - 이중 boolean 반복문으로 가능한 위치에만 놓기
        - 위치 선정 후 퀸의 공격 범위 설정(false)
        - 재귀 호출
        - 퀸의 개수 줄이며 호출
        - 퀸의 개수가 0개면 경우의 수 갱신
        - 재귀 종료 시 맵 복구 시키기 => boolean 아닌 int, 그리고 증가하는 숫자로 마킹하는게 좋을듯
     */
    static int N;
    static int[][] board;
    static int possibleCnt;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        board = new int[N][N];
        possibleCnt = 0;

        n_queen(N, 1);
    }

    private static void n_queen(int nCnt, int mark) {
        if(nCnt == 0) {
            possibleCnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 0){
                    // 퀸을 놓을 수 있음
                    attackQ(i, j, mark);
                    n_queen(nCnt-1, mark++);
                    recover(mark);
                }
            }
        }
    }

    private static void recover(int mark) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == mark) board[i][j] = 0;
            }
        }
    }

    private static void attackQ(int x, int y, int mark) {
        // 가로
        for (int i = 0; i < N; i++) {
            if (board[x][i] == 0 ) board[x][i] = mark;
        }
        // 세로
        for (int i = 0; i < N; i++) {
            if (board[x][i] == 0 ) board[i][y] = mark;
        }
//        // 대각선
//        for (int i = 0; i < ; i++) {
//
//        }
    }
}
