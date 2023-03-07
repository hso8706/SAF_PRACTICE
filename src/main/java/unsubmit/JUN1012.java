package unsubmit;

import java.io.*;
import java.util.*;

public class JUN1012 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    배추흰지렁이 문제
    => 섬 개수 파악 문제랑 똑같음

    - 사방 탐색
     */
    static int T; //Test case
    static int M, N, K; //M: 가로길이, N: 세로길이, K: 배추의 개수(간선)
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{-1,0,1,0}; // 상우하좌
    static int[] dy = new int[]{0,1,0,-1};
    static int cnt; // 섬 마킹용 변수

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            cnt = 1;
            for (int i = 1; i < K+1; i++) {
                st = new StringTokenizer(bf.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[x][y] = 1; // 1: 배추가 있다는 표시
            }
            for (int i = 0; i < N; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
            marking();
        }

    }

    private static void marking() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] != 0){
                    bfs(i, j);
                    cnt++;
                }
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[0];
                int ny = cy + dy[0];
                if (nx<0 || ny<0 || nx>N || ny>M) continue;
                if(!visited[nx][ny] && map[nx][ny] != 0) {
                    map[nx][ny] = cnt;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
