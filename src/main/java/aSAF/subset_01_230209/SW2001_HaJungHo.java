package aSAF.subset_01_230209;

import java.util.Scanner;

public class SW2001_HaJungHo {
    static int T, N, M;
    static int[] deadFlies;
    static int[][] flies;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int tc = 1; tc < T+1; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            flies = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    flies[i][j] = sc.nextInt();
                }
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N - M; i++) {
                for (int j = 0; j < N - M; j++) {
                    int tempSum = slide(M, i, j);
                    if(tempSum > max){
                        max = tempSum;
                    }
                }
            }
            System.out.println("#" + tc + " " + max);
        }

    }
    private static int slide(int M, int i, int j){
        int sum = 0;
        for (int k = i; k < i+M; k++) {
            for (int l = j; l < j+M; l++) {
                sum += flies[k][l];
            }
        }
        return sum;
    }
}
