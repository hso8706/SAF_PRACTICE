package exercise.prac05;

import java.util.Arrays;
import java.util.Scanner;

public class Permutation {
    static int N, R, totalCnt;
    static int[] totals, selected;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        totals = new int[N];
        selected = new int[R];
        isSelected = new boolean[N];
        for (int i = 0; i < N; i++) {
            totals[i] = i+1;
        }

        permu(0);
        System.out.println(totalCnt);
    }

    private static void permu(int cnt) {
        if(cnt == R){
            totalCnt++;
            System.out.println(Arrays.toString(selected));
            return;
        }
        for (int i = 0; i < N; i++) {
            if(!isSelected[i]){
                isSelected[i] = true;
                selected[cnt] = totals[i];
                permu(cnt+1);
                isSelected[i] = false;
            }
        }
    }
}