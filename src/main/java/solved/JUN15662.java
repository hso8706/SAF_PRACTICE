package solved;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN15662 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
     * 우 왼 1: 2 2: 6 2 3: 6 2 4: 6
     */
    static int T, K; // T: tc, K: 돌리는 횟수
    static ArrayList[] magnets; // 1~4번 마그넷 배열
    static int[] dx = new int[]{1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(bf.readLine());
        magnets = new ArrayList[T+1];
        for (int i = 1; i < T+1; i++) {
            magnets[i] = new ArrayList();
        }

        for (int i = 1; i < T+1; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < 8; j++) {
                magnets[i].add(Integer.parseInt(temp[j])); // 0: N, 1: S
            }
        }
        K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()); // 1: 시계, -1: 반시계
            rotate(idx, dir);
        }
        int sum = 0;
        for (int i = 1; i < T+1; i++) {
            sum += (int) magnets[i].get(0);
        }
        bw.write(sum + "\n");
        bw.flush();
        bw.close();
    }

    /*
     * 우 왼 1: 2 2: 6 2 3: 6 2 4: 6
     */
    private static void rotate(int idx, int dir) {
        boolean[][] mustRot = new boolean[T+1][T+1];
        for (int i = 1; i < T; i++) {
            if (magnets[i].get(2) != magnets[i + 1].get(6)) {
                mustRot[i][i + 1] = true;
            }
        }

        boolean[] visited = new boolean[T+1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{idx, dir});
        visited[idx] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cd = current[1];
            if (cd == 1) {
                for (int i = 0; i < 7; i++) {
                    magnets[cx].add(magnets[cx].get(0));
                    magnets[cx].remove(0);
                }
            } else {
                magnets[cx].add(magnets[cx].get(0));
                magnets[cx].remove(0);
            }


            for (int i = 0; i < 2; i++) {
                int nx = cx + dx[i];
                if (nx <= 0 || nx > T)
                    continue;
                if (!visited[nx]) {
                    if (i == 0 && mustRot[cx][nx]) {
                        visited[nx] = true;
                        queue.offer(new int[]{nx, cd * -1});
                    } else if (i == 1 && mustRot[nx][cx]) {
                        visited[nx] = true;
                        queue.offer(new int[]{nx, cd * -1});
                    }
                }
            }
        }
    }
}
