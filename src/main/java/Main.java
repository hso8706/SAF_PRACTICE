import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {-1, 0, 1, 0}; // 상하좌우
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 초기값을 -1로 설정하여 아직 계산하지 않음을 표시
            }
        }

        int result = dfs(0, 0);

        System.out.println(result);
    }

    private static int dfs(int r, int c) {
        // 이미 계산한 경우, 저장된 값을 반환
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        // 목표 지점에 도달한 경우
        if (r == M - 1 && c == N - 1) {
            return 1; // 경로 하나 찾음
        }

        // 현재 위치에서 다음 위치로 이동
        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] < map[r][c]) {
                result += dfs(nr, nc);
            }
        }

        // 계산 결과 저장
        dp[r][c] = result;

        return result;
    }
}
