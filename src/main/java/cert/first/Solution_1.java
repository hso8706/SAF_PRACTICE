package cert.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 조건
    - N*N map
    - 번호가 매겨진 사과: 순서대로 먹을 수 있음
    - 좌측 상단에서 오른쪽 방향을 바라보며 시작
    - 전진 혹은 오른쪽 90도 회전만 가능

    ### 출력
    - 모든 사과를 먹기 위해서 최소 몇 번의 회전이 필요한 지

    ### 풀이1.
    - dfs
    - 회전을 해야하는 상황 파악
        - 우측 방향 끝까지 검색
            - 사과가 있다면 회전
            - 없다면 전진 => 전진 시 벽을 넘어간다면 회전
    - 모든 회전에는 카운트 증가
     */

    static int T, N;
    static int[][] map;
    static int result; // 최소 회전 횟수
    static int apples;
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1}; //북, 동, 남, 서

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");
            apples = 0;
            result = 0;
            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) apples++;
                }
            }


            dfs(0,0,1,0, 0); //시작 좌표, 시작 방향, 먹은 사과 번호, 그리고 회전 cnt 제공
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int cx, int cy, int cd, int num, int turn) {
        if(num == apples){
            result = turn;
            return;
        }

        //우측 확인
        int nd = cd + 1; // 회전 후 방향
        if(nd > 3) nd = 0;
        int nx = cx;
        int ny = cy;
        boolean isApple = false;
        while(true){
            nx = nx + dx[nd];
            ny = ny + dy[nd];
            if(nx<0 || ny<0 || nx>=N || ny>=N) break;
            if(map[nx][ny] == num+1) {
                isApple = true;
                break;
            }
        }
        //우측에 있다면 현재 자리에서 회전 실행
        if(isApple) {
            dfs(cx,cy,nd,num,turn+1);
        }
        //우측에 없다면 바라보는 방향으로 이동
        else {
            nx = cx + dx[cd];
            ny = cy + dy[cd];
            //1. 벽을 넘어가는 경우 => 현재 자리에서 회전 실행
            if(nx<0 || ny<0 || nx>=N || ny>=N) {
                dfs(cx,cy,nd,num,turn+1);
            }
            //2. 사과인 경우
            else if (map[nx][ny] == num+1){
                map[nx][ny] = 0;//사과 제거
                dfs(nx,ny,cd,num+1,turn);
            }
            //3. 아무것도 아닌 경우
            else {
                dfs(nx,ny,cd,num,turn);
            }
        }
    }
}