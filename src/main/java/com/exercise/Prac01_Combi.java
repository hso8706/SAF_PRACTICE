package com.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class Prac01_Combi {
    static int N, R, totalCount;
    static int[] totalArr, selectedArr;
    // Combination 에서는 isSelected 가 필요 없음
    // isSelected 가 필요한 이유는 이전에 사용했던 수를 확인하고 다시 사용할 수 있기 위함인데, 조합은 애초에 사용했던 수를 다시 사용하는 것이 중복된 것이기 때문
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        totalArr = new int[N];
        selectedArr = new int[R];
        for (int i = 1; i < N+1; i++) {
            totalArr[i-1] = i;
        }

        combi(0, 0);
        System.out.println(totalCount);
    }

    private static void combi(int cnt, int start) { // 중복을 막기위해 애초에 시작점을 체크하여 시작점을 변경함
        if(cnt == R){
            System.out.println(Arrays.toString(selectedArr));
            totalCount++;
            return;
        }
        for (int i = start; i < N; i++) {
            selectedArr[cnt] = totalArr[i];
            combi(cnt + 1, i + 1);
        }

    }
}
