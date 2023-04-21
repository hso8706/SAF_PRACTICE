package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN14500_테트로미노 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 테트로미노
    - board : N x M
    - 4 <= N,M <= 500

    - 1번 테트로미노 : 직선
        - 2가지 경우의 수; 가로, 세로
    - 2번 테트로미노 : 네모
        - 1가지 경우의 수
    - 3번 테트로미노 : ㄱ 모양
        - 8가지 경우의 수; 회전(4) x 좌우대칭(2)
    - 4번 테트로미노 : 번개
        - 4가지 경우의 수; 회전(2) x 대칭(2)
    - 5번 테트로미노 : ㅗ 모양
        - 4가지 경우의 수; 회전(4)
     */
    static int N, M;
    static int[][] board;
    static int maxSum;
    
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1}; // 북쪽 시작 시계 방향 90도
    
    static class Pair { // 좌표 용도
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 초기화, 입력
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maxSum = Integer.MIN_VALUE;
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        /*
        테트로미노 놓아보기
        - 시작 좌표 지정 후 메소드 시작
        - 테트로미노의 종류별로 놓는 메소드 구현 및 최대합 갱신
         */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                setTetromino(new Pair(i, j));
            }
        }
    }

    private static void setTetromino(Pair start) {
        firstTet(start);
        secondTet(start);
        thirdTet(start);
        forthTet(start);
        fifthTet(start);
    }

    private static void firstTet(Pair start) {

        for (int i = 0; i < 4; i++) {
            int tempSum = board[start.x][start.y];
            for (int j = 0; j < 3; j++) {
                int nx = start.x + dx[i];
                int ny = start.y + dy[i];
                if(nx < 0 || ny<0 || nx>=N || ny>=M) continue;
                tempSum += board[nx][ny];
            }
            maxSum = Math.max(tempSum, maxSum);
        }

    }
}
