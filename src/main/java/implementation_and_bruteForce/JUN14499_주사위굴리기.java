package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN14499_주사위굴리기 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 주사위 굴리기
    - 지도 숫자, 주사위 숫자 존재
    - 굴러간 주사위가 멈춘 곳의 지도가 0 인 경우,
        - 지도와 맞닿은 주사위 바닥면의 숫자가 지도로 복사됨
    - 굴러간 주사위가 멈춘 곳의 지도가 0 이 아닌 경우,
        - 지도와 맞닿은 주사위 바닥면의 숫자로 지도의 숫자가 복사됨
    - 명령대로 주사위가 이동했을때, 이동할 때마다의 주사위 상단 값 출력
    - 지도 밖으로 나가는 명령일 경우,
        - 명령 불이행, 그냥 패스
        
    - 주의
        - 처음 다이스는 모든 면이 0인 상태
        - 주사위를 가로, 세로로 나눌 때 연결 지점이 필요
            - 주사위가 굴러 간 후 가로, 세로 모두 재설정이 필요
            - 바닥과 천장만 재설정
     */

    // 주사위는 정해진 방향대로 1칸씩 굴러감 => 가로, 세로 배열을 따로 나눔 (랜덤이 아니다)
    // 0번 인덱스가 처음 시작할 때의 바닥값
    // 2번 인덱스가 처음 시작할 때의 천장값
    // 그 외 값도 처음 세팅값에 맞춤
    static int[] diceX = new int[]{0, 0, 0, 0}; // 주사위를 세로방향으로 굴리는 경우
    static int[] diceY = new int[]{0, 0, 0, 0}; // 주사위를 가로방향으로 굴리는 경우

    static int N, M; //지도 크기
    static int[][] map;
    static int x;
    static int y;
    static int K; //명령 개수

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 지도 채우기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 주사위 굴리기
        // 좌표 이동 + 주사위 인덱스 추가
        int dIdxX = 0; // 주사위 바닥 인덱스 세로
        int dIdxY = 0; // 주사위 바닥 인덱스 가로
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < K; i++) {
            int operator = Integer.parseInt(st.nextToken());
            int upside = 0; // 상단 출력값
            switch (operator) {
                case 1:
                    if(y+1 >= M) continue;
                    y++;
                    dIdxY++;
                    if (dIdxY == 4) dIdxY = 0;

                    if (map[x][y] == 0) map[x][y] = diceY[dIdxY];
                    else {
                        diceY[dIdxY] = map[x][y];
                        map[x][y] = 0;
                    }

                    upside = (dIdxY + 2) % 4;
                    bw.write(diceY[upside] +"\n");

                    diceX[dIdxX] = diceY[dIdxY];
                    diceX[(dIdxX + 2) % 4] = diceY[upside];
                    break;
                case 2:
                    if(y-1 < 0) continue;
                    y--;
                    dIdxY--;
                    if (dIdxY == -1) dIdxY = 3;

                    if (map[x][y] == 0) map[x][y] = diceY[dIdxY];
                    else {
                        diceY[dIdxY] = map[x][y];
                        map[x][y] = 0;
                    }

                    upside = (dIdxY + 2) % 4;
                    bw.write(diceY[upside] +"\n");

                    diceX[dIdxX] = diceY[dIdxY];
                    diceX[(dIdxX + 2) % 4] = diceY[upside];
                    break;
                case 3:
                    if(x-1 < 0) continue;
                    x--;
                    dIdxX--;
                    if (dIdxX == -1) dIdxX = 3;

                    if (map[x][y] == 0) map[x][y] = diceX[dIdxX];
                    else {
                        diceX[dIdxX] = map[x][y];
                        map[x][y] = 0;
                    }

                    upside = (dIdxX + 2) % 4;
                    bw.write(diceX[upside] +"\n");

                    diceY[dIdxY] = diceX[dIdxX];
                    diceY[(dIdxY + 2) % 4] = diceX[upside];
                    break;
                case 4:
                    if(x+1 >= N) continue;
                    x++;
                    dIdxX++;
                    if (dIdxX == 4) dIdxX = 0;

                    if (map[x][y] == 0) map[x][y] = diceX[dIdxX];
                    else {
                        diceX[dIdxX] = map[x][y];
                        map[x][y] = 0;
                    }

                    upside = (dIdxX + 2) % 4;
                    bw.write(diceX[upside] +"\n");

                    diceY[dIdxY] = diceX[dIdxX];
                    diceY[(dIdxY + 2) % 4] = diceX[upside];
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}
