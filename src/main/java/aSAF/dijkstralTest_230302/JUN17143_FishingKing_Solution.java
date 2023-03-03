package aSAF.dijkstralTest_230302;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN17143_FishingKing_Solution {
	static int R, C, M;
	static int[][] map; //= new int[101][101];//초기 입력값. (2 ≤ R, C ≤ 100)
	
	static int sumOfSize = 0;//이게 구하는 답
	static Map<Integer, Shark> sharks;
	
	//dir가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.
	static int[] dx = {0, -1, 1, 0,  0};
	static int[] dy = {0,  0, 0, 1, -1};	
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());//행
		C = Integer.parseInt(st.nextToken());//열
		M = Integer.parseInt(st.nextToken());//상어 수
		map = new int[R+1][C+1];
		
		sharks = new HashMap<>();

		for (int i = 0; i < M; i++) {//상어수만큼
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());//이동 시 
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());//상어 번호라 생각해도 됨. 중복 안되니깐
			Shark s = new Shark(x, y, dir, speed, size);
			
			sharks.put(size, s);//size는 겹치지 않아서 키로 사용가능.두 상어가 같은 크기를 갖는 경우는 없고,
			map[x][y] = size;//map에는 그 칸에 있는 상어의 크기가 저장됨. 상어 번호라고 생각해도 됨.
		}
		
		//낚시왕이 왼쪽 -> 오른쪽으로 한 칸씩 이동하면서...		
		for (int i = 1; i <= C; i++) {//열 만큼
			fishing(i);//1. 낚시: 땅과 가장 가까운 상어를 잡음 
			moveShark();//2. 상어가 이동.			
		}		
		
		System.out.println(sumOfSize);
	}
	
	//낚시왕이 col번째 열에서 낚시함
	private static void fishing(int col) {
		for (int i = 1; i <= R; i++) {//i는 행값
			if(map[i][col] != 0) {//상어 발견
				sumOfSize += map[i][col];
				sharks.remove(map[i][col]);
				map[i][col] = 0;//빈칸
				return;
			}			
		}		
	}	
	
	//상어들 이동.상어는 자신의 속도만큼 이동함.
	//위치가 중복되면 크기로 경쟁 후 정리
	private static void moveShark() {		
		//이동 후의 상어위치  저장할 배열
		int[][] tmp = new int[R+1][C+1];
		Queue<Integer> losers = new LinkedList<>();//경쟁에서 진 상어들 모아놓기
		
		for (Integer size : sharks.keySet()) {//map안의 key만 set 타입으로 리턴
			Shark s = sharks.get(size);
			map[s.x][s.y] = 0;//이동할 꺼니까 원래 자리는 초기화 			
			
			//1)상어가 자신의 속도만큼 이동
			//dir가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.
			for (int i = 0; i < s.speed; i++) {				
				//1.진행 방향 체크
				if(s.dir == 1 && s.x == 1) {
					s.dir = 2;
					
				}else if(s.dir == 2 && s.x == R) {
					s.dir = 1;
					
				}else if(s.dir == 3 && s.y == C) {
					s.dir = 4;
					
				}else if(s.dir == 4 && s.y == 1) {
					s.dir = 3;
				}
				
				//2.(바꾼) 방향으로 이동함
				s.x += dx[s.dir];
				s.y += dy[s.dir];			
			}
			// <-- 여기까지 오면 상어는 주어진 속도로 이동한 후 새로운 위치에 가 있음.
			
			//2)이동 후 위치가 겹치면 경쟁
			if(tmp[s.x][s.y] == 0) {//새 위치에 아무도 없어
				tmp[s.x][s.y] = s.size;//그 위치에 들어갈 수 있음
				
			}else if(s.size > tmp[s.x][s.y]) {//이전 애보다 내가 더 큼
				losers.add(tmp[s.x][s.y]);
				tmp[s.x][s.y] = s.size;
				
			}else {//이전 애가 더 큼
				losers.add(s.size);
			}			
		}	
		// <--모든 상어가 새 위치로 이동 후(tmp) 루저 정리 끝
		
		//3.경쟁에서 진 상어들 삭제
		while(!losers.isEmpty()) {
			sharks.remove(losers.poll());
		}
		
		
		//4.이동 후 살아남은 상어들의 새위치를 map에 반영
		for (Integer size: sharks.keySet()) {
			Shark s = sharks.get(size);
			map[s.x][s.y] = tmp[s.x][s.y];
		}		
	}

	

	static class Shark {
		int x, y, dir, speed, size;

		public Shark(int x, int y, int dir, int speed, int size) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.speed = speed;
			this.size = size;//no
		}
	}
}








