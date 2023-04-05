package aSAF.solution02_230405;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN3055_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 탈출
    - 지도 : R x C
    - . : 빈곳 / * : 물 / X : 돌
    - D: 비버의 굴 / S: 고슴도치 위치
    - 고슴도치 사방 탐색 가능(매 분)
    - 물 사방 확장(매 분)
    - 물 : 비버 소굴, 돌 통과 못함
    - 고슴도치 : 물, 돌 통과 못함
     */
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] wVisited;
    static class Pair{
        int x;
        int y;
        int min;

        public Pair(int x, int y, int min) {
            this.x = x;
            this.y = y;
            this.min = min;
        }
    }
    static Queue<Pair> waterS = new ArrayDeque<>();
    static Pair ratS;
    static Pair hole;
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    static int floodTime;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        wVisited = new boolean[R][C];
        fillTheMap();

        int check = findTheRoute(ratS);
        if(check == -1) bw.write("KAKTUS");
        else bw.write(check + "");
        bw.flush();
        bw.close();
    }

    private static int findTheRoute(Pair ratS) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(ratS);
        visited[ratS.x][ratS.y] = true;
        floodTime = 0;

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            int cm = current.min;
            if(hole.x == cx && hole.y == cy) return cm;

            if(floodTime == cm){
                floodTime++;
                int endP = waterS.size();
                for (int i = 0; i < endP; i++) {
                    flood(waterS.poll()); //고슴도치 이동 전 홍수 작업
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
                if(map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new Pair(nx, ny, cm+1));
                }
            }
        }
        return -1;
    }

    private static void flood(Pair cw) {
        for (int i = 0; i < 4; i++) {
            int nx = cw.x + dx[i];
            int ny = cw.y + dy[i];
            if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
            if(map[nx][ny] == 'D' || map[nx][ny] == 'X') continue;
            if(!wVisited[nx][ny]){
                wVisited[nx][ny] = true;
                map[nx][ny] = '*';
                waterS.offer(new Pair(nx, ny, 0));
            }
        }
    }

    private static void fillTheMap() throws IOException {
        for (int i = 0; i < R; i++) {
            char[] temp = bf.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[j];
                if(map[i][j] == 'S') ratS = new Pair(i,j,0);
                else if(map[i][j] == 'D') hole = new Pair(i,j,0);
                else if(map[i][j] == '*') {
                    wVisited[i][j] = true;
                    waterS.offer(new Pair(i,j,0));
                }
            }
        }
    }
}
