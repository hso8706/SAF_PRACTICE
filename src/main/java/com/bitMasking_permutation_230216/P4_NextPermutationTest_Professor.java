package com.day9;

import java.util.Arrays;
import java.util.Scanner;

public class P4_NextPermutationTest_Professor {

	static int totalCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		Arrays.sort(input);
		do {
			System.out.println(Arrays.toString(input));
		} while (np(input));

		System.out.println("총 경우의 수 : " + totalCount);
	}

	private static boolean np(int numbers[]) {
		totalCount++;
		int N = numbers.length;

		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (numbers[i - 1] >= numbers[j])
			--j;
		swap(numbers, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(numbers, i++, k--);
		}
		return true;
	}

	private static void swap(int numbers[], int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
