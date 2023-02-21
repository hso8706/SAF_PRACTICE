package com.backtTracking_230221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN3109_Bakery_prof {
	static int R, C;
	static char[][] map;//. 진행 가능, * 건물
	static int[] dx = {-1, 0, 1};//우상, 우, 우하로 접근시 x값 변화량
	static int count; //답	

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());// 5 5
		
		R = Integer.parseInt(st.nextToken());//행
		C = Integer.parseInt(st.nextToken());//열
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		//입력확인
		//System.out.println(Arrays.deepToString(map));
		
		//모든 행에 대해서 연결 시도
		for (int i = 0; i < R; i++) {
			connect(i, 0); //i는 행값, 0은 열값
		}
		System.out.println(count);
		
	}

	//(x,y)위치에서 시작해서 3방향으로 가 보는 메소드.갈 수 있으면 true, 못가면 false 리턴
	private static boolean connect(int x, int y) {
		//기저조건(종료): 원웅이네 도착
		if(y == C-1) {
			count++;
			return true;//연결 성공!
		}
		
		//아니면 계속해서 3방향 조사 후 전진
		for (int i = 0; i < 3; i++) {//우상, 우, 우하
			int nx = x + dx[i];
			int ny = y + 1;
			
			if(isIn(nx, ny) && map[nx][ny] == '.') {
				map[nx][ny] = 'ⓜ';//pipe 연결된 위치 표시
				boolean result = connect(nx, ny);//새위치부터 다시 연결해 봄
				if(result)//남은 방향이 있더라도 가지 않음.(nx, ny)위치까지 오는 경로가 겹침.
					return true;
			}			
		}		
		return false;
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < R && ny < C;
	}

}


