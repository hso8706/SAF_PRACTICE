package implementation_and_bruteForce;

import java.io.*;
import java.util.*;

/*
참고 반례
4 1 9
96 93 74 30
60 90 65 96
5 27 17 98
10 41 46 20
=>1
 */

public class JUN16234_인구이동 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, L, R;
    static int[][] map;
    static int[][] union;
    static boolean[][] visited;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //bfs 로 열 수 있는 국가 체크(국경선 열기)
        int days = 0;
        flag = true;

        while (flag) { //이동할 인구가 있었다면 반복하여 확인
            visited = new boolean[N][N];
            union = new int[N][N];
            movePop(); //인구 이동; 시간 복잡도: 2_500 * 2000 (완탐 요소 * 2000)
            if (flag) days++; //이동할 인구가 있었다면 날짜 증가
        }
        bw.write(days + "");
        bw.flush();
        bw.close();
    }

    private static void movePop() {
        //완탐을 통해서 본인 위치 선택
        //우, 하 방향만 탐색하여 본인과의 인구 수 차이 확인 (탐색 시 맵을 벗어나는 경우 넘어감)
        int link = 1; // 연결된 나라를 파악하기 위함
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y]) {
                    makeUnion(new Pair(x, y), link); // 시간 : 5_000_000에서 완탐이 되어버리면 125억이됨.
                    link++;
                }
            }
        }
        if (link == (N * N) + 1) flag = false; // link 변화가 없었으면 인구 이동이 없었음(false)
    }

    private static void makeUnion(Pair start, int link) { //굳이 link를 쓸 필요 없지만 새로운 boolean 배열의 생성을 부담하지 않기 위해서 수정 안함(시간 초과 해결)
        // bfs 메소드
        // 4방 탐색으로 갭 차이 조건 확인
        int sum = 0;
        int cnt = 0;

        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            union[cx][cy] = link;
            sum += map[cx][cy];
            cnt++; // 연합 나라 수 확인

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int gap = Math.abs(map[nx][ny] - map[cx][cy]);
                if (!visited[nx][ny] && gap >= L && gap <= R) {
                    visited[nx][ny] = true;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
        int aftMov =  (int) sum / cnt;
        queue.offer(start);
        union[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            map[cx][cy] = aftMov;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (union[nx][ny] == link) {
                    union[nx][ny] = 0;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
    }
}
