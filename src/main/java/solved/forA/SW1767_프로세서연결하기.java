package solved.forA;

import java.io.*;
import java.util.StringTokenizer;

public class SW1767_프로세서연결하기 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /*
    - N x N
    - core : 1 ~ 12
    - dfs
        - 상, 우, 하, 좌 순으로 끝에 닿을때까지
        - 하나의 core 를 완성시킨 후 다음 코어
        - 전선은 2로 마킹하고 2의 개수를 카운팅 => 최소 개수 갱신
        - core 가 가장자리에 있다면 넘어갈 것
     */
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int T, N;
    static Pair[] core;
    static int[][] mexinos;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            bw.write("#" + tc + " ");

            N = Integer.parseInt(bf.readLine());
            mexinos = new int[N + 1][N + 1];
            core = new Pair[13]; // 1 ~ 12
            int idx = 0;
            for (int i = 1; i < N + 1; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j < N + 1; j++) {
                    mexinos[i][j] = Integer.parseInt(st.nextToken());
                    if (mexinos[i][j] == 1) {
                        if (i == 1 || j == 1 || i == N || j == N) continue;
                        core[idx] = new Pair(i, j);
                        idx++;
                    }
                }
            }

            core_dfs(0);
        }
    }

    private static void core_dfs(int i) {
        if(core[i] == null){
            return;
        }

        int cx = core[i].x;
        int cy = core[i].y;
    }
}
