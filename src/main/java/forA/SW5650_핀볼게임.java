package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW5650_핀볼게임 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 임의의 시작점, 임의의 시작 방향 => 완탐 가능성
    - 게임에서 얻을 수 있는 점수의 최댓값 => 결국 완탐
        => 2중 반복문으로 map을 순회하며 시작 지점 선정
        => 추가 반복문으로 시작 지점에서 진행할 방향을 선정
    - 블록
        - 벽을 만나면 반대 방향으로 진행
        - 블록의 대각선을 만나면 90도 꺾여서 진행
        - 블록에 닿은 경우, 어떤 진행 방향으로 닿았는지 알아야한다.

    - 웜홀을 6~10번(5개), 쌍으로 존재, 하나의 웜홀에 위치하게 되면 같은 진행 방향을 지닌채로 다른 웜홀에서 시작
        - 웜홀의 위치 정보를 지녀야한다.

    - 점수 조건
        - 벽에 부딪힘
        - 블록에 부딪힘

    - 종료 조건
        - 블랙홀(-1)을 만나면 종료
        - 시작점으로 돌아오면 종료(시작점 저장)
        - 종료 시 점수 갱신
     */
    //입력
    static class Pair {
        int x;
        int y;
        int d;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d; // 위쪽부터 시계방향
        }
    }
    static int T, N;
    static int[][] map;
    static Pair end;
    static Pair[][] wormHole;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int maxScore;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#" + t + " ");

            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            wormHole = new Pair[11][2]; // 0~5: null, 6~10: 사용, [0],[1]: 다른 두 통로
            boolean[] wormHoleCheck = new boolean[11];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] >=5 && map[i][j] <=10){
                        if(wormHoleCheck[map[i][j]]) wormHole[map[i][j]][0] = new Pair(i, j);
                        else wormHole[map[i][j]][1] = new Pair(i, j);
                        wormHoleCheck[map[i][j]] = !wormHoleCheck[map[i][j]];
                    }
                }
            }

            //핀볼 게임 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == 0) {
                        end = new Pair(i, j);
                        for (int d = 0; d < 4; d++) {
                            startGame(new Pair(i, j, d));
                        }
                    }
                }
            }
            bw.write(maxScore + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void startGame(Pair start) {
        int score = 0;
        int cx = start.x;
        int cy = start.y;
        int cd = start.d;

        //종료 조건 전까지 무한 반복 루프
        while(true){
            //다음 진행 위치 확인
            int nx = cx + dx[cd];
            int ny = cy + dy[cd];
            //벽에 부딪힌 경우 => 반대 방향으로 전환
            if(nx<0 || ny<0 || nx>=N || ny>=N){
                score++;
                cd = reverseDir(cd);
                nx = cx;
                ny = cy;
            }
            //cd ==> 0: 상, 1: 우, 2: 하, 3: 좌
            switch (map[nx][ny]){
                case -1://블랙홀
                    maxScore = Math.max(maxScore, score);
                    return;
                case 1:
                    score++;
                    switch (cd){
                        case 0:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                        case 1:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                        case 2: // 하 > 우
                            cd = 1;
                            break;
                        case 3: // 좌 > 상
                            cd = 0;
                            break;
                    }
                    break;
                case 2:
                    score++;
                    switch (cd){
                        case 0: // 상 > 우
                            cd = 1;
                            break;
                        case 1:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                        case 2:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                        case 3: // 좌 > 하
                            cd = 2;
                            break;
                    }
                    break;
                case 3:
                    score++;
                    switch (cd){
                        case 0: // 상 > 좌
                            cd = 3;
                            break;
                        case 1: // 우 > 하
                            cd = 2;
                            break;
                        case 2:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                        case 3:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                    }
                    break;
                case 4:
                    score++;
                    switch (cd){
                        case 0:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                        case 1: // 우 > 상
                            cd = 0;
                            break;
                        case 2:// 하 > 좌
                            cd = 3;
                            break;
                        case 3:
                            cd = reverseDir(cd);
                            nx = cx;
                            ny = cy;
                            break;
                    }
                    break;
                case 5:
                    score++;
                    cd = reverseDir(cd);
                    break;
                case 0:
                    break;
                default: // 웜홀
                    Pair outWormHole = findWormHole(nx, ny);
                    nx = outWormHole.x;
                    ny = outWormHole.y;
                    break;
            }

            //다음 위치로 이동 완료
            cx = nx;
            cy = ny;

            //기저 조건: 현재 위치가 end 면 종료
            if(cx == end.x && cy == end.y){
                maxScore = Math.max(maxScore, score);
                return;
            }
        }
    }

    private static Pair findWormHole(int x, int y) {
        Pair outWormHole = null;
        int idx = map[x][y];
        for (int i = 0; i < 2; i++) {
            if(x == wormHole[idx][i].x && y == wormHole[idx][i].y) {
                int outIdx = i == 0 ? 1 : 0;
                outWormHole = wormHole[idx][outIdx];
            }
        }
        return outWormHole;
    }

    private static int reverseDir(int cd) {
        int newCd = 0;
        if(cd == 0 || cd == 2){
            newCd = cd == 0 ? 2 : 0;
        }
        else {
            newCd = cd == 1 ? 3 : 1;
        }
        return newCd;
    }
}
