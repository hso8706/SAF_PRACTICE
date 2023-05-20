package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW5650_핀볼게임_solved1 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;


    // 시뮬레이션 : 제시된 규칙을 꼼꼼히 챙긴다.

    // 좌표 처리 => int[] 사용 => 0: y, 1: x
    // 웜홀 좌표 저장 => int[][] 사용 => 첫 인덱스: 웜홀의 번호, 다음 인덱스: y, x, y, x좌표
    // 방향 => 방향 전환에 대한 편리한 구성(혹은 규칙)을 잡고 시작할 것.
    // 점수 조건, 종료 조건을 잘 지정할 것
    static int T, N, ans;
    static int[][] map;
    static int[][] warm;
    
    // 순서 규칙 설정
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine().trim());

        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(bf.readLine().trim());
            map = new int[N][N];
            warm = new int[11][4]; // 5~10: 실사용 구간, 4: (y,x) (y,x)

            for (int i = 6; i <= 10; i++) {
                warm[i][0] = warm[i][1] = warm[i][2] = warm[i][3] = -9; // 사용되지 않은 warm 초기화
            }

            ans = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine().trim());
                for (int j = 0; j < N; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    map[i][j] = n;

                    if( n >= 6 ){
                        if( warm[n][0] == -9){
                            warm[n][0] = i;
                            warm[n][1] = j;
                        }
                        else {
                            warm[n][2] = i;
                            warm[n][3] = j;
                        }
                    }
                }
            }
            // 입력 완료

            // 핀볼 게임 시작
            // 모든 출발점, 모든 방향을 다 고려해야한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == 0){ // 출발 지점

                        map[i][j] = -1; // 출발 지점을 블랙홀로 잠시 변경 => 종료 지점을 두 번 비교하지 않기 위해서

                        for (int d = 0; d < 4; d++) { // 방향 설정
                            int cnt = go(i, j, d);
                            ans = Math.max(ans, cnt);
                        }
                        map[i][j] = 0; // 변경했던 출발 지점을 다시 원상 복구
                    }
                }
            }
            System.out.println("#"+t+" "+ans);
        }
    }

    private static int go(int sy, int sx, int d) {
        int cnt = 0;

        // 시뮬레이션 구조
        int cy = sy;
        int cx = sx;

        while(true){ //while(true) 설정 후 내부에서 탈출구를 마련하는 일반적인 시뮬레이션 구존
            int num = map[cy][cx];
            
            int ny = 0;
            int nx = 0;
            
            // 방향 전환이 필요하면 먼저 방향 전환을 진행
            if ( 1 <= num && num <= 5) { // 1~5번 블록
                cnt++;

                switch (num){
                    case 1: // 상, 우 반대로, 하 -> 우 좌 -> 상
                        if( d == 0 || d == 3 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
                        else d = d == 1 ? 0 : 3;
                        break;
                    case 2:
                        if( d == 2 || d == 3 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
                        else d = d == 0 ? 3 : 2;
                        break;
                    case 3:
                        if( d == 1 || d == 2 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
                        else d = d == 0 ? 1 : 2;
                        break;
                    case 4:
                        if( d == 0 || d == 1 ) d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
                        else d = d == 2 ? 1 : 0;
                        break;
                    case 5:
                        d = ( d % 2 == 0 ) ? 2 - d : 4 - d;
                        break;
                }
            }
            // 좌표 이동
            ny = cy + dy[d];
            nx = cx + dx[d];

            // 벽 (range) 체크
            if ( ny<0 || nx<0 || nx>=N || ny>=N ){
                cnt++; // 점수 증가
                d = ( d % 2 == 0) ? 2-d : 4-d; //방향 전환
                // 방향 전환 후 원 좌표로 복쉬
                ny = cy;
                nx = cx;
            }

            // 종료, 웜홀 => 시작 지점을 블랙홀로 만들어서 블랙홀만 만나면 종료
            if( map[ny][nx] == -1 ){
                break;
            }
            else if( map[ny][nx] >= 6 ){
                int warmNum = map[ny][nx];

                if( warm[warmNum][0] == ny && warm[warmNum][1] == nx){
                    ny = warm[warmNum][2];
                    nx = warm[warmNum][3];
                }
                else {
                    ny = warm[warmNum][0];
                    nx = warm[warmNum][1];
                }
            }

            // 기준점 변경
            cy = ny;
            cx = nx;
        }
        
        return cnt;
    }
}
