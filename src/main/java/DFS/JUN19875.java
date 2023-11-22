package DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN19875 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
//    static ArrayList<Character> route;
    static Deque<Character> route;
    static int steps;
    static int[] dr = new int[]{-1,0,1,0};
    static int[] dc = new int[]{0,1,0,-1};

    public static void main(String[] args) throws IOException {

        init();
        visited[1][1] = true;
        route.offerFirst(map[1][1]);
        dfs(1,1,new boolean[R+1][C+1]);

        System.out.println(steps);
    }

    private static void dfs(int r, int c, boolean[][] visited) {
        steps = Math.max(steps, route.size());
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<1 || nc<1 || nr>R || nc>C) continue;
            if(!visited[nr][nc] && !route.contains(map[nr][nc])){
                route.offerFirst(map[nr][nc]);
                visited[nr][nc] = true;
                dfs(nr,nc, visited);
                route.pollFirst();
                visited[nr][nc] = false;
            }
        }
    }

    private static void init() throws IOException {

        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C+1];

        for (int i = 1; i < R+1; i++) {
            char[] inputs = bf.readLine().toCharArray();
            for (int j = 1; j < C+1; j++) {
                map[i][j] = inputs[j-1];
            }
        }
        visited = new boolean[R+1][C+1];
//        route = new ArrayList<>();
        route = new ArrayDeque<>();
        steps = 0;
    }

}
