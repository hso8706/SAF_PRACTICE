package aSAF.ASolution_230307;

import java.io.*;
import java.util.*;

public class JUN16236_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 아기 상어
    - bfs 를 통해 먹을 수 있는 물고기 탐색 및 리스트 저장
        - 리스트의 요소는 물고기의 좌표와 거리를 저장하는 객체
        - 해당 객체를 거리 순, 좌표 순으로 정렬할 수 있어야함.(Comparable 사용)
    - 정렬된 리스트의 가장 처음을 잡아먹음. 상어 위치 변경
    - 잡아먹고 위치가 바뀐뒤에는 위 과정을 반복.
     */

    static int N; // 공간의 크기
    static int[][] sea; // 공간 배열
    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};//동일 거리 물고기 먹는 순서
    static int eacCnt;
    static int sharkSize;
    static int result;
    static int sharkX;
    static int sharkY;
    static PriorityQueue<FishInfo> pq = new PriorityQueue<>();
    static class FishInfo implements Comparable<FishInfo> {
        int x; // 물고기 x(세로)좌표
        int y; // 물고기 y(가로)좌표
        int distance; // 상어와 물고기까지의 거리

        @Override
        public int compareTo(FishInfo o) { //우선 순위 1: 거리, 2: 세로(x)축, 3: 가로(y)축
            if(this.distance == o.distance){
                if(this.x == o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.distance - o.distance;
        }

        public FishInfo(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        sea = new int[N][N];
        eacCnt = 0;
        sharkSize = 2;
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if(sea[i][j] == 9){
                    sharkX = i;
                    sharkY = j;
                }
            }
        }
        // 찾기
        canEat(sharkX, sharkY, new boolean[N][N]);
        // 먹기
        if(!doEat()) ;

        bw.write(pq.size() + "");
        bw.flush();
        bw.close();
    }

    private static boolean doEat() {
        // 정렬된 리스트의 가장 처음 값 꺼내기
        FishInfo fish = pq.poll();
        // result 값 변경
        if(fish == null) return false;
        result += fish.distance;
        // 상어 위치 변경
        sharkX = fish.x;
        sharkY = fish.y;
        // 해당 지점 값 변경(-> 0)
        sea[fish.x][fish.y] = 0;
        // eatCnt 증가
        eacCnt++;
        // sharkSize 검사
        if (sharkSize == eacCnt){
            sharkSize++;
            eacCnt = 0;
        }
        return true;
    }

    private static void canEat(int x, int y, boolean[][] visited) {
        int cnt = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y, cnt});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int cc = current[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || sea[nx][ny] > sharkSize) continue;
                if(!visited[nx][ny] && (sea[nx][ny] == sharkSize || sea[nx][ny] == 0)){//그냥 지나가는 경우
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cc+1});
                }
                if(!visited[nx][ny] && sea[nx][ny] < sharkSize){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cc+1});
                    pq.offer(new FishInfo(nx, ny, cc+1));
                }
            }
        }
    }
}
