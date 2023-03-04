package aSAF.solution_230303;

import java.io.*;
import java.util.*;

public class JUN7576_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 토마토 문제
    - 문제 해결 포인트
        1. 사방 탐색 bfs, 인접 토마토 확인
        2. 익은 토마토로 입력된 지정 모두에서 bfs 시작
        3. 한 싸이클(day)을 표시할 수 있는 방법 고려
     */
    static int N, M; // N: 세로, M: 가로
    static int[][] box; // 토마토 박스
    static boolean[][] visited; // 방문 여부 체크
    static Queue<int[]> queue = new ArrayDeque<>(); // 0: 세로 좌표, 1: 가로 좌표, 2: dayCnt
    static int maxDays;
    static int[] dn = new int[]{1,0,-1,0};
    static int[] dm = new int[]{0,1,0,-1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        visited = new boolean[N][M];
        maxDays = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1){
                    queue.offer(new int[]{i, j, 0}); // 입력 시 주어지는 익은 토마토 저장, bfs의 시작점들
                }
            }
        }

        bfs();
        if(isIt()) bw.write(-1 + "");
        else bw.write(maxDays + "");
        bw.flush();
        bw.close();
    }

    private static boolean isIt() {//안 익은 토마토 찾기, 있으면 true 없으면 false
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) cnt++;
            }
        }
        if(cnt != 0) return true;
        return false;
    }

    private static void bfs() {
        while(!queue.isEmpty()){
            int[] current = queue.poll(); // 0: 세로 좌표, 1: 가로 좌표, 2: dayCnt
            int cn = current[0], cm = current[1], days = current[2];
            maxDays = Math.max(maxDays, days);
            visited[cn][cm] = true; // 현재 위치 방문 체크

            for (int i = 0; i < 4; i++) {
                int nn = cn + dn[i];
                int nm = cm + dm[i];
                if(nn < 0 || nm <0 || nn >=N || nm >= M || box[nn][nm] == -1) continue;
                if (!visited[nn][nm] && box[nn][nm] == 0){
                    queue.offer(new int[]{nn, nm, days + 1}); // 다음 뎁스를 넘어갈 때마다 day를 늘리는 포인트
                    box[nn][nm] = 1;
                }
            }
        }
    }
}
