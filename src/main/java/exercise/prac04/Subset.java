package exercise.prac04;

import java.util.Scanner;

public class Subset {
    static int N, totalCount;
    static int[] numbers;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        numbers = new int[N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = i+1;
        }

        subset(0);
        System.out.println(totalCount);
    }

    private static void subset(int cnt) {
        if(cnt == N){
            totalCount++;
            for(int i=0; i<N; i++){
                System.out.print((isSelected[i] ? i : "x") + " ");
            }
            System.out.println();
            return;
        }

        isSelected[cnt] = true;
        subset(cnt+1);
        isSelected[cnt] = false;
        subset(cnt+1);
    }
}
