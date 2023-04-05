package aSAF.stringAlgorithm_230404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 1)최단거리: bfs
 2)방문배열: 열쇠보유 상태에 따라 달라지므로 64가지의 상태를 관리. 재방문이 가능 
 3)6개의 열쇠: 64가지 상태 존재
 4)열쇠를 만난 경우: 열쇠 줍기(|), 문을 만난 경우: 열쇠체크(&)
 * */
public class JUN1194_Go4TheMoon {

	static Queue<Position> q; //bfs
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static char[][] map;
	static boolean[][][] visited;//[1][1][2]
	static int N, M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		q = new LinkedList<>();
		map = new char[N][M];
		visited = new boolean[64][N][M];// 64가지 상태로 존재 가능

		//1.입력
		for (int x = 0; x < N; ++x) {
			char[] line = br.readLine().toCharArray();// 한줄 ->배열로
			for (int y = 0; y < M; y++) {
				map[x][y] = line[y];

				if (map[x][y] == '0') {// 민식이 위치. 출발위치
					q.offer(new Position(x, y, 0));// 민식이 위치 저장
					
					visited[0][x][y] = true;// (r,c)위치에서 민식이가 가지고 있는 키 상태 번호
					map[x][y] = '.';
				}
			}
		} 

		System.out.println(bfs());
	}

	

	private static int bfs() {
		int move = 0;//움직임 횟수
		
		while(!q.isEmpty()) {
			int size = q.size();// 큐안에는 현재 민식이의 위치(임의의 한위치)에서 갈수 있는 최대4방향의 위치정보가 들어있음
			move++;
			
			for (int i = 0; i < size; i++) {
				Position cur = q.poll();// 민식이 위치 정보
				int x = cur.x;
				int y = cur.y;
				int keyState = cur.k;// 열쇠 보유상태 . 처음엔 0
				
				for (int j = 0; j < 4; j++) {// 4방 탐색
					int nx = x + dx[j];
					int ny = y + dy[j];
					int nk = keyState;// 아직 열쇠 없어. 열쇠 주우면 값이 바뀔 수 있음. 동일한 열쇠 상태에서 이동해 봄.

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)// 범위 밖
						continue;

					if (map[nx][ny] == '#' || visited[keyState][nx][ny] == true)// 벽, 같은 열쇠 상태로 온적 있음
						continue;
					
					if (map[nx][ny] == '1')// 출구
						return move;
					
					else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {// 열쇠가 있는 칸->주워야 돼(|)
						int shift = (map[nx][ny] - 'a');// 두 글자 사이의 차이 0~5. 'a'인 경우 0. 키를 만들때 1이 움직여야되는 횟수 결정

						// 방금 주운 열쇠의 이진수 모양을 만들기 위해 1을 shift번만큼 왼쪽으로 이동시킴.
						int key = 1 << shift;// 결과값: a->1(0번 이동시키므로), b->2(1번이동시킴), c->4...						
						nk = keyState | key;// 키를 주워야됨. 열쇠 상태 업데이트 됨.민식이는 열쇠 겟함.
						
					} else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {//문 ->열쇠 있나 체크
						int key = 1 << (map[nx][ny] - 'A');//needed key
						if ((keyState & key) == 0) // 열쇠 없어
							continue;						
					}
					
					q.offer(new Position(nx, ny, nk));// 민식이 새위치&열쇠보유상태
					visited[keyState][nx][ny] = true;//
					
				
				}				
			}			
		}
		
		return -1;
	}



	static class Position {
		int x, y, k;

		Position(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;// (r,c)위치에서 민식이가 가지고 있는 키 상태 번호(상태마다 번호 부여)
		}
	}
	
	
	
	
	static String src = "1 7\r\n" + "f0.F..1";
	static String src2 = "7 8\r\n" + "a#c#eF.1\r\n" + ".#.#.#..\r\n" + ".#B#D###\r\n" + "0....F.1\r\n" + "C#E#A###\r\n"
			+ ".#.#.#..\r\n" + "d#f#bF.1";
}