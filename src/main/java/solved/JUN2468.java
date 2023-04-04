package solved;

import java.io.*;
import java.util.*;

public class JUN2468 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /*
    ### 안전 영역
     */
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int maxLevel;
    static int currentLevel;
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    static class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        maxLevel = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxLevel = Math.max(maxLevel, map[i][j]);
            }
        }
        for (int i = 1; i <= maxLevel; i++) {
            currentLevel = i;
            visited = new boolean[N][N];
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(!visited[j][k] && map[j][k] >= currentLevel) {
                        Pair temp = new Pair(j, k);
                        bfs(temp);
                        cnt++;
                    }
                }
            }
            pq.offer(cnt);
        }
        bw.write(pq.poll()+"");
        bw.flush();
        bw.close();
    }

    private static void bfs(Pair temp) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(temp);
        visited[temp.x][temp.y] = true;

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(!visited[nx][ny] && map[nx][ny] >= currentLevel){
                    visited[nx][ny] = true;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
    }
}
