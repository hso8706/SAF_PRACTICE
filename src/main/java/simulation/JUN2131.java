package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2131 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Point {
        int x;
        int y;
        int d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    "}\n";
        }
    }
    static int N, M;
    static int[][] map;
    static Point[] points;
    static int[] answer;
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();
        findLaserSpots();
        output();
    }

    private static void output() throws IOException {
        for (int i = 1; i < answer.length; i++) {
            bw.write(answer[i]+" ");

        }
        bw.flush();
        bw.close();
    }

    private static void findLaserSpots() {
        for(int i=1; i<=N+M; i++){//절반해도
            Point c = points[i];
            dfs(i, c.x, c.y, c.d);
        }
    }

    private static void dfs(int start, int x, int y, int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        if(nx<1||ny<1||nx>=N+1||ny>=M+1) {
            int end = findPoint(nx,ny);
            answer[start] = end;
            answer[end] = start;
            return;
        }
        if(map[nx][ny] == 0){
            dfs(start,nx,ny,d);
        }
        if(map[nx][ny] == 1){
            int nd = reflection(d);
            dfs(start,nx,ny,nd);
        }
    }

    private static int reflection(int d) {
        if(d==1){
            return 0;
        }
        else if(d==2){
            return 3;
        }
        else if(d==3){
            return 2;
        }
        else {
            return 1;
        }
    }

    private static int findPoint(int nx, int ny) {
        for(int i=1; i< points.length; i++){
            if(nx==points[i].x && ny==points[i].y) return i;
        }
        return 0;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+2][M+2];
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < M+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        points = new Point[2*N+2*M+1];
        int x = 0;
        int y = 0;
        for (int i = 1; i < 2*N+2*M+1; i++) {
            if(i<=N){
                x++;
                points[i] = new Point(x, y, 1);
                if(i==N) x++;
            }
            else if(i<=N+M){
                y++;
                points[i] = new Point(x,y,0);
                if(i==N+M) y++;
            }
            else if(i<=N+M+N){
                x--;
                points[i] = new Point(x, y, 3);
                if(i==N+M+N) x--;
            }
            else {
                y--;
                points[i] = new Point(x,y,2);
            }
        }
        answer = new int[2*N+2*M+1];
//        System.out.println(Arrays.toString(points));
    }
}
