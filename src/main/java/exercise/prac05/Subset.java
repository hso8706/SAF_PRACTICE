package exercise.prac05;

import java.util.Scanner;

public class Subset {
    static int N, totalCnt;
    static int[] totals;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        totals = new int[N];
        isSelected = new boolean[N];
        for (int i = 0; i < N; i++) {
            totals[i] = i+1;
        }

        subset(0);
        System.out.println(totalCnt);
    }

    private static void subset(int cnt) {
        if(cnt == N){
            for (int i = 0; i < N; i++) {
                System.out.print((isSelected[i] ? totals[i] : "x") + " ");
            }
            System.out.println();
            totalCnt++;
            return;
        }

        isSelected[cnt] = true;
        subset(cnt+1);
        isSelected[cnt] = false;
        subset(cnt+1);
    }
}