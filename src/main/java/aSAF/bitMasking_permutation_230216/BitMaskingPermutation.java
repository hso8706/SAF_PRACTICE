package aSAF.bitMasking_permutation_230216;

import java.util.Scanner;

public class BitMaskingPermutation {
    static int N, R;
    static int[] numbers, input;
    static int totalCount;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        N = 11;//sc.nextInt();
        R = 11;//sc.nextInt();

        numbers = new int[R];
//        input = new int[N];

        input = new int[]{1,2,3,4,5,6,7,8,9,10,11};
//        for (int i = 0; i < N; i++) {
//            input[i] = sc.nextInt();
//        }
        long start = System.currentTimeMillis(); // 실행 시간 비교 코드
        permutation(0, 0);
        long end = System.currentTimeMillis(); // 실행 시간 비교 코드
        System.out.println(end - start); // 실행 시간 비교 코드

        System.out.println("총 경우의 수 : " + totalCount);
    }

    //cnt : 현재까지 선택한 원소의 개수
    //flag : 선택된 원소에 대한 비트 정보가 들어 있음.
    private static void permutation(int cnt, int flag) {
//        System.out.println(cnt + "," + Integer.toBinaryString(flag));
        if (cnt == R){
            totalCount++;
//            System.out.println(Arrays.toString(numbers));
            return;
        }
        for (int i = 0; i < N; i++) {
            if((flag & (1<<i)) != 0) continue; // i 자리의 비트 확인
            numbers[cnt] = input[i];
            permutation(cnt+1, flag | (1<<i));
        }
    }
}
