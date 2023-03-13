package solved;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN2178 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        for (int i = 1; i < N+1; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 1; j < M+1; j++) {
                map[i][j] = Integer.parseInt(temp[j-1]);
            }
        }

        bfs(1, 1);
        bw.write(map[N][M]+"");
        bw.flush();
        bw.close();
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(nx <= 0 || nx >= N+1 || ny <= 0 || ny >= M+1) continue;
                if(!visited[nx][ny] && map[nx][ny] != 0){
                    queue.offer(new int[]{nx, ny});
                    map[nx][ny] += map[current[0]][current[1]];
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
