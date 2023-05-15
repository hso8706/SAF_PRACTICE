package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SW2105_디저트카페_solved {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 대각선으로 길이 존재함
        - 막힌 길은 없다
    - 칸의 숫자 == 디저트 종류
        - 디저트 투어를 하며 같은 종류의 디저트는 먹지 않는다.
    - 어느 한 카페에서 시작 == 모든 맵을 확인한다.
        - 사각형을 그리며 시작점으로 도착
        - 하나의 지점, 혹은 하나의 선(왔던 길을 되돌아가는 경우)은 경우의 수로 치지 않는다.
    - 디저트를 되도록 많이

    => 모든 경우의 수를 따져보고, 완성된 경우의 수 중 디저트가 가장 많이 먹은(사각형 길이가 가장 긴)경우를 찾기

    ### tip
    - 최단 경로 => bfs
    - 가장 긴 경로 => dfs
    - 사각형 움직임 + 대각선
        - 대각선으로 사각형을 만드는 상황은 반드시 필요한 크기가 존재한다.
        - 좌, 우, 하단 열에서 배제할 행, 열을 고려할 수 있다.(조금이나마 최적화를 할 수 있다)
    - 시계방향, 반시계방향 => 하나만 따져도 무관
        - 회전은 하나의 방향으로 돌되 대신 진행 방향에 있어서 순서가 필요하다
        - 때문에 visit 체크가 굳이 필요 없어진다.
    - 중복 디저트 문제
        - 이전에 방문했던 기록을 남겨야한다.
        - collection 으로 해결하면 좋다(=> set 사용해볼 예정)
    - collection 사용 시 주의 사항
        - 메모리 초과가 날 가능성이 있음(무거움)
        - 방문 배열을 만들어서 대체
        
    ### bfs 로는 힘든 이유
    - 이전에 갔던 경로를 기억해야하기 때문에
        - 가지치듯 진행하는 bfs 로직으로는 모든 경로를 기억하기 힘들다
        - 정 하려면, Node class를 만들고 해당 클래스의 필드에 경로를 기억할 수 있는 cafe[]을 넣어야한다.
     */

    static int T, N, ans;
    static int[][] map;
//    static HashSet<Integer> cafe;
    static boolean[] cafe;

    // delta : 시계방향; 우하-좌하-좌상-우상
    static int[] dx = {1,1,-1,-1};
    static int[] dy = {1,-1,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(bf.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(bf.readLine());
//            cafe = new HashSet<>();
            cafe = new boolean[101]; // idx 1부터 시작
            map = new int[N][N];
            // 값 초기화
            ans = -1; // 반환할 정답 미리 초기화

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 입력 완료
            
            // 각 가능한 좌표에서부터 dfs 출발
            for (int i = 0; i < N-2; i++) { // 행: 밑 두 줄 제외
                for (int j = 1; j < N-1; j++) { // 열: 양 끝 제외
                    //dfs() 출발 시 이전 방문 기록(cafe) 초기화
//                    cafe.clear();
//                    cafe.add(map[i][j]); // 방문 기록에 시작 지점 추가
//                    dfs(i,j,i,j,0);
                    Arrays.fill(cafe, false);
                    cafe[map[i][j]] = true;
//                    dfs(i,j,i,j,0);
                    dfs(i,j,i,j,0, 0);
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }
    // 방문 좌표: y,x | 시작 좌표: y,x | 방문좌표의 방향 : delta 의 index
//    private static void dfs(int cy, int cx, int sy, int sx, int dir) {
    private static void dfs(int cy, int cx, int sy, int sx, int cnt, int dir) {

        for (int d = dir; d < 4; d++) { // 4가 아닌 d+1로 해도 무관
            
            if( d > dir +1) break; // 직진 혹은 90도 회전만 체크하기 위해서 추가한 로직
            int ny = cy + dy[d];
            int nx = cx + dx[d];

            // 종료 조건: 방문 좌표가 시작점으로 돌아오고, 방문한 기록이 3이상 즉 사각형이 완성된 경우
//            if( ny == sy && nx == sx && cafe.size() >= 3){
//                ans = Math.max(ans, cafe.size());
//                return;
//            }
            if( ny == sy && nx == sx && cnt >= 3){
                ans = Math.max(ans, cnt+1);
                return;
            }

//            if( ny<0 || nx<0 || ny>=N || nx>=N ) continue; // 맵 외부로 나가면 패스
            if( ny<0 || nx<0 || ny>=N || nx>=N || cafe[map[ny][nx]]) continue; // 맵 외부로 나가면 패스
//            if( cafe.contains(map[nx][ny])) continue;//이미 방문한 기록이 있다면 패스

//            cafe.add(map[ny][nx]);
            cafe[map[ny][nx]] = true;
//            dfs(ny,nx,sy,sx,d); //dir은 현재 방향 == d
            dfs(ny,nx,sy,sx,cnt+1, d); //cnt 증가 로직 추가
            //dfs 종료 후 되돌아왔을땐 방문 기록에서 삭제
//            cafe.remove(map[ny][nx]);
            cafe[map[ny][nx]] = false;
        }

    }
}