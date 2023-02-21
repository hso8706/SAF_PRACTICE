package com.backtTracking_230221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//6C2 => 총 경기수 15경기. 승패수 => 30
public class JUN6987_WorldCup_prof {

	static int[][] score = new int[6][3];// 입력되는 승패수

	// 총 15경기에서 매치하는 팀정보.어느팀끼리 경기 할 지 미리 정해 놓음
	static int[] t1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] t2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };
	static int win = 0, draw = 1, lose = 2;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {// 입력 박스 4개
			StringTokenizer st = new StringTokenizer(br.readLine());

			flag = false;
			int tot = 0;// 총 승무패 수
			for (int j = 0; j < 6; j++) {// 6나라
				tot += score[j][win] = Integer.parseInt(st.nextToken());
				tot += score[j][draw] = Integer.parseInt(st.nextToken());
				tot += score[j][lose] = Integer.parseInt(st.nextToken());
			}

			if (tot != 30)
				flag = false;

			else
				check(0);// 체크하러 감

			if (flag)
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
	}

	// 경기 번호를 입력 받아 가능여부를 체크해주는 메소드
	private static void check(int match) {
		//기저조건
		if(match == 15) {//모든 경기 끝
			flag = true;
			return;
		}
		
		//경기해야 함
		int a = t1[match];
		int b = t2[match];
		
		//1. a > b
		if(score[a][win] > 0 && score[b][lose] > 0) {
			score[a][win]--;
			score[b][lose]--;
			check(match+1);//그 다음 경기하러 고고
			score[a][win]++;
			score[b][lose]++;	
		}
		
		
		//2. a == b
		if(score[a][draw] > 0 && score[b][draw] > 0) {
			score[a][draw]--;
			score[b][draw]--;
			check(match+1);
			score[a][draw]++;
			score[b][draw]++;
		}
		
		//3. a < b
		if(score[a][lose] > 0 && score[b][win] > 0) {
			score[a][lose]--;
			score[b][win]--;
			check(match+1);
			score[a][lose]++;
			score[b][win]++;
		}		
		
	}

}
