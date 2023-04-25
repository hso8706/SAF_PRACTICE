package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN3190_뱀 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 뱀
    - 머리 다음칸 이동
        - 사과 있다면 꼬리 유지
        - 사과 없다면 꼬리 당기기
     */
    static int[][] board;
    static int N, K, L; //N: board 크기, K: 사과의 개수, L: 뱀 방향 변환 정보
    static int[] X;
    static String[] C;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        board = new int[N+1][N+1];

        K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
        }

        L = Integer.parseInt(bf.readLine());
        X = new int[L];
        C = new String[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(bf.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            C[i] = st.nextToken();
        }
    }
}
