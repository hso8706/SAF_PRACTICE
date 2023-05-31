package cert.SW;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1868_파핑파핑지뢰찾기_Sumin {
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static int N;
    static int ans;
    static int[][] bomb;
    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1,-1, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("testcase/SWE_1868.txt")); //주석
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t= 1; t <= T; t++) {
            ans = 0;
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            bomb = new int[N][N];
            for(int i = 0; i < N; i++){
                map[i] = br.readLine().toCharArray();
            }

            checkBomb();

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if (map[i][j] == '.' && bomb[i][j] == 0){
                        bomb[i][j] = -1;
                        zeroAround(i, j);
                        ans ++;
                    }
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if (map[i][j] == '.' && bomb[i][j] > 0){
                        bomb[i][j] = -1;
                        ans ++;
                    }
                }
            }


            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static void zeroAround(int i, int j) {
        //0 8방 다 0
        bomb[i][j] = -1;
        for(int d = 0; d < 8; d++){
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (!inRange(nx, ny)){
                continue;
            }
            if (bomb[nx][ny] == 0){
                zeroAround(nx, ny);
            }
            bomb[nx][ny] = -1;
        }
    }

    private static void checkBomb() {
        for(int x = 0; x < N; x++){
            for(int y = 0; y < N; y++){
                if (map[x][y] == '.'){
                    bomb[x][y] = getBomb(x, y);
                }
                else{
                    bomb[x][y] = -1;
                }
            }
        }
    }

    private static int getBomb(int x, int y) {
        int cnt = 0;
        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!inRange(nx, ny) || map[nx][ny] == '.'){
                continue;
            }
            cnt ++;
        }
        return cnt;
    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < N;
    }


}
