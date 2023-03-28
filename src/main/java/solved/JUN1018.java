package solved;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN1018 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 체스판 다시 칠하기
    - M x N 보드 => 8 x 8 보드로 수정

    ### 문제 해결
    - 8 x 8 로 만들 수 있는 경우의 수 모두 순회
    - 개중 변경이 최소인 상황의 개수 갱신
    - bfs 로 순회하며 인접 체크 및 변경
     */
    static int N, M; // N: 세로, M: 가로
    static String[][] originBoard; // M x N
    static int[][] cutBoard; // 8 X 8 보드
    static boolean[][] visited;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originBoard = new String[N][M];
        //origin board 초기화
        for (int i = 0; i < N; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < M; j++) {
                originBoard[i][j] = temp[j];
            }
        }
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                cutting(i, j);
                bfs(i, j);
                bfs(i + 7, j);
                bfs(i, j + 7);
                bfs(i + 7, j + 7);
            }
        }
        bw.write(pq.poll() + "");
        bw.flush();
        bw.close();
    }

    private static void bfs(int i, int j) {
        visited = new boolean[N][M];
        int cnt = 0;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j, cutBoard[i][j]});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int cc = current[2];

            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || cutBoard[nx][ny] == 0) continue;
                int nc = cutBoard[nx][ny];
                if (!visited[nx][ny]) {
                    if (cc != nc) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, nc});
                    } else {
                        visited[nx][ny] = true;
                        //                        cutBoard[nx][ny] = cc == 1? 2: 1;
                        //                        nc = cutBoard[nx][ny];
                        nc = cc == 1 ? 2 : 1;
                        cnt++;
                        queue.offer(new int[]{nx, ny, nc});
                    }
                }
            }
        }
        pq.offer(cnt);
    }

    private static void cutting(int n, int m) {
        cutBoard = new int[N][M];
        for (int i = n; i < n + 8; i++) {
            for (int j = m; j < m + 8; j++) {
                if (originBoard[i][j].equals("W")) cutBoard[i][j] = 1; // W = 0
                else cutBoard[i][j] = 2; // B = 1
            }
        }
    }
}
