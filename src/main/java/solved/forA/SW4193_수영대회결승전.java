package solved.forA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW4193_수영대회결승전 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 수영대회 결승전
    - 바다 : N x N
    - 유지되는 장애물, 생겼다 없어졌다하는 장애물 존재
    - 장애물 : 1
    - 소용돌이 : 2, 생성된 시간 포함 2초 유지 후 1초 동안 사라짐
        - 소용돌이가 사라지고 난 걸 확인하고 지나갈 수 있음(사라짐과 동시에 이동은 불가)
     */

    //input section
    static int T, N; // T: tc, N: Sea size
    static int[][] sea;
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Point startP, endP;

    //bfs section
    static int[] dx = new int[]{1,0,-1,0}; // 세로축
    static int[] dy = new int[]{0,1,0,-1}; // 가로축
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#"+tc+" ");
            N = Integer.parseInt(bf.readLine());
            sea = new int[N][N];
            visited = new boolean[N][N];
            // sea 배열 내용을 입력받는 메서드
            fillTheSea();
            startP = inputPoint();
            endP = inputPoint();
            //bfs
            int minTime = goSamsung_bfs();
            bw.write(minTime+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static int goSamsung_bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startP.x, startP.y, 0});
        visited[startP.x][startP.y] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int ct = current[2];
            if(cx == endP.x && cy == endP.y) {
                return ct;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(!visited[nx][ny]){
                    if(sea[nx][ny] == 1){
                        continue;
                    }
                    else if(sea[nx][ny] == 2){
                        if(ct%3 == 2){ // 소용돌이가 사라지는 타이밍
                            visited[nx][ny] = true;
                            queue.offer(new int[]{nx, ny, ct+1});
                        }
                        else{
                            //방문처리 없이 현재 자리에서 대기할 것.
                            queue.offer(new int[]{cx,cy,ct+1});
                        }
                    }
                    else {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, ct+1});
                    }
                }
            }
        }
        return -1;
    }

    private static Point inputPoint() throws IOException {
        st = new StringTokenizer(bf.readLine());
        Point temp = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        return temp;
    }

    private static void fillTheSea() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
