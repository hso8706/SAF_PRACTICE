package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN15683_감시 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] dx = new int[]{ -1, 0, 1, 0 };
    static int[] dy = new int[]{ 0, 1, 0, -1 };

    static int N, M;
    static int[][] map;
    /*
    ### 감시
    - 사각 지대의 최소 크기를 출력
     */

    public static void main(String[] args) throws IOException {
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


    }

}
