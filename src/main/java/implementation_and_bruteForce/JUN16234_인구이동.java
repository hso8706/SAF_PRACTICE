package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN16234_인구이동 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] boardLine;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //bfs 로 열 수 있는 국가 체크(국경선 열기)
        //visited 와 boardLine 을 따로 운영
        movePopulation(new Pair(0, 0));
    }

    private static void movePopulation(Pair start) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 0 || ny<0 || nx>=N || ny>=N) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    int gap = map[cx][cy] - map[nx][ny];
                    if(gap >= L || gap <= R){
                        boardLine[cx][cy] = true;
                        boardLine[nx][ny] = true;
                    }

                }
            }
        }
    }
}
