package com.exercise.prac01;

import java.util.Scanner;

public class Prac01_Subs {
    static int N, totalCount;
    static int[] arr;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        isSelected = new boolean[N];

        subs(0);
        System.out.println(totalCount);

    }

    private static void subs(int cnt) {
        if (cnt == N){
            totalCount++;
            for (int i = 0; i < N; i++) {
                System.out.print((isSelected[i] ? arr[i] : "X") + "\t");
            }
            System.out.println();
            return;
        }

        isSelected[cnt] = true;
        subs(cnt + 1);
        isSelected[cnt] = false;
        subs(cnt + 1);
    }
}
