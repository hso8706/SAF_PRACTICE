package solution;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1953 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T; // T: 테스트 케이스
    static int N, M; // map 의 크기, 세로 x 가로
    static int R, C; // 맨홀 위치, 세로 x 가로
    static int L; // 소요 시간
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{1, 0, -1, 0}; // 상 방향 부터 시계방향으로
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            bw.write("#" + tc + " ");
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            fillMap();
            bfsSearch();
        }

    }

    private static void bfsSearch() {
        Queue<int[]> queue = new ArrayDeque<>(); // x, y, pipeType, time
        queue.offer(new int[]{R, C, map[R][C], 1});
        visited[R][C] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int cType = current[2];
            int cTime = current[3];
            switch (cType) { //이동 가능 부분
                case 1: // 상하좌우
                    for (int i = 0; i < 4; i++) {

                    }
                    break;
                case 2: // 상하
                    for (int i = 0; i < 3; i++) {
                        if(i == 1) continue;
                    }
                    break;
                case 3: // 좌우
                    for (int i = 1; i < 4; i++) {
                        if (i == 2) continue;
                    }
                    break;
                case 4: // 상우
                    for (int i = 0; i < 2; i++) {

                    }
                    break;
                case 5: // 하우, 우하
                    for (int i = 1; i < 3; i++) {

                    }
                    break;
                case 6: // 하좌
                    for (int i = 2; i < 4; i++) {

                    }
                    break;
                case 7: // 상좌, 좌상
                    for (int i = 0; i < 4; i++) {
                        if(i == 1 || i == 2) continue;
                    }
                    break;
            }

        }
    }

    private static void fillMap() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
