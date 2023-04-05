package aSAF.stringAlgorithm_230404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 1)입력받은 섬 정보를 바탕으로 섬마다 번호를 붙여줌.(dfs)
 2)각 섬간에 다리를 건설해 봄.(bfs) 
 3)모든 섬을 연결하는 다리길이의 최소값 찾음.(mst) 
 * */
public class JUN17472_MakingBridge {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int R, C, N;//N:섬번호
	static int[][] map;
	static int[] parent;//union-find
	static Queue<Island> q = new LinkedList<>();//섬 정보 저장할 리스트
	static PriorityQueue<Island> pq = new PriorityQueue<>();//다리길이 기준으로 정렬된 섬 정보가 저장됨

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		//1.입력 : 섬을 -1로 표시
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {//땅
					map[i][j] = -1;//섬번호를 1번부터 사용할 거라서 
				}
			}
		}
		
		//2.섬 넘버링(1~N)
		N = 1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == -1)
					dfs(i, j, N++);
			}
		}
		
		//3.union-find 준비작업
		make();
		
		//4.섬과 섬을 잇는 다리 건설=> pq
		buildBridge();
		
		//5.각 섬들을 최소비용으로 연결해 주는 다리 찾기
		System.out.println(mst());
	}
	
	private static int mst() {
		int sum = 0;
		while(!pq.isEmpty()) {
			Island n = pq.poll();
			if(n.val != 1 && union(n.x, n.y)) {
				sum += n.val;
			}			
		}
		
		//모든 섬이 연결됐나요?
		int rootCnt = 0;//대표자 개수 카운팅 변수
		for (int i = 0; i < N; i++) {
			if(parent[i] == -1) {
				rootCnt++;
			}
		}			
		return (rootCnt == 1) ? sum : -1;
	}

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {//합칠 수 있음
			parent[b] = a;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parent[a] < 0) {//자기가 대표
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	//섬과 섬을 잇는 다리 만들기
	private static void buildBridge() {
		while(!q.isEmpty()) {
			Island n = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				
				int length = 0;//섬 n에서 건설할 다리의 길이
				while(nx >= 0 && ny >= 0 && nx < R && ny < C) {//계속 다리 늘려가기
					if(map[nx][ny] != 0) {// 땅.다른 섬. 다리 하나 건설 완성
						if(n.val < map[nx][ny]){//시작섬 번호 < 도착섬 번호 ex)1->2 
							pq.add(new Island(map[n.x][n.y],//시작섬 번호
											  map[nx][ny],//도착섬 번호
											  length));
						}
						break;//다리 건설 끝이니까 그방향으로는 고만 감						
					}else {
						length++;//다리 길이 늘임
						nx += dx[i];//같은 방향으로 이동
						ny += dy[i];
					}
				}				
			}			
		}		
	}

	private static void make() {
		parent = new int[N];
		for (int i = 1; i < N; i++) {
			parent[i] = -1;
		}
	}

	private static void dfs(int i, int j, int no) {
		q.add(new Island(i, j, no));
		map[i][j] = no;
		
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			
			if(nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] == -1) {
				dfs(nx, ny, no);
			}
		}		
	}

	static class Island implements Comparable<Island>{
		int x, y, val;//섬번호

		public Island(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Island o) {			
			return this.val - o.val;
		}	
	}
}