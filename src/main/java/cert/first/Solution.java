package cert.first;

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
    ### 정보
    - map: N*N
    - 사과: 1~, 순서대로만 먹을 수 있음
    - 시작위치: 좌측 상단 고정

    - 전진 및 오른쪽 회전만 가능
    ### 출력
    - 모든 사과를 먹어서 게임을 종료할 때, 최소 회전 횟수

    ### 풀이1. dfs
    - 방향 존재
    - 한 칸 전진할 때 마다, 우측을 확인
        - 사과가 없으면 전진
        - 사과가 있으면 회전 => 카운트
    - 벽을 넘어가는 경우도 회전 => 카운트

     */
    static int T, N;
    static int[][] map;
    static int[] dx = {-1,0,1,0}; // 시작 인덱스: 1
    static int[] dy = {0,1,0,-1};
    static int lastApple;
    static int result;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            N = Integer.parseInt(bf.readLine());
            lastApple = 0;
            result = 0;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) lastApple++; // 마지막 사과 번호 저장 => 기저조건
                }
            }

            dfs(0,0,1,0,0); //현재 위치(x,y), 방향, 회전 cnt, 먹은 사과 번호
            bw.write(result+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int cx, int cy, int cd, int cnt, int num) {
        //현 위치가 사과인지 확인
        //사과인 경우
        if(map[cx][cy] == num+1){
            num++;
            map[cx][cy] = 0;//사과 없앰
        }

        //마지막 사과를 먹은 경우 종료
        if(num == lastApple){
            result = cnt;
            return;
        }
        //사과가 아닌 경우, 그냥 진행

        //우측 확인 => 방향에 따라 다름
        int nx = cx;
        int ny = cy;
        boolean isApple = false;
        int nd = cd + 1; //우측 방향)
        nd = nd > 3 ? 0 : nd;
        while(true){
            nx = nx + dx[nd];
            ny = ny + dy[nd];
            if(nx<0 || ny<0 || nx>=N || ny>=N) break; //사과 없는 경우 종료
            if(map[nx][ny] == num+1) {
                isApple = true; // 사과 있는 경우 체크
                break;
            }
        }

        //이동
        if(isApple){ //사과 있음
            //좌표 유지, 방향 전환, cnt 증가, num 유지
            dfs(cx, cy, nd, cnt+1, num);
        }
        else{ //사과 없음
            //한 칸 전진, 방향 유지, cnt 유지, num 유지
            nx = cx+dx[cd];
            ny = cy+dy[cd];
            if(nx<0 || ny<0 || nx>=N || ny>=N) dfs(cx, cy, nd, cnt+1, num); // 벽을 넘어서는 경우에도 현재 좌표에서 방향만 전환
            else dfs(cx+dx[cd], cy+dy[cd], cd, cnt, num);
        }
    }
}
