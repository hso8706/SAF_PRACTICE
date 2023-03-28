package solved;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN2178 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    미로 탐색
    - 0, 1 로 주어진 map, 1은 길을 의미
    - 1,1 시작 / N,M 도착
    - 최소 경로
     */
    static int N, M; // N: 세로, M: 가로
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int cellCnt;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        cellCnt = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 1; j < M+1; j++) {
                map[i][j] = Integer.parseInt(temp[j-1]);
            }
        }
        bfs(1,1, 1);
        bw.write(cellCnt + "");
        bw.flush();
        bw.close();
    }

    private static void bfs(int x, int y, int cnt) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, cnt});
        visited[x][y] = true;

        while (!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int cc = current[2];
            if(cx == N && cy == M) {
                if(cellCnt > cc) cellCnt = cc;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<1 || ny<1 || nx>N || ny>M) continue;
                if(!visited[nx][ny] && map[nx][ny] != 0){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx,ny,cc+1});
                }
            }
        }
    }

}