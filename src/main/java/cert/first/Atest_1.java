package cert.first;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Atest_1 {
    static int cnt;
    static int idx;
    static PriorityQueue<int[]> queue;

    public static void solve() {
        int[] start = queue.poll();
        int v = start[0];
        int sx = start[1];
        int sy = start[2];

        while (!queue.isEmpty()) {
            int[] destination = queue.poll();
            int x = destination[1];
            int y = destination[2];

            if (idx % 4 == 0) {
                if (x < sx) {
                    cnt += 3;
                    idx += 3;
                } else if (sx < x) {
                    if (y < sy) {
                        cnt += 2;
                        idx += 2;
                    } else if (y > sy) {
                        cnt += 1;
                        idx += 1;
                    }
                }
            } else if (idx % 4 == 1) {
                if (sy < y) {
                    cnt += 3;
                    idx += 3;
                } else if (sy > y) {
                    if (x < sx) {
                        cnt += 2;
                        idx += 2;
                    } else if (x > sx) {
                        cnt += 1;
                        idx += 1;
                    }
                }
            } else if (idx % 4 == 2) {
                if (sx < x) {
                    cnt += 3;
                    idx += 3;
                } else if (sx > x) {
                    if (sy < y) {
                        cnt += 2;
                        idx += 2;
                    } else if (sy > y) {
                        cnt += 1;
                        idx += 1;
                    }
                }
            } else {
                if (y < sy) {
                    cnt += 3;
                    idx += 3;
                } else if (y > sy) {
                    if (sx < x) {
                        cnt += 2;
                        idx += 2;
                    } else if (sx > x) {
                        cnt += 1;
                        idx += 1;
                    }
                }
            }

            sx = x;
            sy = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int n = scanner.nextInt();
            int[][] graph = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }

            int anum = 0;
            queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};

            cnt = 0;
            idx = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 1) {
                        if (i != 0) {
                            cnt += 1;
                            idx += 1;
                        }
                    }

                    if (graph[i][j] != 0) {
                        queue.offer(new int[]{graph[i][j], i, j});
                    }
                }
            }

            solve();
            System.out.println("#" + tc + " " + cnt);
        }
    }
}
