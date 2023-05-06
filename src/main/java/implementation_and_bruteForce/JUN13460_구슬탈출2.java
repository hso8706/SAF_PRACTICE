package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN13460_구슬탈출2 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 같은 방향으로 같이 움직여지는 2개의 구슬(R, B)
    - R이 구멍(O)로 혼자 먼저 나와야 성공
    - B가 먼저 혹은 동시에 나오면 실패
    - 두 개의 구슬은 같은 칸에 동시에 존재하지 못함.
    - 구슬이 더 이상 움직이지 못하면 종료
    - 기울이기 동작은 벽에 구슬이 닿을때까지 기울이는 동작임

    - 출력
        - R이 구멍을 빠져나올 수 있는 기울이기 최소 횟 수
     */
    static class Pair {
        int x;
        int y;
        int c; //cnt

        public Pair(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
    static int N, M; //board size
    static char[][] board;
    static boolean[][][] visited; // red, blue 별개
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    static Pair redBall;
    static Pair blueBall;
    static Pair hole;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[2][N][M]; //0: red, 1: blue

        for (int i = 0; i < N; i++) {
            char[] rowInput = bf.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = rowInput[j];
                if(board[i][j] == 'B') blueBall = new Pair(i,j,0);
                if(board[i][j] == 'R') redBall = new Pair(i,j,0);
                if(board[i][j] == 'O') hole = new Pair(i,j,0);
            }
        }

        playGame();
    }

    private static void playGame() {
        // 2개의 공을 동시에 운용
        Deque<Pair> red = new ArrayDeque<>();
        Deque<Pair> blue = new ArrayDeque<>();
        red.offer(redBall);
        visited[0][redBall.x][redBall.y] = true;
        blue.offer(blueBall);
        visited[1][blueBall.x][blueBall.y] = true;

        while(!red.isEmpty()){ //더 이상 공이 움직일 수 없는 경우 종료(blue는 못 움직여도 상관없음)
            Pair redCurrent = red.poll();
            int cxRed = redCurrent.x;
            int cyRed = redCurrent.y;
            int ccRed = redCurrent.c;
            Pair blueCurrent = blue.poll();
            int cxBlue = blueCurrent.x;
            int cyBlue = blueCurrent.y;
            int ccBlue = blueCurrent.c;

            for (int i = 0; i < 4; i++) {
                int nxRed = cxRed + dx[i];
                int nyRed = cyRed + dy[i];
                if(!isSafe(nxRed, nyRed)) { // 바로 막힌 경우의 현재 자리 유지
                    nxRed = cxRed;
                    nyRed = cyRed;
                }
                else{
                    while(isSafe(nxRed, nyRed)){ // 벽이 아닌 경우 해당 방향으로 계속 진행
                        nxRed = cxRed + dx[i];
                        nyRed = cyRed + dy[i];
                    }    
                }
                if(board[nxRed][nyRed] == 'B') continue; //
                if(!visited[0][nxRed][nyRed]){
                    visited[0][nxRed][nyRed] = true;

                }
            }
            for (int i = 0; i < 4; i++) {
                int nxBlue = cxBlue + dx[i];
                int nyBlue = cyBlue + dy[i];
                if(!isSafe(nxBlue, nyBlue)) continue;
            }
        }
    }

    private static boolean isSafe(int nx, int ny) {
        return board[nx][ny] != '#'; // 벽
    }
}
