package cert.forth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Robo_Prof_D {
	private static class Ground {
		int state; // state 0빈농지, 1씨를 심음, 2싹이 남, 3곡식 열림
		int K = 3; // 현재 농지에서 싹이 튼 회수 (초기값은 3, 싹이나는 순간 ++ 하고 작업) => 곡식이 열리려면 지나야되는 날
		int day; // 싹이난후 지난날
		/** 객체를 초기화 해주는 메서드 */
		void init() {
			state = 0;
			K = 3;
			day = 0;
		}
	}
	private static int[] dr = { 0,-1, 0, 1}; // 기본방향(우앞왼뒤)
	private static int[] dc = { 1, 0,-1, 0}; // 기본방향(우앞왼뒤)
	private static int N;
	private static Ground[][] map;
	private static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine()); // 50개의 테스트 케이스
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 지형의 한 변의 길이 N 은 6 이상 9 이하의 정수이다. ( 6 ≤ N ≤ 9 )
			M = Integer.parseInt(st.nextToken()); // 로봇이 동작하는 일 수 M 은 10 이상 50 이하의 정수이다. ( 10 ≤ M ≤ 50 )
			
			map = new Ground[N][N]; // 지형정보 농지는 0=> 객체생성, 산은 1 => 아무것도 안함 null
			for (int r = 0; r < N; r++) {
				String s = br.readLine();
				for (int c = 0, index = 0; c < N; c++, index += 2) {
					if (s.charAt(index) == '0') { // 농지
						map[r][c] = new Ground();
					}
				}
			}
			
			int maxGainCnt = 0; // 로봇 수확하는 최대 회수
			
//			로봇의 시작위치(농지에서 시작)와 방향을 정해서 시뮬해본다
			for (int r = 1; r < N-1; r++) {
				for (int c = 1; c < N-1; c++) { // 테두리는 산이니까 제외
					if (map[r][c] == null) continue; // 산이면 pass, 
					for (int dir = 0; dir < 4; dir++) {
						int gainCnt = go(r, c, dir); // 수확회수 얻어옴
						if (maxGainCnt < gainCnt) maxGainCnt = gainCnt;
						initGround(); // 맵 원복 (초기화)
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(maxGainCnt).append("\n"); // 로봇 "개척자" 의 최대 수확 횟수
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main
	
	/** (r,c)위치에 로봇, d 방향에서 시작, 수확 횟수를 리턴 */
	private static int go(int r, int c, int dir) {
		int gainCnt = 0; // 수확 회수
		int nr = -1;
		int nc = -1;
		for (int day = 0; day < M; day++) {
			// 이동 가능한 곳이 있는지 확인 선택, 방향    기본방향(우앞왼뒤)
			boolean movable = false; // 이동불가
			for (int i = 0; i < dc.length; i++) {
				int tempDir = (i + dir) % 4;
				nr = r + dr[tempDir];
				nc = c + dc[tempDir];
				// 이동가능 : 빈 농지, 또는 곡식이 열린 농지, 이동 불가능 : 산이거나 싹이 나는 농지 // state 0빈농지, 1씨를 심음, 2싹이 남, 3곡식 열림
				if (map[nr][nc]!=null && map[nr][nc].state!=1 && map[nr][nc].state!=2) {
					dir = (dir + i + 3) % 4; // 현재 위치에서 방향 꺽으면 놓이는 절대방향으로 보정
					movable = true;
					break;
				}
			}
			
//		1) 로봇은 오전에 현재 위치에서 아래의 작업 중 하나를 수행한다.
			Ground g = map[r][c];
			if (g!=null && g.state==0 && movable) { // 현재 농지가 빈 농지이고 로봇이 다음 농지로 이동할 수 있는 경우 씨를 심는다.
				g.state = 1; // 1씨를 심음
			} else if (g!=null && g.state==0 && !movable) { // 현재 농지가 빈 농지이고, 로봇이 다음 농지로 이동할 수 없을 경우 아무것도 하지 않고 현재 위치에서 머무른다.
				// 격하게 아무것도 하고 싶지 않다
			} else if (g!=null && g.state==3) { // 현재 농지에 곡식이 열린 경우 수확을 한다. 수확을 하면 농지는 빈 농지가 된다.
				gainCnt++;
				g.state = 0; // 0빈농지
			}
			
//		2) 로봇은 오후에 로봇이 바라보는 방향의 오른쪽, 앞쪽, 왼쪽, 뒤쪽 이웃하는 곳 중, 이동 가능한 곳으로 이동한다.
//		  - 이동 가능한 곳은 빈 농지, 또는 곡식이 열린 농지이다. 산이거나 싹이 나는 농지인 경우 이동이 불가능하다.
//		  - 만약, 이동 가능한 곳이 여러 개인 경우, 로봇의 오른쪽, 앞쪽, 왼쪽, 뒤쪽의 순서로 가장 먼저인 이동 가능한 곳으로 이동한다.
//		  - 만약 이동 가능한 곳이 없는 경우 로봇은 이동하지 않고 현재 위치에 머무른다.
			if (movable) {
				r = nr;
				c = nc;
			}
			
		// 하루 경과 처리, state 0빈농지 x, 1씨를 심음 =>2 K++, 2싹이 남 day++ if(day==K) {state=3; day=0}, 3곡식 열림 x
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < N-1; j++) {
					g = map[i][j];
					if (g == null) continue;
//					state 0빈농지 x,
					if (g.state == 1) { // 1씨를 심음 =>2 K++,
						g.state = 2;
						g.K++;
					} else if (g.state == 2) { // 2싹이 남 day++ if(day==K) {state=3; day=0}, 
						g.day++;
						if (g.day == g.K) {
							g.state = 3;
							g.day = 0;
						}
					}
//					3곡식 열림 x
				}
			}
		}
		return gainCnt;
	}
	/** 농지 위치의 Ground 배열 객체를 초기화 */
	private static void initGround() {
		for (int r = 1; r < N-1; r++) {
			for (int c = 1; c < N-1; c++) {
				if (map[r][c] != null) { // 농지면
					map[r][c].init();
				}
			}
		}
	}
} // end of class


















