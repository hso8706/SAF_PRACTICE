package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN17135_캐슬디펜스 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - N X M
    - 성의 위치 : N+1
    - 궁수 3명, 성의 위치 중 3 곳
    - 궁수와 가장 가까운 적 제거(D 이하인 적 중에)
        - 같은 거리면 왼쪽부터
    - 거리 : 맨해튼 거리

    - 궁수의 배치 : 임의, 제거할 수 있는 최대 적의 수 ==> 완탐
     */
    static class Pair{
        int x;
        int y;
        int d;

        public Pair(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static int N, M, D;
    static int[][] map; // N+1 * M
    static int[][] tempMap; // N+1 * M
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    static boolean[] archers;
    static boolean[][] visited;
    static boolean[][] aimed;
    static int killCnt;
    static int tempKillCnt;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N+1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 지정; 조합
        archers = new boolean[M];
        killCnt = 0;
        combi(0,0);
        System.out.println(killCnt);
    }

    private static void combi(int start, int cnt) {
        if(cnt == 3){
//            System.out.println(Arrays.toString(archers));
            // 궁수 위치 선정 완료
            // 선정된 궁수 위치에서 게임 한 판을 모두 진행해야함.

            tempKillCnt = 0;
//            int tempD = D;
            tempMap = new int[N][M];
            copyTheMap();
            while (alive()) {
//            for (int n=N-1; n>=0; n--) {
                // 적 제거 로직
                aimed = new boolean[N][M];
                for (int i = 0; i < M; i++) {
                    if(archers[i]){
                        //bfs를 이용하여 가장 가까운 적을 찾음
                        visited = new boolean[N+1][M];
                        visited[N-1][i] = true;
                        searchAnEnemy(new Pair(N-1, i, 1)); // 궁수 바로 위 부터 시작(최단 거리 == 1)
                    }
                }
                // 적 제거
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if(aimed[i][j]) tempMap[i][j] = 0;
                    }
                }
                // 적의 이동
                for (int n = N-1; n > 0; n--) {
                    for (int i = 0; i < M; i++) {
                        tempMap[n][i] = tempMap[n-1][i];
                    }
                }
                for (int i = 0; i < M; i++) {
                    tempMap[0][i] = 0;
                }
            }
            killCnt = Math.max(killCnt, tempKillCnt);

            return;
        }

        for (int i = start; i < M; i++) {
            archers[i] = true;
            combi(i+1, cnt+1);;
            archers[i] = false;
        }
    }

    private static boolean alive() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tempMap[i][j] == 1) return true;
            }
        }
        return false;
    }

    private static void copyTheMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
    }

    private static void searchAnEnemy(Pair start) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            int cd = current.d;
            if(cd > D){ // 사정 거리 이내에 적이 없으면 종료
                return;
            }
            if(tempMap[cx][cy] == 1){ // 적을 발견하면 종료
//                tempMap[cx][cy] = 0;
                if(!aimed[cx][cy]) { // 공격 당한 적이 아닌 경우 카운트
                    aimed[cx][cy] = true;
                    tempKillCnt++;
                }
                // 이미 공격 당한 적은 카운트에서 제외
                return;
            }
            for (int i = 0; i < 3; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nd = cd + 1;
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new Pair(nx,ny,nd));
                }
            }
        }

    }
}
