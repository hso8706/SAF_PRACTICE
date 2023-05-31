package cert.forth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
    오0          앞1          왼2          뒤3

    -1           -1           -1           -1
     0            0            0            0
    왼           앞           오           뒤
 0뒤▶▶앞0     0왼▲▲오0     0앞◀◀뒤0     0오▼▼왼0
-1  오  1    -1  뒤  1    -1  왼  1    -1  앞  1
     1            1            1            1
     0            0            0            0
 */
public class Solution_Prof {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T, N, M, max;
    static int[][] map, cop;
//    static int[] dr = {0,-1,0,1}; //오, 앞, 왼, 뒤
//    static int[] dc = {1,0,-1,0};
    static int[][] dr = {{1,0,-1,0},{0,-1,0,1},{-1,0,1,0},{0,1,0,-1}};
    static int[][] dc = {{0,1,0,-1},{1,0,-1,0},{0,-1,0,1},{-1,0,1,0}};

    static int[][] seedCnt; // 씨를 실제로 심는 맵
    static List<int[]> seeds; // 씨앗의 좌표 정보를 저장할 리스트
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");
            st= new StringTokenizer(bf.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            cop = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == 1) continue; // 1: 산
                    for (int d = 0; d < 4; d++) { // 0:오, 1:앞, 2: 왼, 3: 뒤
                        copyMap();
                        start(i,j,d);
                    }
                }
            }
            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 0: 빈 곳, 1: 산, 2: 싹, 3: 곡식
    private static void start(int i, int j, int d) {
        seedCnt = new int[N][N];
        seeds = new ArrayList<>();
        int cnt = 0; // 하나의 경우의 수확 카운트 횟수
        int m = M;
        while(m!=0){
            m--; // 하루 지남을 감소로 표현
            // 3 곡식 표시, 3일이 지나면,

            // 다음 이동가능한 방향 처리
            int nd = -1;

            if(nd == -1){
                continue;
            }
            else {

            }
        }
        max = Math.max(max, cnt);
    }

    private static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cop[i][j] = map[i][j];
            }
        }
    }
}
