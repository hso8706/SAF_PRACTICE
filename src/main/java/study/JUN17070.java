package study;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN17070 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    파이프 옮기기 1
    => 사실상 길찾기 문제, 그런데 갈 수 있는 방향이 상황따라 정해진.

    - 체크
    1. 빈 공간: 0, 벽: 1, 외부 못나감
    2. 상황별 이동
        - 가로 상황 : 우, 우하단
        - 세로 상황 : 하단, 우하단
        - 대각선 상황 : 우, 우하단, 하단
    3. 첫 시작은 무조건 1,1 에서 1,2
    + 놓친 부분
    + 4. 진행 방향에 따라 벽이 무조건 없어야하는 부분이 존재함. 체크할 것

    - 목적
    => 1,1 시작으로 N, N 도착 가능한 경우의 수
    
    - 문제 해결
        - dfs 선택: N, N에 도달하면 cnt++ && 방문 여부 체크 false로 변경하여 다시 회귀

     */
    static int N; // 집의 크기
    static int[][] house; // 집 배열
    static boolean[][] visited; // 집 배열 방문 여부
    static int[] dx = new int[]{1, 1, 0}; //가로축 이동 방향
    static int[] dy = new int[]{0, 1, 1}; //세로축 이동 방향
//    static int[] direction = new int[]{0, 1, 2}; //0: 가로, 1: 대각선, 2: 세로
    static int cnt;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        house = new int[N + 1][N + 1]; // 인덱스 1부터 시작, 인덱스 0에 닿으면 아웃
        visited = new boolean[N + 1][N + 1];
        cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < N + 1; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(1, 2, 0);
        bw.write(cnt + "");
        bw.flush();
        bw.close();
    }

    private static void dfs(int y, int x, int direction) {
        if (x == N && y == N) {
            cnt++;
            return;
        }
        visited[y][x] = true;

        switch (direction) {
            //가로 상황
            case 0:
                for (int i = 0; i < 2; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx<=0 || nx > N || ny <=0 || ny > N) continue;
                    if(isWall(i, y, x)) continue; // 상황별 벽 대응
                    if(!visited[ny][nx]){
                        dfs(ny, nx, i);
                        visited[ny][nx] = false; // 이전 분기점으로 회귀하면서 재방문을 위한 false 변경
                    }
                }
                break;
            //대각선 상황
            case 1:
                for (int i = 0; i < 3; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx<=0 || nx > N || ny <=0 || ny > N) continue;
                    if(isWall(i, y, x)) continue;
                    if(!visited[ny][nx]){
                        dfs(ny, nx, i);
                        visited[ny][nx] = false;
                    }
                }
                break;
            //세로 상황
            case 2:
                for (int i = 1; i < 3; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx<=0 || nx > N || ny <=0 || ny > N) continue;
                    if(isWall(i, y, x)) continue;
                    if(!visited[ny][nx]){
                        dfs(ny, nx, i);
                        visited[ny][nx] = false;
                    }
                }
                break;
        }
    }

    //체크사항 4.
    //해당 위치에 벽이 있으면 true 반환하여 위 반복문 continue;
    private static boolean isWall(int nextD, int y, int x) {
        switch (nextD){
            // 가로 진행 방향
            case 0:
                if(house[y][x+1] == 1) return true;
                break;
            // 대각선 진행 방향
            case 1:
                if(house[y][x+1] == 1 || house[y+1][x+1] == 1 || house[y+1][x] == 1) return true;
                break;
            // 세로 진행 방향
            case 2:
                if(house[y+1][x] == 1) return true;
                break;
        }
        return false;
    }
}
