package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN2933_미네랄 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - R*C map
    - 클러스터 : 인접한 미네랄 그룹
        - 창을 던진 후 매번 클러스터 구분을 해주어야 할 듯
    - 창 던지는 높이
        - 1: 배열의 가장 밑 바닥
        - R: 그 위로 R번째 층(높이)
    - 창에 맞은 미네랄은 파괴
        - 미네랄이 파괴될 때 클러스터가 분리될 때, 새로 생성된 클러스터가 하늘에 떠있다면 추락
            - 지면에 붙어있다면 분리되더라도 추락하지 않음
        - 추락은 땅 혹은 다른 클러스터에 만날때까지 진행
        - 추락 도중 클러스터 모양의 변형은 없음

    - 클러스터를 dfs로 확인하여 dfs의 끝이 map 바깥으로 나가지 않는다면 공중에 뜬 클러스터로 감지
    - 추락 로직도 고민
     */
    static int R, C, T;
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        //R, C 입력
        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        //map 초기화 및 입력
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] temp = bf.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[j];
            }
        }
        //T 횟수 입력
        T = Integer.parseInt(bf.readLine());
        //T에 대한 반복문 설정 및 홀수 짝수 나누기
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < T+1; i++) {
            int level = R- (Integer.parseInt(st.nextToken()));
            int tx = -1;
            int ty = -1;
            if(i%2 != 0){ // 왼쪽
                for (int j = 0; j < C; j++) {
                    if(map[level][j] == 'x') {
                        map[level][j] = '.';
                        tx = level;
                        ty = j;
                        break;
                    }
                }
            }
            else { // 오른쪽
                for (int j = C-1; j >= 0; j--) {
                    if(map[level][j] == 'x') {
                        map[level][j] = '.';
                        tx = level;
                        ty = j;
                        break;
                    }
                }
            }
            //클러스터 확인
            //맞은 지점 4방에서 확인
            for (int j = 0; j < 4; j++) {
                int nx = tx + dx[j];
                int ny = ty + dy[j];
                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == '.') continue;
                visited = new boolean[R][C];
                visited[nx][ny] = true;
                if(isFloated(nx, ny)){ // 클러스터가 부유하는 경우
                    falldown();
                };
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                bw.write(map[i][j]+"");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();

    }

    private static void falldown() {
        //visited 가 true 인 곳이 뷰유하는 클러스터가 있는 곳
        //클러스터 자체를 통째로 걸릴때까지 움직여줄 것

        //구간 복사
        int l = C-1;
        int r = 0;
//        int[] lowest = new int[C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(visited[i][j]){
                    l = Math.min(l, j);
                    r = Math.max(r, j);
//                    lowest[j] = Math.max(lowest[j], i); // 클러스터 구간 내 최하단 값
                }
            }
        }
        while(true) {
            char[][] temp = new char[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    temp[i][j] = map[i][j];
                }
            }
            boolean flag = true;
            for (int j = l; j <= r; j++) {
                for (int i = R - 2; i >= 0; i--) {
                    if (visited[i][j]) {
                        if (temp[i + 1][j] != 'x') {
                            temp[i + 1][j] = temp[i][j];
                            temp[i][j] = '.';
                            visited[i+1][j] = true;
                            visited[i][j] = false;
                        }
                        else {
                            flag = false;
                        }
                    }
                }
            }
            if(flag){
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        map[i][j] = temp[i][j];
                    }
                }
            }
            else break;
        }
    }

    private static boolean isFloated(int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || ny<0 || ny>=C ) continue;
                if(nx>=R) return false;
                if(map[nx][ny] == '.') continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }
        return true;
    }

}
