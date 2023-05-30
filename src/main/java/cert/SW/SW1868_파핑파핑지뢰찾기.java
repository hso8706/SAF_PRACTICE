package cert.SW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1868_파핑파핑지뢰찾기 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 조건
    - N*N 맵
    - 지뢰가 있거나 없거나
    - 0, 1~8, 지뢰
    - 0이면 8방에 지뢰가 없고, 자동으로 판을 밝힘

    ### 출력
    - 지뢰가 아닌 칸을 모두 밝혔을 때, 가장 최소의 클릭인 상황 출력

    ### 풀이
    - 지뢰의 8방을 제외한 곳에서 시작
    - 완탐 => 조금은 최적화 될 듯
    - 한 번의 완탐 내에서 map 을 클론해서 사용
    - visited 필요(bfs 사용)
    - 공백 -1, 지뢰 -2
    - 지뢰를 저장하는 리스트 => 매번 8방 확인 필요
     */
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int T, N;
    static int[][] map, cMap;
    static boolean[][] visited, cVisited;
    static ArrayList<Pair> mines;
    static int minValue;
    static int[] dx = new int[]{-1,-1,0,1,1,1,0,-1}; //북부터 시계 방향
    static int[] dy = new int[]{0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            cMap = new int[N][N];
            visited = new boolean[N][N];
            cVisited = new boolean[N][N];
            mines = new ArrayList<>();
            minValue = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                char[] temp = bf.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    char c = temp[j];//- 공백 -1, 지뢰 -2
                    if(c == '.') map[i][j] = -1;
                    if(c == '*') {
                        map[i][j] = -2;
                        mines.add(new Pair(i,j));
                    }
                }
//                System.out.println(Arrays.toString(map[i]));
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] != -2) {
                        cloneMap();
                        cloneVisited();
                        bfs(new Pair(i,j));
                    }
                }
            }
        }
    }

    private static void bfs(Pair pair) {//0인 점에서 시작
        int clicks = 0;

        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(pair);
        cVisited[pair.x][pair.y] = true;
        cMap[pair.x][pair.y] = adjMine(pair.x, pair.y);
        clicks++;

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            //8방 탐색 후 지뢰가 아닌 곳 + 방문 처리가 안 된 곳으로 이동
            //이동 후 지뢰 리스트를 이용하여 cnt 누적 확인
            for (int i = 0; i < 8; i++) {
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || cMap[nx][ny] == -2) continue;
                if(!cVisited[nx][ny]){
                    cVisited[nx][ny] = true;
                    cMap[nx][ny] = adjMine(nx, ny);
                    if(cMap[nx][ny] == 0) queue.offer(new Pair(nx,ny));
                }
            }
        }

    }

    private static int adjMine(int cx, int cy) {
        int cnt = 0;
        for (int i = 0; i < mines.size(); i++) {
            if(mines.get(i).x == cx && mines.get(i).y == cy) cnt++;
        }
        return cnt;
    }

    private static void cloneVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cVisited[i][j] = visited[i][j];
            }
        }
    }

    private static void cloneMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cMap[i][j] = map[i][j];
            }
        }
    }
}
