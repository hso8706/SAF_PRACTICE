package cert.forth;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution_Sangil {
    static int n;
    static int m;
    static int[][] map;
    static int[][] originalMap;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t < T + 1; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            originalMap = new int[n][n];
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    originalMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (originalMap[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < n; l++) {
                                map[l] = originalMap[l].clone();
                            }
                            max = Math.max(max, bfs(i, j, k));
                        }
                    }
                }
            }
//            System.out.println(max);
            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        System.out.println(sb);

    }

    private static int bfs(int x, int y, int d) {
        int count = 0;
        for (int i = 2; i <= m + 1; i++) {

            if (map[x][y] == 0) {
                if (checkMove(x, y, i)) {
                    map[x][y] = i;
                }
            } else {
                if (map[x][y] + 5 < i) {
                    count++;
                    map[x][y] = 0;
                }
            }

            if (move(x, y, d + 1, i)) {
                d += 1;
                if (d == 4) {
                    d = 0;
                }
                x += dx[d];
                y += dy[d];
                continue;
            }
            if (move(x, y, d, i)) {
                x += dx[d];
                y += dy[d];
                continue;
            }
            if (move(x, y, d - 1, i)) {
                d -= 1;
                if (d == -1) {
                    d = 3;
                }
                x += dx[d];
                y += dy[d];
                continue;
            }
            if (move(x, y, (d + 2) % 4, i)) {
                d = (d + 2) % 4;
                x += dx[d];
                y += dy[d];
            }
        }
        return count;
    }

    private static boolean move(int x, int y, int d, int day) {
        if (d == 4) {
            d = 0;
        }
        if (d == -1) {
            d = 3;
        }

        int tx = x + dx[d];
        int ty = y + dy[d];
        if (tx < 0 || ty < 0 || tx >= n || ty >= n || map[tx][ty] == 1) {
            return false;
        }
        if (map[tx][ty] == 0) {
            return true;
        }
        if (map[tx][ty] != 0 && map[tx][ty] + 5 < day) {
            return true;
        }
        return false;
    }

    private static boolean checkMove(int x, int y, int day) {
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= n || ty >= n || map[tx][ty] == 1) {
                continue;
            }
            if(map[tx][ty] ==0 ||  (map[tx][ty] != 0 && map[tx][ty] + 5 < day)){
                return true;
            }
        }
        return false;
    }
}
