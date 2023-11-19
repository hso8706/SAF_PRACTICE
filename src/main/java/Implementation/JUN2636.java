package Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN2636 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M;
    static int[][] map;
    static int beforeEnd;
    static boolean isRemained;
    static int endTime;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();

        while (isRemained){
            logic_bfs(0,0);
        }

        bw.write(endTime +"\n");
        bw.write(beforeEnd + "\n");
        bw.flush();
        bw.close();
    }

    private static void logic_bfs(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        int meltedCnt = 0;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    if(map[nx][ny] == 1){
                        meltedCnt++;
                        map[nx][ny] = 0;
                    }
                    else if(map[nx][ny] == 0){
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        if(meltedCnt == 0) isRemained = false;
        else {
            beforeEnd = meltedCnt;
            endTime++;
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isRemained = true;
    }

}
