package exercise.prac04;

import java.util.Arrays;
import java.util.Scanner;

public class Combination {
    static int N, R, totalCount;
    static int[] numbers, selectedNumbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        numbers = new int[N];
        selectedNumbers = new int[R];
        for (int i = 0; i < N; i++) {
            numbers[i] = i+1;
        }

        combi(0, 0);
        System.out.println(totalCount);
    }

    private static void combi(int cnt, int start) {
        if(cnt == R){
            totalCount++;
            System.out.println(Arrays.toString(selectedNumbers));
            return;
        }

        for (int i = start; i < N; i++) {
            selectedNumbers[cnt] = numbers[i];
            combi(cnt+1, i+1);
        }
    }


}
