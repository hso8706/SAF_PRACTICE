package com.recur_03_230208;

import java.util.Scanner;

public class JUN15650_HaJungHo {
    static int N, R;
    static int[] numbers;
    static int[] results;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        numbers = new int[N];
        results = new int[R];

        for (int i = 0; i < N; i++) {
            numbers[i] = i+1;
        }

        combi(0, 0);
    }

    private static void combi(int cnt, int start) {
        if(cnt == R){
            for(int r : results){
                System.out.print(r + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            results[cnt] = numbers[i];
            combi(cnt + 1, i + 1);
        }
    }
}
