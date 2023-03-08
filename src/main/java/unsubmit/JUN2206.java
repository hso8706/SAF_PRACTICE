    package unsubmit;

    import java.io.*;
    import java.util.*;

    public class JUN2206 {
        static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringTokenizer st;

        /*
        아이디어1. => 시간 초과
        1. 값이 1인곳의 좌표를 받아 인덱스로 저장
        2. 해당 인덱스를 순회하며 벽 하나씩 허물어보기
        3. 2에 이어서 허문 상태의 맵을 bfs 순회
        4. 2, 3을 반복해서 최소 경로 갱신
         */
        static int N, M; // 맵 크기
        static int[][] map;
    //    static boolean[][] visited;
        static int[][] walls;
        static int minRoute;
        static int[] dx = new int[]{-1, 0, 1, 0};
        static int[] dy = new int[]{0, 1, 0, -1};

        public static void main(String[] args) throws IOException {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N + 1][M + 1];
    //        visited = new boolean[N + 1][M + 1];
            walls = new int[N * M][2];
            minRoute = Integer.MAX_VALUE;

            int idx = 0;
            Arrays.fill(walls, null); // 없는 부분 순회안하기 위한 체크 포인트 null

            for (int i = 1; i < N + 1; i++) {
                String[] temp = bf.readLine().split("");
                for (int j = 1; j < M + 1; j++) {
                    map[i][j] = Integer.parseInt(temp[j - 1]);
                    if (map[i][j] != 0) {
                        walls[idx] = new int[]{i, j};
                        idx++;
                    }
                }
            }
            if(idx != 0) {
                for (int[] point : walls) {
                    if (point != null) {
                        map[point[0]][point[1]] = 0;
                        bfs(1, 1, 1, new boolean[N + 1][M + 1]);
                        map[point[0]][point[1]] = 1;
                    }
                }
            }
            else bfs(1, 1, 1, new boolean[N + 1][M + 1]);
            minRoute = minRoute == Integer.MAX_VALUE ? -1 : minRoute;
            bw.write(minRoute + "");
            bw.flush();
            bw.close();
        }

        private static void bfs(int x, int y, int cnt, boolean[][] visited) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{x, y, cnt});
            visited[x][y] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int cx = current[0];
                int cy = current[1];
                int cc = current[2];
                if (cx == N && cy == M) {
                    minRoute = Math.min(minRoute, cc);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (nx < 1 || ny < 1 || nx > N || ny > M || map[nx][ny] == 1) continue;
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, cc + 1});
                    }
                }
            }
        }
    }
