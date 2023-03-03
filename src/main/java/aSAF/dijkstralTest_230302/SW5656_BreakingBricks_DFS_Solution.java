package aSAF.dijkstralTest_230302;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SW5656_BreakingBricks_DFS_Solution {

	private static class Point{
		int r,c,cnt;
		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static int N,W,H,min;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= TC; ++tc) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 횟수
			W = Integer.parseInt(st.nextToken()); // 열크기
			H = Integer.parseInt(st.nextToken()); // 행크기
			int[][] map = new int[H][W];
			for (int r = 0; r < H; ++r) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < W; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}	
			min = Integer.MAX_VALUE;
			go(0,map);
			System.out.println("#"+tc+" "+min);
		}
	}
	
	// 구슬 한번 던지기 
	private static boolean go(int count, int[][] map) { // 구슬이 던져진 횟수, 벽돌 맵 
		// 벽돌개수 파악
		int result = getRemain(map);
		if(result==0) {
			min = 0;
			return true;
		}
		
		if(count == N) {
			if(min>result) min = result;
			return false;
		}
		
		int[][] newMap = new int[H][W];
		// 모든 열에 구슬 던져보기
		for (int c = 0; c < W; c++) { // c: 구슬을 던지는 열
			
			// 구슬에 처음 맞는 벽돌 찾기(위쪽에서)
			int r = 0;
			while(r<H && map[r][c]==0) ++r;
			
			if(r==H) continue; //맞는 벽돌이 없으면 다음 열에 던져보기 
			
			// 배열 원본의 상태로 초기화
			copy(map,newMap);
			// 벽돌 부수기
			boom(newMap,r,c,newMap[r][c]);
			// 벽돌 내리기
			down(newMap);
			// 다음 구슬 던지러 가기
			if(go(count+1, newMap)) return true;
		}
		return false;
	}

	private static Stack<Integer> stack = new Stack<>();
	private static void down(int[][] map) {
		// 각 열에 대해  윗행부터 아래행까지 벽돌만 스택에 넣어두고 빼서 아래행부터 채우기
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if(map[r][c]>0) {
					stack.push(map[r][c]);
					map[r][c] = 0;
				}
			}
			int r = H-1;
			while(!stack.isEmpty()) {
				map[r--][c] = stack.pop();
			}
		}
	}

	// BFS
	private static void boom(int[][] map, int r, int c,int cnt) {
		map[r][c] = 0;
		if(cnt == 1) return;
		
		// 현벽돌의 cnt-1만큼 4방을 체크
		for (int d = 0; d < 4; d++) {
			int nr = r;
			int nc = c;
			for (int k = 1; k <= cnt-1; k++) {
				nr += dr[d];
				nc += dc[d];
				if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]>0) {
					boom(map,nr,nc,map[nr][nc]);
				}
			}
		}
	}
	private static int getRemain(int[][] map) {
		int count = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if(map[r][c]>0) count++;
			}
		}
		return count;
	}
	private static void copy(int[][] map, int[][] newMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}
}
