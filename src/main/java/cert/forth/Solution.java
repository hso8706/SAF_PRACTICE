package cert.forth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 씨
    - 0 : 씨앗
    - 1일 후 : 싹
    - 4(1+3)일 이후 : 곡식
    - K번째 싹이 난 경우, K+3일 이후 곡식

    ### 개척자
    - 오전
        - 현재 빈 농지, 다음 이동 가능 => 현재 농지에 씨를 심음
        - 현재 빈 농지, 다음 이동 불가능 => 작업과 움직임 둘 다 하지 않음
        - 현재 농지에서 곡식이 열리면 수확, 수확 후 빈 농지가 됨
    - 오후
        - 로봇이 바라보는 방향을 기준으로 오른, 앞, 왼, 뒤 중 이동 가능한 곳으로 이동
            - 이동 가능한 곳: 빈 농지 혹은 곡식 농지
            - 이동 불가능한 곳: 산, 싹 농지
            - 이동 가능한 곳이 여러 개인 경우: 방향 순서 중 가장 먼저인 이동 가능한 곳으로 이동
            - 이동 가능한 곳이 없는 경우: 로봇이 이동없이 현재 위치에 머무름
    - 행동 일수 제한
        - 입력으로 주어지는 M일 까지만 동작할 수 있음

    ### 출력
    - M일 동안 곡식을 가장 많이 수확할 수 있는 시작 위치와 방향을 찾고, 그 상황에서의 수확 횟수를 출력
    => 완전 탐색
     */

    /*
    - map: 산을 -1로 입력받기, N*N
    - visited: 매 상황마다 새로 생성 => 따로 필요없을지도 모르겠다.
    - dfs
        - 재귀를 사용하므로 robo class는 따로 불필요

    - 씨: 1
    - 싹: 2, 3, 4
    - 곡식: 5
     */
    static int T, N, M;
    static int[][] map;
//    static boolean[][] visited;
    static int[] dx = {-1,0,1,0}; //북,동,남,서 => 방향+1을 시작으로, -1씩 감소하여 순회
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    if(temp == 1) map[i][j] = -1;
                    else map[i][j] = 0;
                }
            }
        }
        //완전 탐색 => 테두리 제외
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < N-1; j++) {
                for (int d = 0; d < 4; d++) {
//                    visited = new boolean[N][N];
                    dfs(i,j,d,0,1);//시작점과 방향, 수확 cnt, 날짜 cnt
                }
            }
        }
    }

    private static void dfs(int cs, int cy, int cd, int cnt, int day) {
        //day == M+1 종료, 그 전까지는 갈 길이 없더라도 진행
        if(day == M+1){
            return;
        }

        //가능한 길 탐색
        int flag = 0;
        int nd = cd + 1;
        if(nd == 4) nd = 0;
        while(flag < 4){// 4방 순회
            flag++;
            //다음 갈 위치
            int nx = cd + dx[nd];
            int ny = cd + dy[nd];
            if(nx<=0 || ny<=0 || nx>=N-1 || ny>=N-1) {
                nd--;
                if(nd == -1) nd = 3;
                continue;
            }
            //해당 위치 지형 확인
        }
    }
}
