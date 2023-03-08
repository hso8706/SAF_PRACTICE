package unsubmit;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN16928 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 뱀과 사다리 게임
    - 1 ~ 100 이 마킹된 10x10 board
    - 주사위 수 만큼 이동
    - 사다리와 뱀이라는 워프존 존재; 편의상 구현에선 사다리가 아래로, 뱀이 위로 워프되게끔 구현

    ### 포인트
    - 2차원이 아닌 1차원으로 해결하면 될 듯 하다.
    - 최소 거리이므로 bfs가 유리할 듯 함.
    - 상황 나누기
        1. 사다리, 뱀 없음
            - 그냥 진행
            - 진행 칸 수가 100 인덱스 이상인 경우에는 종료
        2. 사다리, 뱀 있음
            - 워프
     */
    static int N, M; // N: 사다리의 수, M: 뱀의 수
    static int[] board = new int[101]; // 1 인덱스부터 시작
    static boolean[] visited = new boolean[101]; // 1차원 배열이어도 최소거리 구하는 bfs에서는 갔던 곳을 다시 방문할 필요가 없다.
    static int[] ladder = new int[101]; // 사다리 배열, from 인덱스 to 값, 1~100만 사용
    static int[] snake = new int[101]; // 뱀 배열, 위와 동일
    static int[] dice = new int[]{1, 2, 3, 4, 5, 6}; // 주사위 배열, 주사위 눈 수 만큼 이동하는 배열

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i < 101; i++) {
            board[i] = i;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            ladder[from] = to;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            snake[from] = to;
        }
        bfs(1);
        bw.flush();
        bw.close();
    }

    private static void bfs(int start) throws IOException {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, 0}); // 시작점, step 수
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0]; // 현재 x 좌표
            int cnt = current[1]; // 현재 step cnt
            if (cx == 100) {
                bw.write(cnt + "");
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = cx + dice[i];
                if (nx > 100) continue;
                if (!visited[nx]) {
                    if (isLadder(nx)) {
                        visited[nx] = true;
                        visited[ladder[nx]] = true;
                        queue.offer(new int[]{ladder[nx], cnt + 1});
                    } else if (isSnake(nx)) {
                        visited[nx] = true;
                        visited[snake[nx]] = true;
                        queue.offer(new int[]{snake[nx], cnt + 1});
                    }
                    else {
                        visited[nx] = true;
                        queue.offer(new int[]{board[nx], cnt + 1});
                    }
                }
            }
        }
    }

    private static boolean isSnake(int nx) {
        return snake[nx] != 0;
    }

    private static boolean isLadder(int nx) {
        return ladder[nx] != 0;
    }
}
