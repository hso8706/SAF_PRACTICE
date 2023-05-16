package forA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// k (서비스의 길이) 가 작은 ~~ 큰 범위로 경우의 수를 따진다. <= 어느 특정 지점에서 어느 크기의 서비스 영역을 정하느냐에 따라
//   비용, 집의 수가 각각 달라진다. => 특정지을 수 없다. 어느좌표가 유리한지. 어느 k 가 유리한지....
// 완탐!
// 모든 좌표에서 모든 k를 다 고려
//    1. map 2차원 배열 순회, 탐색 X
//		=> 어차피 k 값은 순차적으로 증가, 또는 감소하는 static 한 값
//	    => 집의 위치가 알 수 없는 미궁의??  => 입력처리를 하면서 집을 별도의 자료구조에 저장 List 에 (좌표)
//    2. map 2차원 배열 순회, 탐색 O
//		=> 모든 좌표에서 서비스를 모든 k에 대해서 진행하되 k의 길이만큼 맵에서 가보는 것.

// 2번 2차원 배열 순회 탐색
public class SW2217_홈방범서비스_solved2 {

    static int T, N, M, ans;
    static int[][] map;
    static int[] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken()); // 집당 수익

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 입력 완료

            ans = 0;

            // K 서비스의 길이는 1 ~ 얼마까지?
            // N이 홀수 : N과 동일, N이 짝수 : N + 1
            // cost[0] : dummy cost[1] : k == 1 비용, cost[N+1]까지 계산
            cost = new int[N + 2];

            for (int k = 1; k <= N + 1; k++) {
                cost[k] = k*k + (k-1)*(k-1);
            }

            // 2차원 배열을 돌면서 모든 지점에 서비스를 놓는 경우를 따진다.
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {

                    check(y, x);
                }
            }


            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void check(int y, int x) {
        // 동일한 y, x 좌표에서 k 를 1부터 쭉 따져나가는데 이전에 따진 내용을 재활용
        int[] cnt = new int[N + 2]; // cnt[3]
        for (int k = 1; k <= N + 1; k++) {
            cnt[k] = countBoundaryHouse(y, x, k) + cnt[k-1];
            if( cnt[k]*M >= cost[k] ) ans = Math.max(ans, cnt[k]);
        }
    }

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = {  0, 0,-1, 1 };

    static int[] dyBorder = { 1, 1, -1, -1 };
    static int[] dxBorder = { 1,-1, -1,  1 };

    static int countBoundaryHouse(int y, int x, int k) {
        int cnt = 0;
        if( k == 1 ) cnt = map[y][x] == 1 ? 1 : 0;
        else if( k == 2 ) {
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if( ny < 0 || nx < 0 || ny >= N || nx >= N ) continue;
                if( map[ny][nx] == 1 ) cnt++;
            }
        }else {
            // 꼭대기 보정
            int ny = y - k + 1;
            int nx = x;

            // 꼭대기 좌표부터 외각의 마름모를 따라가면서 집확인
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < k - 1; i++) {
                    ny = ny + dyBorder[d];
                    nx = nx + dxBorder[d];

                    if( ny < 0 || nx < 0 || ny >= N || nx >= N ) continue;
                    if( map[ny][nx] == 1 ) cnt++;
                }
            }
        }
        return cnt;
    }
}






