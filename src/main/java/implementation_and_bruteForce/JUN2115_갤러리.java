package implementation_and_bruteForce;

import implementation_and_bruteForce.JUN2589_보물섬.Pair;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN2115_갤러리 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*

    ### 입력
    - 가로 M, 세로 N => map
    - 벽 X, 빈 .

    ### 해결 방법 1
    - 빈 공간을 탐색
    - 빈 공간의 4방을 탐색하여 빈 공간과 맞닿은 벽 true 체크
    - true 의 길이를 수학적으로 로직을 만들어서 최대 개수 파악
     */
    static int M, N;
    static String[][] map;
    static boolean[][] visited;
    static boolean[][] isWall;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new String[M][N];
        visited = new boolean[M][N];
        isWall = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].equals(".")){
                    wallOrEmpty(new Pair(i, j));
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
//                if(isWall[i][j]){
//                    howLong(new Pair(i,j));
//                }
                if(isWall[i][j]) cnt++;
                else cnt = 0;
            }
        }
    }

    private static void howLong(Pair start) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);


    }

    private static void wallOrEmpty(Pair start) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=M || ny>=N) continue;
                if(map[nx][ny].equals("X")){
                    isWall[nx][ny] = true;
                    continue;
                }
                if(map[nx][ny].equals(".")){
                    queue.offer(new Pair(nx, ny));
                }

            }
        }
    }

}
