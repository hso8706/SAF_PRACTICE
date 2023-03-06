package solution;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN2146 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] dx = new int[]{1, 0, -1, 0}; // dx가 세로
    static int[] dy = new int[]{0, 1, 0, -1}; // 상우하좌
    static int N; // 지도 크기
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        int cnt = 1; // 섬의 개수 및 구분

        fillMap();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]==0 || visited[i][j]) continue;
                division(new int[]{i, j}, cnt);
                cnt++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
            }
        }
    }

    private static void division(int[] current, int cnt) {
        Queue<int[]> queue = new ArrayDeque<>(); // 0: x(세로)좌표, 1: y 좌표
        queue.offer(current);

        while(!queue.isEmpty()){
            int[] c = queue.poll();
            int cx = c[0];
            int cy = c[1];
            map[cx][cy] = cnt;
            visited[cx][cy] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(!visited[nx][ny] && map[nx][ny] != 0){
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static void fillMap() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
