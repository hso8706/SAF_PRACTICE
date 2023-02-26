package exercise.prac04;

import java.util.Arrays;
import java.util.Scanner;

public class Permutation {
    static int N, R, totalCount;
    static int[] numbers, selectedNumbers;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        numbers = new int[N];
        selectedNumbers = new int[R];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = i+1;
        }

        permu(0);
        System.out.println(totalCount);
    }

    private static void permu(int cnt) {
        if(cnt == R){
            totalCount++;
            System.out.println(Arrays.toString(selectedNumbers));
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!isSelected[i]){
                isSelected[i] = true;
                selectedNumbers[cnt] = numbers[i];
                permu(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}
