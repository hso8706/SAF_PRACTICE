package implementation_and_bruteForce;

<<<<<<< HEAD
import java.io.*;
import java.util.StringTokenizer;

public class JUN15683_감시 {
=======
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN15683_감시 {

>>>>>>> 88600e0caaecfc033e39226f935f8800b94f7017
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

<<<<<<< HEAD
    /*
    - 1,2,3,4,5 감시 카메라 존재
        - 각 감시 카메라는 감시할 수 있는 방향이 존재
    - 6 == 벽
    - 사각 지대를 최소로 하는, 즉 감시 범위(#)가 가장 클 때의 사각 지대 범위를 출력
    - 카메라 종류
        - 1번 : 1방향, 4가지
        - 2번 : 2방향, 대칭, 2가지 //0: 좌우, 1: 상하
        - 3번 : 2방향, 90도 인접, 4가지
        - 4번 : 3방향, 4가지
        - 5번 : 4방향, 1가지

    - cctv 개수 <= 8개
    - 감시 방향이 다른 경우가 존재하는 cctv의 모든 경우의 수를 고려해야할 듯 하다.
        - 순열을 사용하고 입력받은 cctv 개수가 되면 재귀 종료
        - 재귀 종료되는 순간마다 최소 사각 지대 범위 구하는 메소드 실행
    
    - 감시 카메라 배열 필요 : 총 8개, null 인 부분은 패스
    - 감시 카메라 클래스 필요 : 좌표와 방향, 카메라 종류
     */
    static class CctvInfo {
        int x;
        int y;
        int kind;
        int dir;

        public CctvInfo(int x, int y, int kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }
    }

    static int N, M; // size
    static int[][] map; // 입력을 저장할 원본
    static int[][] mapTemp; // 메소드에서 활용할 사봄
    static CctvInfo[] cctvs = new CctvInfo[8];
    static int cctvLastIdx;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
=======
    static int[] dx = new int[]{ -1, 0, 1, 0 };
    static int[] dy = new int[]{ 0, 1, 0, -1 };

    static int N, M;
    static int[][] map;
    /*
    ### 감시
    - 사각 지대의 최소 크기를 출력
     */
>>>>>>> 88600e0caaecfc033e39226f935f8800b94f7017

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
<<<<<<< HEAD
        mapTemp = new int[N][M];
=======
>>>>>>> 88600e0caaecfc033e39226f935f8800b94f7017
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
<<<<<<< HEAD
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs[cctvLastIdx] = new CctvInfo(i, j, map[i][j]);
                    cctvLastIdx++;
                }
            }
        }

        //순열과 재귀를 이용하여 방향 경우의 수를 구하는 메소드
        makeDir(0);
    }

    //모든 방향의 경우의 수를 만드는 메소드
    private static void makeDir(int cnt) {
        if (cnt == cctvLastIdx) {
            //하나의 방향의 경우의 수가 완성된 시점
            int blindSpot = howWide();
            return;
        }

        if (cctvs[cnt].kind == 5) makeDir(cnt + 1);
        else if (cctvs[cnt].kind == 2) {
            for (int j = 0; j < 2; j++) {
                cctvs[cnt].setDir(j);
                makeDir(cnt + 1);
            }
        } else {
            for (int j = 0; j < 4; j++) {
                cctvs[cnt].setDir(j);
                makeDir(cnt + 1);
            }
        }
    }

    /*
        - 카메라 종류
            - 1번 : 1방향, 4가지
            - 2번 : 2방향, 대칭, 2가지 //0: 좌우, 1: 상하
            - 3번 : 2방향, 90도 인접, 4가지
            - 4번 : 3방향, 4가지
            - 5번 : 4방향, 1가지
     */
    private static int howWide() {
        for (int i = 0; i < cctvLastIdx; i++) {
            switch (cctvs[i].kind) {
                case 1://색칠하는 로직 구현
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
    }
=======
            }
        }


    }

>>>>>>> 88600e0caaecfc033e39226f935f8800b94f7017
}
