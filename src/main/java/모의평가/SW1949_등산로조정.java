package 모의평가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW1949_등산로조정 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 필드
    - T, N, K
    - map 정보
    - Peek 배열
    - 최대 길이

    ### 등산로 탐색 로직(dfs)
    - (현재 위치보다 낮은 위치인지) && (방문하지 않은 곳인지)
    - 더 이상 진행할 곳이 없다면, 최대 길이 갱신

    ### 산 깎는 로직
    - 깎았는지 여부 확인(T/F)
    - F && (현재 위치 - 다음 위치) <= K 이면 산 깎기
     */
    static int T, N, K;
    static int[][] map;
    static List<int[]> peek;
    static int maxLength;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            peek = new ArrayList<>();
            inputMapInfo();
            maxLength = 0;

            for(int[] spot : peek){
                int x = spot[0];
                int y = spot[1];
                visited = new boolean[N][N];
                visited[x][y] = true;
                dfs(x,y, 1, false);
            }
            bw.write("#"+t+" "+maxLength + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int x, int y, int cnt, boolean flag) {
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
            int heightDIff = map[x][y] - map[nx][ny];
            if(!visited[nx][ny]) {
                if (heightDIff > 0) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, cnt+1, flag);
                    visited[nx][ny] = false;
                }
                else {
                    if(!flag && Math.abs(heightDIff) <= K && heightDIff+K >0) {
                        flag = true;
                        for (int j = 1; j <= K; j++) {
                            map[nx][ny] -= j;
                            heightDIff = map[x][y] - map[nx][ny];
                            if (heightDIff > 0) {
                                visited[nx][ny] = true;
                                dfs(nx, ny, cnt+1, flag);
                                visited[nx][ny] = false;
                            }
                            map[nx][ny] += j;
                        }
                        flag = false;
                    }
                    else maxLength = Math.max(maxLength, cnt);
                }
            }
            else maxLength = Math.max(maxLength, cnt);
        }
    }

    private static void inputMapInfo() throws IOException {
        int peekPoint = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(peekPoint < map[i][j]){
                    peek.clear();
                    peekPoint = map[i][j];
                    peek.add(new int[]{i, j});
                }
                else if(peekPoint == map[i][j]){
                    peek.add(new int[]{i, j});
                }
            }
        }
    }
}
