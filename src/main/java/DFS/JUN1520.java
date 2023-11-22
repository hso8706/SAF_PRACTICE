package DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN1520 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int M, N;
    static int[][] map;
    static boolean[][] visited;
    static int canRoute;
    static int[] dm = new int[]{-1,0,1,0};
    static int[] dn = new int[]{0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();

        visited[0][0] = true;
        dfs(0,0);
        System.out.println(canRoute);
    }

    private static void dfs(int m, int n) {
        if(m == M-1 && n == N-1){
            canRoute++;
            return;
        }

        int currentHeight = map[m][n];
        for (int i = 0; i < 4; i++) {
            int nm = m + dm[i];
            int nn = n + dn[i];
            if(nm<0 || nn<0 || nm>=M || nn>=N)continue;
            int nextHeight = map[nm][nn];
            if(!visited[nm][nn] && currentHeight > nextHeight){
                visited[nm][nn] = true;
                dfs(nm, nn);
                visited[nm][nn] = false;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[M][N];
        canRoute = 0;
    }
}
