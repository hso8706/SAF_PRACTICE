package solved;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW5656_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 벽돌깨기
    - 구슬은 0행, x열에서 떨어짐, N번 발사
    - 벽돌은 1부터 9까지의 수가 부여되어 있음.
    - 구슬에 닿은 벽돌은 제거되며, 해당 벽돌이 갖고있는 숫자 범위내에 있는 벽돌은 같이 제거가됨. 이때, 제거되는 벽돌은 같은 조건으로 제거됨
        - 즉 제거되는 로직은 항상 같음. 제거 대상이 되면 본인의 벽돌 숫자만큼의 상하좌우 범위가 연쇄적으로 제거
        - 벽돌의 범위는 본인 자신부터 1로 측정
    ### 목표
    - 최대한 많은 벽돌을 깰 수 있는 상황을 구하고, 해당 상황에서의 남은 벽돌을 출력하라

    ### 해결 요소
    1. 가장 많은 벽돌이 제거되는 상황 판단 로직
        - 파악하고 구슬 발사
            - 그리디적으로 해결 => 안된다. 깨진 후에 부숴야 더 많이 꺠지는 경우가 있음
        - 1 <= N <= 4 이니까 모든 경우의 수 계산
            - 경우의 수를 모두 계산하며 남은 벽돌의 최소를 갱신
            - 경우의 수는 중복 순열으로 해결 ==> 12^4이면 대충 2만 == 가능
            - 재귀로 구현 => 기저 조건 : cnt == N
    2. 벽돌 제거 로직
    3. 벽돌 제거 후 중력 로직
    => 1, 2,3은 묶여있어야한다. 구슬 발사 횟수에 대한 반복문 내에 메서드를 묶자.
     */
    static int T; // test case
    static int N, W, H; // N: 구슬 발사 횟수, W: 가로 너비, H: 세로 높이
    static int[][] map; // 벽돌 맵(0인 곳은 빈 공간.)
    static int[][] temp; // 가상 맵
    static boolean[][] visited;
    static int min; // 최소값 갱신
    static int[] dh = new int[]{0,0,-1}; // 좌우하, 위는 필요 없음
    static int[] dw = new int[]{-1,1,0};
    static int recoverW;
    static int recoverH;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T + 1; t++) {
            bw.write("#" + t + " ");

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            temp = new int[H][W];
            visited = new boolean[H][W];
            min = Integer.MAX_VALUE;
            fillMap();
            shoot(0);
        }
    }

    private static void shoot(int cnt) {
        if(cnt == N){
            //min 갱신
//            recoverTemp(); //temp 초기화
            return;
        }
        for (int i = 0; i < W; i++) { // W 너비만큼 모두 검사
            explosion(i);
            falling();
            shoot(cnt+1);
            //삭제 지점 파악 후 복구
//            temp[recoverH][recoverW] = map[recoverH][recoverW];
        }
    }

    private static void recoverTemp() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                temp[i][j] = map[i][j];
            }
        }
    }

    // 벽돌 정렬 메서드
    private static void falling() {

        for (int i = 0; i < H; i++) {
            System.out.println(Arrays.toString(temp[i]));
        }
        System.out.println();
    }

    // 벽돌 파괴 메서드
    private static void explosion(int w) {
        //bfs 방식 : h, w, number 저장된 queue
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < H; i++) {
            if(temp[i][w] > 0) {
                // 벽돌 위치 파악 및 숫자 확인 후 큐 저장 + 삼방탐색 필요 유무 저장(필요: -1, 노필요: 각 방향)
                queue.offer(new int[]{i, w, temp[i][w], -1});
                recoverH = i;
                recoverW = w;
                break;
            }
            else if(temp[i][w] == 1){
                queue.offer(new int[]{i, w, temp[i][w], 2});
                recoverH = i;
                recoverW = w;
                break;
            }
        }

        while (!queue.isEmpty()){
            int[] current = queue.poll();
            int ch = current[0];
            int cw = current[1];
            int cn = current[2];
            int d = current[3];
            temp[ch][cw] = 0; // 현재 위치 박살
            if(cn == 1) continue; // 현재 벽돌 숫자가 1이면 박살만나고 끝
            if(d == -1){
                for (int i = 0; i < 3; i++) { //좌우하
                    int nh = ch + dh[i];
                    int nw = cw + dw[i];
                    if (nw<0 || nw > W || nh > H) continue;
                    if (temp[nh][nw] > 1){  // 새로 만난 벽돌의 숫자가 1이 아닌 경우, 해당 지점 방향 탐색 필요
                        queue.offer(new int[]{nh, nw, temp[nh][nw], -1});
                    }
                    else if (temp[nh][nw] == 1){ // 새로 만난 벽돌 숫자가 1인 경우, 방향 탐색 필요 없음
                        queue.offer(new int[]{nh, nw, cn-1, i});
                    }
                }
            }
            else{
                int nh = ch + dh[d];
                int nw = cw + dw[d];
                if (nw<0 || nw > W || nh > H) continue;
                if (temp[nh][nw] > cn){
                    queue.offer(new int[]{nh, nw, temp[nh][nw], -1});
                }
                else if (temp[nh][nw] != 0){
                    queue.offer(new int[]{nh, nw, cn-1, d});
                }
            }
        }
        recoverTemp();


    }

    // 맵 채우는 메서드
    private static void fillMap() throws IOException {
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                temp[i][j] = map[i][j];
            }
        }
    }
}
