package solving;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN16918_봄버맨 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    R, C 직사각형
    3초 뒤 폭발
    N초
     */

    static int R, C, N;
    static String[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new String[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[j];
                if(map[i][j].equals("O")) makeEmpty(i, j);
            }
        }

        if(N == 1){
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write(map[i][j] + "");
                }
                bw.write("\n");
            }
        }
        else if(N%2 == 0){
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write("O");
                }
                bw.write("\n");
            }
        }
        else {
            for (int t = 3; t <= N; t+=2) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if(visited[i][j]) map[i][j] = ".";
                        else map[i][j] = "O";
                    }
                }

                visited = new boolean[R][C];

                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if(map[i][j].equals("O")) {
                            makeEmpty(i,j);
                        }
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write(map[i][j] + "");
                }
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();

    }

    private static void makeEmpty(int cx, int cy) {
        visited[cx][cy] = true;

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
            visited[nx][ny] = true;
        }
    }

}