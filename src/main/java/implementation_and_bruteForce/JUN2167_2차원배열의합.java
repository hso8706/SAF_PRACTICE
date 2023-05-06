package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN2167_2차원배열의합 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 2차원 배열의 합
    - A(i,j)와 B(x,y)의 관계
        - A랑 B는 같은 row, col 에 있을 수 있음
        - A는 좌측 상단, B는 우측 하단이 기본
            - 즉, col: i<=x && row: j<=y 를 구해주면 된다.
     */
    static int N, M, K;
    static int[][] secDim;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        secDim = new int[N+1][M+1];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < M+1; j++) {
                secDim[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        K = Integer.parseInt(bf.readLine());
        for (int n = 0; n < K; n++) {
            st = new StringTokenizer(bf.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int sum = sumOfPart(i,j,x,y);
            bw.write(sum+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static int sumOfPart(int i, int j, int x, int y) {
        int s = 0;
        for (int row = i; row <= x; row++) {
            for (int col = j; col <= y ; col++) {
                s += secDim[row][col];
            }
        }
        return s;
    }
}
