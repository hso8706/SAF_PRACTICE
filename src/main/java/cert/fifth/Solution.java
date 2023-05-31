package cert.fifth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Solution {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] balloon;
    static boolean[] visit;
    static int n;
    static int max;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());

        for (int t = 1; t < T + 1; t++) {
            bw.write("#" + t + " ");

            max = 0;
            n = Integer.parseInt(bf.readLine());
            balloon = new int[n];
            visit = new boolean[n];

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                balloon[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int count, int sum) {
        if (count == n) {
            max = Math.max(max, sum);
            return;
        }
        //visit 를 활용하여 터뜨리는 풍선의 순서(idx)를 정함
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            dfs(count + 1, sum + findScore(i)); // 풍선을 터뜨리며 계산될 점수를 누적으로 넘김
            visit[i] = false;
        }
    }

    private static int findScore(int i) {

        int leftScore = 0;
        for (int j = i - 1; j >= 0; j--) {
            if (!visit[j]) {
                leftScore = balloon[j];
                break;
            }
        }

        int rightScore = 0;
        for (int j = i + 1; j < n; j++) {
            if (!visit[j]) {
                rightScore = balloon[j];
                break;
            }
        }
        if (leftScore == 0 && rightScore == 0) {
            return balloon[i];
        } else if (leftScore == 0) {
            return rightScore;
        } else if (rightScore == 0) {
            return leftScore;
        }

        return leftScore * rightScore;
    }
}
