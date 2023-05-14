package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW2105_디저트카페 {
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

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    ", c=" + c +
                    '}';
        }
    }
    
    static int T, N;
    static int[][] map;
    static int[] dx = new int[]{1,1,-1,-1};
    static int[] dy = new int[]{-1,1,1,-1};
    static List<Integer> stores;
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            cnt = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N-2; i++) { //i == N-1,N-2 무의미
                for (int j = 1; j < N-1; j++) { //j==0, N-1 무의미
                    stores = new ArrayList<>();
                    visited = new boolean[N][N];
                    stores.add(map[i][j]);
                    visited[i][j] = true;
                    startDesertTrip(new Pair(i,j,1), new Pair(i,j,1), 0); //시작점과 방향
//                    System.out.println();
                }
            }
            if(cnt==0) cnt = -1;
            bw.write(cnt+"\n");
        }
        bw.flush();
        bw.close();

    }

    private static void startDesertTrip(Pair current, Pair end, int dir) {
        if(current.c != 2 && current.x == end.x+1 && current.y == end.y+1){//모든 여정 후 사각형의 시작점으로 돌아온 경우
            cnt = Math.max(cnt, current.c);
//            System.out.println(">>" + current);

            return;
        }

        for (int i = dir; i <= dir+1; i++) {
            if(i==4) return;
//            System.out.println(i + "," + current);
            int nx = current.x + dx[i];
            int ny = current.y + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=N ) {
                dir++;
                continue;
            }
            if(!visited[nx][ny] && !stores.contains(map[nx][ny])) {
                visited[nx][ny] = true;
                stores.add(map[nx][ny]);
                startDesertTrip(new Pair(nx, ny, current.c + 1), end, i);
                visited[nx][ny] = false;
                stores.remove(stores.size()-1);
            }
        }
    }
}
