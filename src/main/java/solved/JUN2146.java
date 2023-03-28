package solved;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN2146 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    아이디어 1. 46% 메모리 초과
    - bfs 이용해서 섬 카운팅
    - 섬에 매겨진 각 번호를 이용해서 섬의 외곽 파악
    - 섬의 외곽에서 다른 섬의 외각이 닿는 최소 거리(bfs) 구하기
    - 모든 섬(번호)를 같은 방법으로 반복하여 최소 거리 갱신 => 흠 초과 각인디
    => 새로운 2차원 boolean 배열 만들기, 실패
    => 3차원 boolean 배열 이용, 실패
    => 각 섬의 임의 지점을 저장하기 + 이중 반복문 제거, 실패
    => 각 섬의 외곽만 특정짓고 모두 포인트 저장해서 순회, 성공

    개선
    내 제출: 메모리 79536 KB, 시간 276 ms
    1등 : 메모리 12104 KB, 시간 88 ms
     */
    static int[] dx = new int[]{1, 0, -1, 0}; // dx가 세로
    static int[] dy = new int[]{0, 1, 0, -1}; // 상우하좌
    static int N; // 지도 크기
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> startPoint = new ArrayList<>();

    static int minRoute; // 최소값 갱신

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        int cnt = 0; // 섬의 개수 및 구분
        minRoute = Integer.MAX_VALUE;

        fillMap();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || visited[i][j]) continue;
                cnt++;
                division(new int[]{i, j}, cnt);
            }
        }

        for (int[] start : startPoint) {
            int i = start[0];
            int j = start[1];
            visited = new boolean[N][N];
            buildBridge(i, j, map[i][j]);
        }

        bw.write(minRoute + "");
        bw.flush();
        bw.close();
    }

    private static void buildBridge(int x, int y, int island) {
        Queue<int[]> queue = new ArrayDeque<>();
        int cnt = 0;
        queue.offer(new int[]{x, y, cnt});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int cc = current[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == island) continue;
                if (!visited[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, cc + 1});
                    }
                    else if(map[nx][ny] != 0){
                        minRoute = Math.min(minRoute, cc);
                        return;
                    }
                }

            }
        }
    }

    private static void division(int[] current, int cnt) {
        Queue<int[]> queue = new ArrayDeque<>(); // 0: x(세로)좌표, 1: y 좌표
        queue.offer(current);
        visited[current[0]][current[1]] = true;

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            int cx = c[0];
            int cy = c[1];
            map[cx][cy] = cnt;

            boolean flag = false; // flag 반복문 순회 횟수를 줄이기 위한 변수
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(!flag && map[nx][ny] == 0){ // flag 를 통해 섬의 외곽에 닿았을 경우 반복문 순회 횟수를 줄일 수 있음.
                    startPoint.add(new int[]{cx, cy});
                    flag = true;
                }
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static void fillMap() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
