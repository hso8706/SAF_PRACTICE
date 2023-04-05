package solved.forA;

import java.io.*;
import java.util.StringTokenizer;

public class SW2382 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 미생물 격리
    - NxN 구역, K개 미생물 군집, 가장자리(약품 구역)는 접근 불가
    - 미생물 군집 정보: 위치, 미생물 수, 이동 방향
    - 이동
        - 1시간마다 이동
        - 약품 구역에 도착하면 미생물 절반 죽고 이동방향 반대로 전환
        - 미생물 수가 홀수인 경우, 2로 나누고 소수점 이하를 버림
        - 미생물 수가 0이 된 경우 군집은 사라진 것으로 간주
        - 두 개 이상의 군집이 만나면 미생물 수가 합쳐짐 => 한 공간에 둘 다 도착해야함
        - 합쳐진 군집의 이동방향은 합쳐지기 전 군집 중 미생물 수가 많은 쪽의 이동방향을 따라감
    - 목표 : M 시간 후 남아있는 미생물 수의 총 합
     */
    static class Microbe {
        int num; // 미생물 번호
        int x; // 세로 위치
        int y; // 가로 위치
        int amount; // 미생물 수
        int dir; // 이동 방향

        public Microbe(int num, int x, int y, int amount, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.amount = amount;
            this.dir = dir;
        }
    }

    static int T, N, M, K; // T: tc, N: 셀 개수, M: 격리 시간, K: 미생물 군집 수
    static Microbe[][] glass, temp; // glass: 현실 배열, temp: 이동을 위한 임시 배열
    static Microbe[] microbes; // 입력값
    static boolean[][] safety;
    static int[] movement = new int[]{0, -1, 1, -1, 1}; // 1: 상, 2: 하, 3: 좌, 4: 우
    static int totalAmount;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            bw.write("#"+tc+" ");
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            glass = new Microbe[N][N];
            //안전 구역 체크
            safety = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                safety[i][0] = true;
                safety[i][N-1] = true;
                safety[0][i] = true;
                safety[N-1][i] = true;
            }
            for (int k = 1; k < K + 1; k++) {
                st = new StringTokenizer(bf.readLine());
                int num = k;
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                glass[x][y] = new Microbe(num, x, y, amount, dir);
            }
            for (int k = 0; k < K; k++) {
                temp = new Microbe[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(glass[i][j] != null){
                            move(glass[i][j]);
                        }
                    }
                }
                //temp -> glass
                reverseClone();
            }
            counting();
            bw.write(totalAmount + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void counting() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(glass[i][j] != null){
                    totalAmount += glass[i][j].amount;
                }
            }
        }
    }

    private static void reverseClone() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                glass[i][j] = temp[i][j];
            }
        }
    }

    private static void move(Microbe microbe) {
        int num = microbe.num;
        int nx = microbe.x + movement[microbe.dir];
        int ny = microbe.y + movement[microbe.dir];
        int amount = -1;
        int newDir = -1;
        if (safety[nx][ny]) { // 안전 구역 도착 로직
            amount = (int)microbe.amount/2;
            switch (microbe.dir){
                case 1:
                    newDir = 2;
                    break;
                case 2:
                    newDir = 1;
                    break;
                case 3:
                    newDir = 4;
                    break;
                case 4:
                    newDir = 3;
                    break;
            }
        }
        else { // 안전 구역 아닌 경우
            if (temp[nx][ny] != null){ // 합쳐지는 경우
                newDir = temp[nx][ny].amount > microbe.amount? temp[nx][ny].dir : microbe.dir;
                amount = microbe.amount + temp[nx][ny].amount;
            }
            else {
                newDir = microbe.dir;
                amount = microbe.amount;
            }
        }
        temp[nx][ny] = new Microbe(num, nx, ny, amount, newDir);
    }
}
