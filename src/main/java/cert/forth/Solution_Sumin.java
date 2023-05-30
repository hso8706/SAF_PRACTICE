package cert.forth;

import java.util.*;
import java.io.*;

public class Solution_Sumin {
    static int ans, N, M;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int[][][] map;
    static int[][][] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("testcase/test4.txt")); //주석
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N][2];
            ans = 0;


            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j][0] = -Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j][0] == 0){
                        for(int k = 0; k < 4; k++){
                            arr = copy(map);
                            ans = Math.max(ans, dfs(i, j, k, 0, 0));
                        }
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static int dfs(int x, int y, int dir, int time, int result){
        if(time == M){
            return result;
        }

        boolean flag = false;
        if(arr[x][y][0] > 0){ //수확가능한지 체크
            result++;
            arr[x][y][0] = 0;
            flag = true;
        }

        for(int i = 0; i < 4; i++){
            int nd = (dir + 3 + i) % 4;
            int nx = x + dx[nd];
            int ny = y + dy[nd];

            if(arr[nx][ny][0] < 0 || arr[nx][ny][0] > time){
                continue;
            }

            if(!flag){
                arr[x][y][1]++;
                arr[x][y][0] = time + 4 + arr[x][y][1];
            }

            return dfs(nx, ny, nd, time + 1, result);
        }
        return dfs(x, y, dir, time + 1, result);
    }
    static int[][][] copy(int[][][] arr){
        int[][][] clone = new int[N][N][2];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                clone[i][j][0] = arr[i][j][0];
            }
        }
        return clone;
    }
}