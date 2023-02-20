package com.divide_230220;

public class DebugTest {
    static int answer;
    public static void main(String[] args) {
        int total = 0;
        System.out.println("step-1");
        System.out.println("step-2");
        System.out.println("step-3");

        for (int i = 1; i < 100; i++) {
            total += i;
            System.out.println("1 ~ " + i + "까지의 합 : " + total);
        }
        answer = total;
        System.out.println(answer);
    }
}
