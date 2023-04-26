package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN14500_테트로미노 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 테트로미노
    - board : N x M
    - 4 <= N,M <= 500
    
    - setTetromino
        - 4방(위, 아래, 오른쪽, 왼쪽)을 통한 bfs(혹은 dfs) 진행 방식과 생성 방식이 같다.
        - ㅗ 모양만 예외로 형성
            - 우로 세칸, 혹은 아래로 세칸인 경우 if문으로 예외를 따로 둘 것
     */
    static int N, M;
    static int[][] board;
    static int maxSum; //setTetromino를 통해 하나의 테트로미노가 자리를 잡은 경우의 총합

    static int[] dx = new int[]{0, 1, 0}; // 우방향, 상방향, 하방향, 좌방향
    static int[] dy = new int[]{1, 0, -1};

    static class Pair { // 좌표 용도
        int x;
        int y;
        int c; // 현재 놓은 칸의 개수
        int s; // 현재 덮은 칸의 총합

        public Pair(int x, int y, int c, int s) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.s = s;
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
                boolean[][] visited = new boolean[N][M];
                visited[i][j] = true;
                setTetromino(new Pair(i, j, 1, board[i][j]), visited);
                exceptionTet(i, j);
            }
        }
        System.out.println(maxSum);
    }

    private static void exceptionTet(int i, int j) {
        int sumRow = board[i][j];
        int rx = i;
        int ry = j;
        for (int k = 0; k < 2; k++) {
            rx += dx[0];
            ry += dy[0];
            if(isNotSafe(rx,ry)) continue;
            sumRow += board[rx][ry];
        }
        if(!isNotSafe(i-1, j+1)) {
            maxSum = Math.max(maxSum, sumRow + board[i-1][j+1]);
        }
        if(!isNotSafe(i+1, j+1)) {
            maxSum = Math.max(maxSum, sumRow + board[i+1][j+1]);
        }

        int cx = i;
        int cy = j;
        int sumCol = board[i][j];
        for (int k = 0; k < 2; k++) {
            cx += dx[2];
            cy += dy[2];
            if(isNotSafe(cx,cy)) continue;
            sumCol += board[cx][cy];
        }
        if(!isNotSafe(i+1, j+1)) {
            maxSum = Math.max(maxSum, sumCol + board[i+1][j+1]);
        }
        if(!isNotSafe(i+1, j-1)) {
            maxSum = Math.max(maxSum, sumCol + board[i+1][j-1]);
        }

    }

    private static void setTetromino(Pair pair, boolean[][] visited) {
        if(pair.c == 4){
            maxSum = Math.max(maxSum, pair.s);
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = pair.x + dx[i];
            int ny = pair.y + dy[i];
            int nc = pair.c + 1;
            if(isNotSafe(nx, ny)) continue;
            int ns = pair.s + board[nx][ny];
            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                setTetromino(new Pair(nx, ny, nc, ns), visited);
                visited[nx][ny] = false;
            }
        }
    }

    private static boolean isNotSafe(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }

}
