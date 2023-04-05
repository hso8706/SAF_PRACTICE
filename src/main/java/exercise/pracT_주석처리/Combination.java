package exercise.pracT_주석처리;

import java.util.Arrays;
import java.util.Scanner;

public class Combination {
    static int N, R, totalCnt;
    static int[] totals, selected;
//    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        totals = new int[N];
//        isSelected = new boolean[N];
        selected = new int[R];
        for (int i = 0; i < N; i++) {
            totals[i] = i+1;
        }

        combi(0, 0);
        System.out.println(totalCnt);
    }

    private static void combi(int cnt, int start) {
        if(cnt == R){
            totalCnt++;
            System.out.println(Arrays.toString(selected));
            return;
        }

        for (int i = start; i < N; i++) {
            selected[cnt] = totals[i];
            combi(cnt +1, i+1);
        }
    }

}