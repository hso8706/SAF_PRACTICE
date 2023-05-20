package forA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시물레이션
// 제시된 규칙을 꼼꼼히 챙긴다.

// 좌표 처리 int[] [0]:y, [1]:x
// 웜홀 자료 구조
//    warm[][]   => warm[7][0] : 입력 처리시  먼저 만난 7번 웜홀의 y 좌표, warm[7][3] : 나중에 만난 7번 웜홀의 x 좌표
// delta 구성
//  <---  --->  방향 전환 이 중요하므로 방향전환 계산에 편리한 쉬운 구성, 순서
// 점수 조건, 종료 조건
public class SW5650_핀볼게임_Prof {

	static int T, N, ans;
	static int[][] map;
	static int[][] warm;
	
	// 순서가 필요.          상 -좌 -하 -우
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = {  0,-1, 0, 1 };
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			warm = new int[11][4]; // 0 ~ 5 : dummy, [4] => (0,1) (2,3)
			
			for (int i = 6; i <= 10; i++) {
				warm[i][0] = warm[i][1] = warm[i][2] = warm[i][3] = -9; // 아직 사용되지 않은 값 의미
			}
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;
					
					// 웜홀의 자료구조 
					if( n >= 6 ) {
						if( warm[ n ][0] == -9 ) {
							warm[ n ][0] = i;
							warm[ n ][1] = j;
						}else {
							warm[ n ][2] = i;
							warm[ n ][3] = j;
						}
					}
				}
			}
			// 입력 완료
			
			ans = 0;
			
			// 모든 출발점에서 모든 방향 다 고려
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if( map[i][j] == 0 ) {
						
						map[i][j] = -1; // 시작점 블랙홀
						
						// 모든 방향
						for (int d = 0; d < 4; d++) {
							int cnt = go(i, j, d);
							ans = Math.max(ans, cnt);
						}
						
						map[i][j] = 0; // 시작점 블랙홀 원복
						
					}
				}
			}
			
			System.out.println("#" + t + " " + ans);			
		}
	}

	static int go(int sy, int sx, int d) { // 해당 좌표에서 해당 방향으로 시물레이션을 돌린 결과의 cnt 리턴
		int cnt = 0;
		
		// 현재 좌표 <- 시작
		int cy = sy;
		int cx = sx;
		
		// 시물레이션 구조
		while( true ) {
			
			int num = map[cy][cx];
			
			int ny = 0;
			int nx = 0;
			
			// 방향 전화이 필요하면 먼저 방향 전환을 진행
			// block
			if( 1 <= num && num <= 5 ) {
				cnt++;
				
				switch(num) {
					case 1: // 상, 우 반대로, 하 -> 우 좌 -> 상
						if( d == 0 || d == 3 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
						else d = d == 1 ? 0 : 3;
						break;
					case 2: 
						if( d == 2 || d == 3 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
						else d = d == 0 ? 3 : 2;
						break;
					case 3: 
						if( d == 1 || d == 2 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
						else d = d == 0 ? 1 : 2;
						break;
					case 4: 
						if( d == 0 || d == 1 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
						else d = d == 2 ? 1 : 0;
						break;
					case 5: 
						d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
						break;						
						
				}
			}
						
			// 좌표 이동
			ny = cy + dy[d];
			nx = cx + dx[d];
			
			// 벽 + range
			if( ny < 0 || nx < 0 || ny >= N || nx >= N ) {
				cnt++;
				d = ( d % 2 == 0 ) ? 2 - d : 4 - d; // 방향 반대
				// 원 좌표 복귀
				ny = cy;
				nx = cx;
			}
			
			// 종료, 웜홀
			if( map[ny][nx] == -1 ) { // 블랙홀만
				break;
			}else if( map[ny][nx] >= 6 ) {
				int warmNum = map[ny][nx];
				
				if( warm[ warmNum ][0] == ny && warm[ warmNum][1] == nx ) {
					ny = warm[ warmNum ][2];
					nx = warm[ warmNum ][3];
				}else {
					ny = warm[ warmNum ][0];
					nx = warm[ warmNum ][1];
				}
			}
			
			// 기준 점 변경
			cy = ny;
			cx = nx;
			
		}
		
		return cnt;
	}
}












