package aSAF.DynamicProgramming2_230329;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN1600_HaJungho {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 말이 되고픈 원숭이
    - 왼쪽 위에서 오른쪽 아래로만 가야한다. => 돌아갈 필요가 없음
        - 말의 이동 방향 => 종류 2개 : 1. 우+대각 / 2. 하+대각
        - 원숭이의 이동 방향 => 종류 2개 : 1. 우 / 2. 하
    - 장애물은 못넘음(말의 이동으로도 못넘음)
    - 말의 이동 횟수는 K번으로 제한
        - 말의 이동은 항상 원숭이의 이동을 동반하고, 둘이 합쳐서 1회 이동으로 간주
        - 순서는 원숭이 이동이 먼저인 듯 함.
    - 최소 동작수 출력이므로 bfs 방법 사용

    ### 실수
    1. 가로 입력, 세로 입력 순서 실수
    2. 말 이동 + 원숭이 이동이 하나가 아니라 각각을 하나로 친다는 얘기
    3. 오른쪽 아래로만 가기 때문에 원숭이, 말 움직임을 제한했지만 사방으로 움직여서 도착만 하면 됨.
        - 말만 이동하면 될 듯.
     */
    static int K, H, W; // K: 말의 이동, H: 세로, W: 가로
    static int[][] field;
    static boolean[][][] visited;
    static int[] mh = new int[]{0, 1, 0, -1}; // 원숭이, 우 하 좌 상
    static int[] mw = new int[]{1, 0, -1, 0};

    static int[] hh = new int[]{2, -1, 1, -2, -2, -1, 1, 2};
    static int[] hw = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        field = new int[H][W];
        visited = new boolean[K+1][H][W]; // K+1: 말처럼 이동했을 경우
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < W; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][0][0] = true;
        int result = moveLikeHorseBFS(new int[]{0, 0, 0, 0});
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static int moveLikeHorseBFS(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int ch = current[0];
            int cw = current[1];
            int cnt = current[2];
            int houseMove = current[3];
            if (ch == H - 1 && cw == W - 1) {
                return cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nh = ch + mh[i];
                int nw = cw + mw[i];
                if (nh < 0 || nw < 0 || nh >= H || nw >= W || field[nh][nw] == 1) continue;
                if (!visited[houseMove][nh][nw]) {
                    queue.offer(new int[]{nh, nw, cnt + 1, houseMove});
                    visited[houseMove][nh][nw] = true;
                }
            }
            if (houseMove < K) { // 말의 이동이 가능한 경우; 추가 이동// 우하, 좌하, 좌상, 우상
                for (int i = 0; i < 8; i++) {
                    int nh = ch + hh[i];
                    int nw = cw + hw[i];
                    if (nh < 0 || nw < 0 || nh >= H || nw >= W || field[nh][nw] == 1) continue;
                    if (!visited[houseMove][nh][nw]) {
                        queue.offer(new int[]{nh, nw, cnt + 1, houseMove+1});
                        visited[houseMove][nh][nw] = true;
                    }
                }
            }
        }
        return -1;
    }
}
