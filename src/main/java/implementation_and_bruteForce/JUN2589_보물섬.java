package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN2589_보물섬 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Pair {
        int x;
        int y;
        int c;

        public Pair(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
    static int N, M;
    static char[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            char[] temp = bf.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]=='L') {
                    visited = new boolean[N][M];
                    bfs_map(new Pair(i,j,0));
                }
            }
        }
        System.out.println(cnt);
    }

    private static void bfs_map(Pair start) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.x][start.y] = true;
        
        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            int cc = current.c;
            cnt = Math.max(cnt, cc);

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == 'W') continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new Pair(nx, ny, cc+1));
                }
            }
        }
    }
}
