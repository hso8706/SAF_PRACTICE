package solved;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN2667 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] houses;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        houses = new int[N*N +1];

        for (int i = 0; i < N; i++) {
            String[] st = bf.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st[j]);
            }
        }
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
//                    map[i][j] = cnt;
                    houses[cnt] = 1;
                    bfs(i, j, cnt);
                    cnt++;
                }
            }
        }
        bw.write(cnt-1 + "\n");
        Arrays.sort(houses);
        for (int i = 0; i < houses.length; i++) {
            if(houses[i] == 0) continue;
            bw.write(houses[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void bfs(int x, int y, int cnt) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx<0 || ny <0 || nx>=N || ny>=N) continue;
                if(!visited[nx][ny] && map[nx][ny] != 0){
//                    map[nx][ny] = cnt;
                    houses[cnt] ++;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
