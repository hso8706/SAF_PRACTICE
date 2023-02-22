package aSAF.divide_230220;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1074_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, r, c;
    static int[][] board;
    static int num = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken()); // 2^N, N : 지수
        r = Integer.parseInt(st.nextToken()); // r행
        c = Integer.parseInt(st.nextToken()); // c열
        board = new int[(int)Math.pow(2, N)][(int)Math.pow(2, N)];
        // 2^N x 2^N 2차원 배열 (1 < N)
        // r행 c열을 몇 번째로 방문하는지 출력
        // 0행 0열은 0번째 방문으로 취급
        // 행과 열의 시작은 0
        // 문제 쪼개기 필요
            // 2x2 배열로 될 때까지 쪼개기
        //1. 배열을 쪼개면서 칸 채우는 방법
        //2. 사분면을 이용한 찾기 방법
//        zArr(N, 0 ,0);
//        findMap(N, r, c);
    }

//    private static void findMap(int n, int r, int c) {
//        if(n==1){
//            return;
//        }
//        int halfSize = n/2;
//        //1
//        if (r< halfSize && c < halfSize){
//            findMap(halfSize, r, c);
//        }
//        //2
//        else if (r < halfSize && halfSize <= c) {
//            findMap(halfSize, r, c);
//
//        }
//        //3
//        else if (r>= halfSize && c > halfSize) {
//            findMap(halfSize, r, c);
//
//        }
//        //4
//        else if (r >= halfSize && c>= halfSize) {
//            findMap(halfSize, r, c);
//
//        }
//
//    }

//    private static void zArr(int n, int x, int y) {
//        if((n-1) == 0){
//            for (int i = x; i < 2+x; i++) {
//                for (int j = y; j < 2+y; j++) {
//                    System.out.println("내부: " + i + ", " + j);
//                    board[i][j] = num;
//                    num++;
//                }
//            }
//            return;
//        }
//        for (int i = 0; i < 2 + 0; i++) {
//            for (int j = 0; j < 2 + 0; j++) {
//                System.out.println("n-1: " + (n-1));
//                System.out.println("외부: " + i + ", " + j);
//                zArr(n-1, i * (int)Math.pow(2, n-1), j * (int)Math.pow(2, n-1)) ;
//            }
//        }
//    }
}
