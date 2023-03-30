package aSAF.DynamicProgramming3_230330;

import java.io.*;
import java.util.*;

public class JUN14502_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 연구소
    - 벽을 세워 바이러스의 유출을 막음
    - 0: 평지, 1: 벽, 2: 바이러스
    - 세울 수 있는 벽의 개수 == 3개
    - 시간, 메모리 넉넉

    ### 문제 해결
    - 벽을 만드는 메서드 작업(재귀)
    - 벽 3개가 만들어진 시점에 bfs 를 통해서 바이러스 유출시키기
    - 바이러스 유출 후 0으로 남아있는 안전 구역 체크 후 min value 갱신
    - 최종 min value 출력
     */

    static class VirusPoint{
        int x;
        int y;

        public VirusPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M; // N: 세로, M: 가로
    static int[][] lab;
    static int[][] temp;
    static boolean[][] visited;
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,-1,0,1};
    static ArrayList<VirusPoint> vp = new ArrayList<>();
    static int max;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        temp = new int[N][M];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2){
                    vp.add(new VirusPoint(i, j));
                }
            }
        }
        buildWalls(0);
        bw.write(max + "");
        bw.flush();
        bw.close();
    }

    private static void buildWalls(int cnt) {
        if(cnt == 3){
            //복사
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = lab[i][j];
                }
            }
            // 바이러스 전염 과정
            for(VirusPoint start : vp){
                visited = new boolean[N][M];
                infectedBFS(start);
            }
            int safetyZone = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(temp[i][j] == 0) safetyZone++;
                }
            }
            if (max < safetyZone) max = safetyZone;
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(lab[i][j] != 2 && lab[i][j] != 1){
                    lab[i][j] = 1;
                    buildWalls(cnt+1);
                    lab[i][j] = 0;
                }
            }
        }


    }

    private static void infectedBFS(VirusPoint start) {
        Queue<VirusPoint> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            VirusPoint current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(!visited[nx][ny] && lab[nx][ny] == 0){
                    temp[nx][ny] = 2;
                    visited[nx][ny] = true;
                    queue.offer(new VirusPoint(nx, ny));
                }
            }
        }
    }
}
