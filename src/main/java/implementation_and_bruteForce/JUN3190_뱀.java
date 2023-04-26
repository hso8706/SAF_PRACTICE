package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN3190_뱀 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 뱀
    - 머리 다음칸 이동
        - 사과 있다면 꼬리 유지
        - 사과 없다면 꼬리 당기기
     */
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int[][] board;
    static int N, K, L; //N: board 크기, K: 사과의 개수, L: 뱀 방향 변환 정보
    static int[] X;
    static String[] C;
    static int[] dx = new int[]{-1,0,1,0};//북쪽부터 시계방향
    static int[] dy = new int[]{0,1,0,-1};

    public static void main(String[] args) throws IOException {
        // board 초기화
        N = Integer.parseInt(bf.readLine());
        board = new int[N+1][N+1];
        // 사과 초기화
        K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
        }
        // 이동 초기화
        L = Integer.parseInt(bf.readLine());
        X = new int[L];
        C = new String[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(bf.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            C[i] = st.nextToken();
        }
        int endTime = moveSnake();
        System.out.println(endTime);
    }

    private static int moveSnake() {
        // 1초씩 증가는 시간 구현
        int time = 0;
        int dir = 1; // 뱀의 이동 방향, 초기 방향은 우측 방향
        int xcIdx = 0;
        // 뱀의 길이를 리스트로 구현하기 => 뱀의 머리가 닿는지 유무 확인 및 꼬리의 이동 용이
//        ArrayList<Pair> snake = new ArrayList<>();
//        snake.add(new Pair(1,1)); // 첫 시작점
        // 뱀의 길이를 새로운 보드판에 true, false로 나타내보자
        boolean[][] snakeBoard = new boolean[N+1][N+1];
        int nx = 1;
        int ny = 1;
        snakeBoard[nx][ny] = true;
        Deque<Pair> snakePos = new ArrayDeque<>();
        snakePos.offerLast(new Pair(nx,ny));

        if (X[0] == 1) { // 제공된 이동에 해당되는 시간일 경우
            if (C[0].equals("D")) { // 우회전
                dir++;
            } else {  // 좌회전
                dir--;
            }
            xcIdx++;
        }

        // 1초씩 증가하며 뱀의 머리의 이동 구현
        while(true) {
            time++;

            nx += dx[dir];
            ny += dy[dir];
            // 벽에 닿는 경우
            if (nx < 1 || ny < 1 || nx > N || ny > N) break;
            // 자기 몸에 닿는 경우
            if (snakeBoard[nx][ny]) break;
            // 머리 이동
            snakeBoard[nx][ny] = true;
            snakePos.offerLast(new Pair(nx, ny));

            // 꼬리 이동; 사과가 있는지 없는지에 따른 조건 구현
            if (board[nx][ny] == 0) { // 사과가 없는 경우
                Pair snakeTail = snakePos.pollFirst(); // 꼬리의 정보
                int tx = snakeTail.x;
                int ty = snakeTail.y;
                snakeBoard[tx][ty] = false;
            }
            else { // 사과가 있는 경우의 로직은 위에서 해결, 사과 자체만 처리
                board[nx][ny] = 0;
            }

            if (xcIdx < X.length && X[xcIdx] == time) { // 제공된 이동에 해당되는 시간일 경우
                if (C[xcIdx].equals("D")) { // 우회전
                    dir++;
                    if (dir == 4) dir = 0;
                } else {  // 좌회전
                    dir--;
                    if (dir == -1) dir = 3;
                }
                xcIdx++;
            }
        }
        
        return time;
    }
}
