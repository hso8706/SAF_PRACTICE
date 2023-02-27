package aSAF.disJoinSet_230227;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN2567_2차월말 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[][] board = new int[101][101]; // 100 + 1 : 4방 탐색 여유 공간
    static boolean[][] visited = new boolean[101][101]; // 100 + 1 : 4방 탐색 여유 공간
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int len;

    public static void main(String[] args) throws IOException {
        len = 0;
        int n = Integer.parseInt(bf.readLine());
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            fillBlack(x, y);
        }
        for (int i = 0; i < 101; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        measurementBFS(x, y);
        System.out.println(len);

    }

    private static void measurementBFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx<0 || nx>100 || ny<0 || ny>100 || visited[nx][ny]) continue;
                if (board[nx][ny] == 0) len++;
                else if(board[nx][ny] == 1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static void fillBlack(int x, int y) {
        for (int i = x; i < x + 10; i++) {
            for (int j = y; j < y + 10; j++) {
                board[i][j] = 1;
            }
        }
    }
}
