package aSAF.ASolution_230307;

import java.io.*;
import java.util.*;

public class JUN16236_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 아기 상어
    1. 첫 시도
    - 사방탐색만으로 시도
    - 물고기도 잘 찾아고 잘먹음. 로직은 괜찮았음.
    - 문제는 먹을 수 있는 물고기에 대한 우선 순위를 두는 조건을 지정하기 힘들었음.
        - 처음에는 사방탐색의 순서로 해결해보려했으나 실패
        - 사방탐색으로 하려면 1번의 사방 탐색이 마무리된 후, 먹을 수 있는 조건의 물고기들의 좌표를 저장하고 비교하는 과정이 더 필요했을 듯 싶음.

    2. 두번째 시도
    - bfs 를 통해 먹을 수 있는 물고기 탐색 및 리스트 저장
        - 리스트의 요소는 물고기의 좌표와 거리를 저장하는 객체
        - 해당 객체를 거리 순, 좌표 순으로 정렬할 수 있어야함.(Comparable 사용)
    - 정렬된 리스트의 가장 처음을 잡아먹음. 상어 위치 변경
        - 자동 정렬을 위해 pq 사용
        - 물고기를 잡아먹은 후에는 pq를 비우고, 다시 pq를 생성하여 위치를 찾음
    - 잡아먹고 위치가 바뀐뒤에는 위 과정을 반복.
     */

    static int N; // 공간의 크기
    static int[][] sea; // 공간 배열
    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};//동일 거리 물고기 먹는 순서
    static int eacCnt; // 먹은 물고기 수 체크
    static int sharkSize; // 상어의 현재 사이즈 체크
    static int result; // 출력을 위한 거리 측정
    static int sharkX; // 현재 상어 x좌표
    static int sharkY; // 현재 상어 y좌표
    static PriorityQueue<FishInfo> pq = new PriorityQueue<>();
    static class FishInfo implements Comparable<FishInfo> {
        int x; // 물고기 x(세로)좌표
        int y; // 물고기 y(가로)좌표
        int distance; // 상어와 물고기까지의 거리

        @Override
        public int compareTo(FishInfo o) { //우선 순위 1: 거리, 2: 세로(x)축, 3: 가로(y)축
            if(this.distance == o.distance){
                if(this.x == o.x){
                    return this.y - o.y; // 가장 왼쪽
                }
                return this.x - o.x; // 가장 위쪽
            }
            return this.distance - o.distance; // 최소 거리
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
                if(sea[i][j] == 9){ // 첫 상어 시작점 체크 및 자리 비워주기
                    sharkX = i;
                    sharkY = j;
                    sea[i][j] = 0;
                }
            }
        }
        while(true){ // 메서드 묶음 반복
            // 찾기
            canEat(sharkX, sharkY, new boolean[N][N]); // 먹을 수 있는 물고기 탐색 메서드
            // 먹기
            if(!doEat()) break; // 먹을게 있는지, 있으면 먹고나서의 처리를 해주는 메서드
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    // 먹을게 있는지, 있으면 먹고나서의 처리를 해주는 메서드
    private static boolean doEat() { // 먹을게 있으면 true, 먹을게 없으면 false(==종료)
        // 정렬된 리스트의 가장 처음 값 꺼내기 (가장 가까운 > 가장 위쪽인 > 가장 왼쪽인) 
        FishInfo fish = pq.poll();
        if(fish == null) return false; // 먹을 수 있는 물고기가 없다면 false 반환
        // 먹을 수 있는 물고기가 있는 경우
        // result 값 변경
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
        pq.clear(); // 가장 첫 물고기만 먹고 나머지를 비워서 처음부터 다시 진행
        return true;
    }

    private static void canEat(int x, int y, boolean[][] visited) {
        int cnt = 0; // 현재 지점에서 떨어진 거리를 구하기 위한 변수

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
                if(nx<0 || ny<0 || nx>=N || ny>=N || sea[nx][ny] > sharkSize) continue; //지나갈 수 없는 경우
                if(!visited[nx][ny] && (sea[nx][ny] == sharkSize || sea[nx][ny] == 0)){//그냥 지나가는 경우
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cc+1});
                }
                if(!visited[nx][ny] && sea[nx][ny] < sharkSize){ //먹을 수 있는 물고기인 경우
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cc+1});
                    pq.offer(new FishInfo(nx, ny, cc+1));
                }
            }
        }
    }
}
