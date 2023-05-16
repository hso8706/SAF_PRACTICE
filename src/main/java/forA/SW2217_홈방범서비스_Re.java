package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW2217_홈방범서비스_Re {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 마름모 영역 서비스
    - 운영 비용 : 마름모 칸 수 => k*k + (k-1)*(k-1)
        - 1, 5, 13, 25 ,...
    - 도시(map)을 벗어난 영역에 서비스를 제공해도 운영 비용은 똑같음. => 운영 비용은 무슨 일이 있어도 변함이 없다. 오로지 k에만 영향을 받음
    - M : 각 집에서 서비스에 대해 낼 수 있는 비용
    - 보안 회사 이익 = (서비스 영역에 해당하는 집 수 * M) - 운영 비용
    - 보안 회사 이익이 양수인 시점일 때, 서비스를 제공받는 집의 수가 가장 많을 때의 집의 수 출력
        - 시작점 불분명 => 완탐
        - 양수인 시점일 때 => bfs 진행하며 마름모 영역이 완성되는 시점에서 확인

    ### 해결이 필요한 부분
    - k 영역이 변하는 시점에 체크해야하는데 어떻게 체크해야할 지 헤매는 중
         - dfs 로 해결 시도
     */
    static class Pair {

        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int T, N, M;
    static int[][] map;
    static boolean[][] visited;
    static int maxValue;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int houses;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T + 1; t++) {
            bw.write("#" + t + " ");

            //초기화
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            maxValue = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k < 2 * N; k++) {
                        visited = new boolean[N][N];
                        houses = map[i][j];
                        visited[i][j] = true;
                        dfs(new Pair(i, j), 1, k); // 포함되는 집 개수 구하기
                        int profit = houses * M - (k * k + (k - 1) * (k - 1));
                        if (profit > 0) {
                            maxValue = Math.max(maxValue, houses);
                        }
                    }
                }
            }
            bw.write(maxValue + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(Pair current, int cnt, int len) {
        if (cnt == len) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = current.x + dx[i];
            int ny = current.y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }
            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                houses += map[nx][ny];
                dfs(new Pair(nx, ny), cnt + 1, len);
                visited[nx][ny] = false;
            }
        }
    }
}