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
//    static int[] dx = new int[]{1,-1,0,0};
//    static int[] dy = new int[]{0,0,1,-1};
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    static boolean flag;

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
        int days = 0;
        flag = true;
        while(flag){
            boardLine = new boolean[N][N];
            checkAllDir();
            if(flag) days++;
        }
        bw.write(days+"");
        bw.flush();
        bw.close();
    }

    private static void checkAllDir() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int i = 0; i < 2; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx < 0 || ny<0 || nx>=N || ny>=N) continue;
                    int gap = Math.abs(map[nx][ny] - map[x][y]);

                    if(gap >= L && gap <= R){
                        boardLine[nx][ny] = true;
                        boardLine[x][y] = true;
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(boardLine[i][j]) {
                    union(new Pair(i,j));
                    cnt++;
                }
            }
        }
        flag = cnt != 0;
    }

    private static void union(Pair pair) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(pair);
        int sum = 0;
        int cnt = 0;
        boolean[][] temp = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = boardLine[i][j];
            }
        }

        boardLine[pair.x][pair.y] = false;

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            sum += map[cx][cy];
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 0 || ny<0 || nx>=N || ny>=N) continue;
                if(boardLine[nx][ny]) {
                    boardLine[nx][ny]=false;
                    queue.offer(new Pair(nx,ny));
                }
            }
        }
        int movePop = (int) sum/cnt;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(temp[i][j]) {
                    temp[i][j] = false;
                    map[i][j] = movePop;
                }
            }
        }
    }

}
