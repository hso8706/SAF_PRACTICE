package solved;

import java.io.*;
import java.util.StringTokenizer;

public class JUN4963 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int w, h; //w: 가로, h: 높이
    static int[][] map;
    static boolean[][] visited;
    static int cnt;
    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};//8방 검색
    static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};//8방 검색
    public static void main(String[] args) throws IOException {
        while(true){
            st = new StringTokenizer(bf.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;

            map = new int[h][w];
            visited = new boolean[h][w];
            cnt = 2;
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(!visited[i][j] && map[i][j] != 0){
                        visited[i][j] = true;
                        map[i][j] = cnt;
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            bw.write(cnt-2 + "\n");
            bw.flush();
        }
        bw.close();

    }

    private static void dfs(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
            if(!visited[nx][ny] && map[nx][ny] != 0){
                visited[nx][ny] = true;
                map[nx][ny] = cnt;
                dfs(nx, ny);
            }
        }
    }
}
