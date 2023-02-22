package aSAF.bitMasking_permutation_230216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
//음식 A를 위해 식재료 2, 3, 6이 사용되었다면 A의 맛 = S[2][3] + S[2][6] + S[3][2] + S[3][6] + S[6][2] + S[6][3]  

public class SW4012_Chef_Professor {

	public static int N, half;
	public static int[][] synergy;
	public static int answer = Integer.MAX_VALUE;

	public static ArrayList<Integer> sub1;
	public static ArrayList<Integer> sub2;

	static int foodA, foodB;
	public static void subset(int cnt, int start) {
		if (cnt == half) {
			sub2.clear();
			for (int i = 0; i < N; i++) {
				if (!sub1.contains(i)) {
					sub2.add(i);
				}
			}

			foodA = 0;
			foodB = 0;

			for (int i = 0; i < half; i++) {
				for (int j = 0; j < half; j++) {
					foodA += synergy[sub1.get(i)][sub1.get(j)];
					foodB += synergy[sub2.get(i)][sub2.get(j)];
				}
			}
			answer = Math.min(answer, Math.abs(foodA - foodB));
		}
		
		for (int i = start; i < N; i++) {
			if (!sub1.contains(i)) {
				sub1.add(i);
				subset(cnt + 1, i);
				sub1.remove(new Integer(i));
			
			}
		}
	}

	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			sub1 = new ArrayList<Integer>();
			sub2 = new ArrayList<Integer>();

			N = Integer.parseInt(br.readLine());
			half = N / 2;
			synergy = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			subset(0, 0);
			System.out.println("#" + tc + " " + answer);
		
		} // end test_case
	}// end main
}
