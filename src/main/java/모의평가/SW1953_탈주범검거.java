package 모의평가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SW1953_탈주범검거 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 목표
    - 탈주범이 존재할 수 있는 위치 개수 출력

    ### 규칙
    - 시간 당 1의 이동
    - 7 종류의 파이프
    - 시간 시점이 1시간

    ### 아이디어
    - 파이프 종류에 따라 이동 가능한 경로를 구현하는 것에 주의
        - switch 혹은 if 문으로 구현하기
        - 2차원 boolean 배열로 저장하기
    - bfs 진행 시, 파이프로 들어가는 방향도 고려
        - 0번 진행 시, 2번 true 인지 확인
        - 1번 진행 시, 3번 true 인지 확인
        - 2번 진행 시, 0번 true 인지 확인
        - 3번 진행 시, 1번 true 인지 확인
        - (진행 방향 + 2) % 4

    ### 구현
    - T와 N, M, R, C, L, 그리고 Map 구조 입력받기
    - 파이프 종류를 나타내는 2차원 boolean 배열 설정하기
    - dx, dy 배열 설정하기
    - 2차원 visited 배열 설정 및 bfs
     */
    static int T, N, M, R, C, L;
    static int[] dx = {-1,0,1,0}; //상, 우, 하, 좌
    static int[] dy = {0,1,0,-1};
    static boolean[][] pipes = {
            {false, false, false, false},
            {true, true, true, true},
            {true, false, true, false},
            {false, true, false, true},
            {true, true, false, false},
            {false, true, true, false},
            {false, false, true, true},
            {true, false, false, true}
    };
    static int[][] map;
    static boolean[][] visited;
    static int cnt;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= T; t++) {
            bw.write("#"+ t+ " ");
            //N, M, R, C, L 입력
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            //배열 초기화
            map = new int[N][M];
            visited = new boolean[N][M];
            cnt = 0;
            //map 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            bfs(R, C, L);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(visited[i][j]) cnt++;
                }
            }
            bw.write(cnt+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void bfs(int r, int c, int l) {
        //queue 입력값
        //x 좌표, y 좌표, 카운트, 이전 진행 방향
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{ r, c, 1});
        visited[R][C] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int cc = current[2];
            int cp = map[cx][cy];
            if(cc == l) continue;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>= N || ny>=M || !pipes[cp][i]) continue;
                int np = map[nx][ny];
                int nd = (i + 2) % 4;
                if(!visited[nx][ny] && pipes[np][nd]){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{ nx, ny, cc + 1});
                }
            }
        }
    }

}
