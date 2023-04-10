package exercise.example;

import java.util.Arrays;
import java.util.Scanner;

public class SubsetOthers {
    static int N, totalCnt;
    static int[] totals;
//    static boolean[] isSelected;
    static int[] isSelected;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        totals = new int[N];
        isSelected = new int[N];
        for (int i = 0; i < N; i++) {
            totals[i] = i+1;
        }

        subset(0);
        System.out.println(totalCnt);
    }

    private static void subset(int cnt) {
        if(cnt == N){
            System.out.println(Arrays.toString(isSelected));
            totalCnt++;
            return;
        }

        isSelected[cnt] = 0;
        subset(cnt+1);
        isSelected[cnt] = 1;
        subset(cnt+1);
        isSelected[cnt] = 2;
        subset(cnt+1);
    }
}