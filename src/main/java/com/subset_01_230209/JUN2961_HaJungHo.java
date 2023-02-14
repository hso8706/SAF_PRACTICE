package com.subset_01_230209;

import java.util.Arrays;
import java.util.Scanner;

public class JUN2961_HaJungHo {
    //입력 1. tc ; 1 <= tc <= 10
    //입력 n. 재료의 맛(신맛, 쓴맛), tc개
    static int tc;
    static int[][] ingredient;
    //부분 집합
    static int[] countOfIngredient;
    static boolean[] isSelected;
    static int tasteLen;
    static int[] sour;
    static int[] bitter;
    static int num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tc = sc.nextInt();
        ingredient = new int[tc][2];
        for (int i = 0; i < tc; i++) {
            ingredient[i][0] = sc.nextInt();
            ingredient[i][1] = sc.nextInt();
        }
        countOfIngredient = new int[tc];
        isSelected = new boolean[tc];
        tasteLen = (int)Math.pow(2, tc);
        sour = new int[tasteLen];
        Arrays.fill(sour, 1);
        bitter = new int[tasteLen];
        num = 0;
        generateSubset(0);
        findGoodFood();

    }

    private static void findGoodFood() {
        int min = Integer.MAX_VALUE; // 최대에서 시작하는 법
        for (int i = 0; i < tasteLen-1; i++) {
            if(Math.abs(sour[i] - bitter[i]) == 0) {
                System.out.println(Math.abs(sour[i] - bitter[i]));
                return;
            }
            if(Math.abs(sour[i] - bitter[i]) < min) min = Math.abs(sour[i] - bitter[i]);
        }
        System.out.println(min);
    }

    private static void generateSubset(int cnt) {
        if(cnt == countOfIngredient.length){
            for (int i = 0; i < tc; i++) {
                if (isSelected[i]){
                    sour[num] *= ingredient[i][0];
                    bitter[num] += ingredient[i][1];
                }
            }
            num++;
            return;
        }
        isSelected[cnt] = true;
        generateSubset(cnt + 1);
        isSelected[cnt] = false;
        generateSubset(cnt + 1);

    }
}
