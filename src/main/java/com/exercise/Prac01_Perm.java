package com.exercise;

import java.util.Scanner;

public class Prac01_Perm {
    static int N, R, totalCount;
    static int[] totalArr;
    static int[] selectedArr;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        totalArr = new int[N];
        isSelected = new boolean[N];
        selectedArr = new int[R];

        perm(0);
        System.out.println(totalCount);
    }

    public static void perm(int cnt){
        if(cnt == R){
            totalCount++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!isSelected[i]){
                selectedArr[cnt] = totalArr[i];
                isSelected[i] = true;
                perm(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}
