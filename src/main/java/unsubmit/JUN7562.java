package unsubmit;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN7562 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 나이트의 이동
    - 시작점에서 끝지점까지 이동하는 최소 이동거리 출력
        - bfs로 거리 체크하며 도달하기
     */
    static int T, l; //T: tc, l: board 크기
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = new int[]{-2,-1,1,2,2,1,-1,-2};
    static int[] dy = new int[]{1,2,2,1,-1,-2,-2,-1};
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            l = Integer.parseInt(bf.readLine());
            board = new int[l][l];
            visited = new boolean[l][l];
            st = new StringTokenizer(bf.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            bfs(start[0], start[1], 0);
        }
        bw.flush();
        bw.close();
    }

    private static void bfs(int x, int y, int step) throws IOException {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, step});
        visited[x][y] = true;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int cs = current[2];
            if(cx == end[0] && cy == end[1]){
                bw.write(cs + "\n");
                return;
            }

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=l || ny>=l) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cs+1});
                }
            }
        }
//        bw.write(0 + "\n");
    }
}
