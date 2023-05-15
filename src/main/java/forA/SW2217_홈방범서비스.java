package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SW2217_홈방범서비스 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    이익 = 수익 - 비용
    비용 = k*k + (k-1)*(k-1)
    손해를 보지 않는 경우(이익>0)에서 서비스를 제공받는 최대 집의 개수

    서비스 영역 : bfs로 증가, 손해 범위에서 종료
     */
    static class Pair {

        int x;
        int y;
        int k; // 서비스 영역 단계 확인

        public Pair(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    static int T, N, M; //M: 하나의 집이 지불하는 비용
    static int[][] map;
    static boolean[][][] visited;
    static int expense;
    static int incomeBase;
    static int houses;
    static int[] dx = new int[]{ 1, -1, 0, 0 };
    static int[] dy = new int[]{ 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T + 1; t++) {
            bw.write("#" + t + " ");
            houses = 0;

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited = new boolean[2][N][N];
                    expense = 0;
                    incomeBase = 0;
                    securityService(new Pair(i, j, 1));
                }
            }
            bw.write(houses + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void securityService(Pair start) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[0][start.x][start.y] = true;
        visited[1][start.x][start.y] = true;
        int flag = 1;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            int ck = current.k;
//            System.out.println(visited[1]);

            if (flag < ck) { //범위 확장이 종료된 시점, 범위 변화가 일어난 상황

                expense = flag * flag + (flag - 1) * (flag - 1);
                incomeBase = contained();

                int profit = (incomeBase * M) - expense;
                if (profit > 0) { // 이익인 시점
                    houses = Math.max(houses, incomeBase); // houses 갱신
                }
                flag = ck; //flag 갱신
            }

            visited[1][cx][cy] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (!visited[0][nx][ny]) {
                    visited[0][nx][ny] = true;
                    queue.offer(new Pair(nx, ny, ck + 1));
                }
            }
        }
    }

    private static int contained() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[1][i][j]) {
                    cnt += map[i][j];
                }
            }
        }
        return cnt;
    }
}
