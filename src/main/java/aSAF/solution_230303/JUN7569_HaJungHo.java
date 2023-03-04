package aSAF.solution_230303;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN7569_HaJungHo {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 토마토마토
    - 7576 토마토 업그레이드 문제

    문제 해결 포인트
    - x, y, z 축
    - 6방 탐색
     */
    static int M, N, H; // M: 가로, N: 세로, H: 높이
    static int[][][] boxes; // 3차원 배열
    static boolean[][][] visited;
    static Queue<int[]> queue = new ArrayDeque<>(); // bfs, idx=> 0: 가로, 1: 세로, 2: 높이, 3: days
    static int maxDays;
    static int[] dn = new int[]{1,0,-1,0,0,0};
    static int[] dm = new int[]{0,1,0,-1,0,0};
    static int[] dh = new int[]{0,0,0,0,1,-1}; // 2차원 상우하좌 + 3차원 상하

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        boxes = new int[H][N][M]; //높이, 세로, 가로
        visited = new boolean[H][N][M]; //높이, 세로, 가로

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0; k < M; k++) {
                    boxes[i][j][k] = Integer.parseInt(st.nextToken());
                    if(boxes[i][j][k] == 1){
                        queue.offer(new int[]{k, j, i, 0});
                    }
                }
            }
        }
        maxDays = Integer.MIN_VALUE;
        bfs();
        if(isIt()) bw.write(-1 + "");
        else bw.write(maxDays + "");
        bw.flush();
        bw.close();
    }

    private static boolean isIt() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(boxes[i][j][k] == 0) cnt++;
                }
            }
        }
        return cnt != 0;
    }

    private static void bfs() {
        while(!queue.isEmpty()){
            int[] current = queue.poll(); //m: 가로, n: 세로, h: 높이
            int cm = current[0], cn = current[1], ch = current[2], days = current[3];
            maxDays = Math.max(maxDays, days);

            for (int i = 0; i < 6; i++) {
                int nm = cm+dm[i];
                int nn = cn+dn[i];
                int nh = ch+dh[i];
                if (nm<0 || nn<0 || nh<0 || nm>=M || nn>=N || nh>=H) continue;
                if (!visited[nh][nn][nm] && boxes[nh][nn][nm]==0){
                    boxes[nh][nn][nm]=1;
                    queue.offer(new int[]{nm,nn,nh,days+1});
                }
            }
        }
    }
}
