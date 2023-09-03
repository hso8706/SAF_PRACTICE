package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2115_갤러리 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*

    ### 입력
    - 가로 M, 세로 N => map
    - 벽 X, 빈 .

    ### 해결 방법 1
    - 완전 탐색 실시(가로, 세로 구분)
    - 빈 공간인 경우 벽의 위치 확인
    - 이어진 벽이면 row cnt 증가
        - row 종료 시 row cnt / 2 실시 후 total cnt 에 추가
     */
    static int M, N;
    static String[][] map;
    static boolean[][] visited;
    static boolean[][] isWall;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new String[M][N];
        visited = new boolean[M][N];
        isWall = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
            }
        }
        int totalCnt = 0;
        for (int i = 0; i < M; i++) {
            int rowCntUpper = 0;
            int rowCntLower = 0;
            int colCntRight = 0;
            int colCntLeft = 0;
            for (int j = 0; j < N; j++) {
                if(map[i][j].equals(".")){
                    if(isWall(new Pair(i, j), 0)) rowCntUpper++;
                    else {
                        totalCnt += rowCntUpper/2;
                        rowCntUpper = 0;
                    }
                    if(isWall(new Pair(i, j), 2)) rowCntLower++;
                    else {
                        totalCnt += rowCntLower/2;
                        rowCntLower = 0;
                    }
                }

                if(j == N-1 || map[i][j].equals("X")){
                    totalCnt += rowCntUpper/2  + rowCntLower/2;
                    rowCntUpper = 0;
                    rowCntLower = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int colCntRight = 0;
            int colCntLeft = 0;
            for (int j = 0; j < M; j++) {
                if(map[j][i].equals(".")){
                    if(isWall(new Pair(j, i), 1)) colCntRight++;
                    else {
                        totalCnt += colCntRight/2;
                        colCntRight = 0;
                    }
                    if(isWall(new Pair(j, i), 3)) colCntLeft++;
                    else {
                        totalCnt += colCntLeft/2;
                        colCntLeft = 0;
                    }
                }

                if(j == M-1 || map[j][i].equals("X")){
                    totalCnt += colCntRight/2  + colCntLeft/2;
                    colCntRight = 0;
                    colCntLeft = 0;
                }
            }
        }

        System.out.println(totalCnt);
    }

    private static boolean isWall(Pair pair, int dir) {
        int nx = pair.x + dx[dir];
        int ny = pair.y + dy[dir];
        return map[nx][ny].equals("X");
    }

}
