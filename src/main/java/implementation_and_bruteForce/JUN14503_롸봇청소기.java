package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN14503_롸봇청소기 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 로봇 청소기
    - 기본
        - NxM 방
        - 청소기 바라보는 방향 : 동,서,남,북
        - 최좌상단 : 0,0
        - 최동하단 : N-1, M-1
        - 동서남북 가장자리 모두 벽
        - 청소기는 항상 빈 칸에서 시작
    - 청소
        - 현재 칸 청소 여부
            - x : 청소 실시
        - 주변 4칸이 모두 청소된 상태
            - 방향 유지 + 현재 방향 기준 뒤로 한 칸 후진
            - 후진 불가(벽)할 경우, 작동 종료
        - 주변 4칸 중 청소가 안 된 곳이 있는 경우
            - 반시계 90도 회전
            - 바라보는 방향이 청소 안 된 곳이면 전진
            - 1번 실시
    - 출력
        - 작동 종료전까지 청소하는 칸의 개수

    ### 해결01.
    - 구현 파이팅
     */
    static class Robot {
        int n; // 세로 좌표
        int m; // 가로 좌표
        int d; // 방향; 0: 북, 1: 동, 2: 남, 3: 서
//        int c; // 청소한 방 개수

        public Robot(int n, int m, int d) {
            this.n = n;
            this.m = m;
            this.d = d;
        }
    }
    
    static int[] dx = new int[]{-1,0,1,0}; // 북동남서, 로봇 방향 순서와 매칭, 다음 진행 방향을 인덱스로 대입
    static int[] dy = new int[]{0,1,0,-1}; // 전진 개념
    
    static int N, M; // room size
    static int[][] room;
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        Robot startR = new Robot(n,m,d);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cleaningRobot(startR);
        System.out.println(cnt);
    }

    // bfs 같지만 dfs 느낌
    private static void cleaningRobot(Robot startR) {
        Deque<Robot> queue = new ArrayDeque<>();
        queue.offer(startR);
        cnt = 0;

        while(!queue.isEmpty()){
            Robot current = queue.poll();
            int cx = current.n;
            int cy = current.m;
            int cd = current.d;
            int cleanCnt = 0;
            if(room[cx][cy] == 0) {
                room[cx][cy] = 2;
                cnt++;
            }

            for (int i = 0; i < 4; i++) { // 청소 가능 장소 탐색
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(room[nx][ny] == 1 || room[nx][ny] == 2) cleanCnt++;
            }

            if (cleanCnt == 4){ // 청소할 곳이 없는 경우
                // 방향 유지 + 현재 방향 기준 후진 1칸
                int backDir = cd +2;
                if (backDir > 3) backDir -= 4;
                int nx = cx + dx[backDir];
                int ny = cy + dy[backDir];
                // 후진 불가(벽) 시, 종료
                if (room[nx][ny] == 1) break;
                queue.offer(new Robot(nx,ny,cd));
            }
            else { // 청소 할 곳이 있는 경우
                // 반시계 90도로 돌며 진행 방향 파악
                int nd = cd;
                while(true){
                    nd = nd - 1;
                    if(nd == -1) nd = 3;
                    int nx = cx + dx[nd];
                    int ny = cy + dy[nd];
                    if(room[nx][ny] == 0) {
                        queue.offer(new Robot(nx,ny,nd));
                        break;
                    }
                }
            }
        }
    }
}
