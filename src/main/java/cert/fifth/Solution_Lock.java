package cert.fifth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Lock {
    static int N;
    static int[] scores;
    static int[] comb;
    static boolean[] selected;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#" + tc + " ");

            N = Integer.parseInt(br.readLine());
            comb = new int[N]; // 풍선 터뜨릴 순서의 인덱스를 저장할 배열
            selected = new boolean[N]; // 순열 사용 배열
            scores = new int[N + 2];
            ans = 0;

            // 제일 왼쪽 오른쪽에 1점을 설정 -> 나중에 계산하기 편하려고 이렇게 함
            scores[0] = 1;
            scores[N + 1] = 1;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }

            search(0);

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static void search(int cnt) {
        if (cnt == N) {
            // 점수 계산 시작
            ans = Math.max(ans, getTotalScore());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                comb[cnt] = i;
                search(cnt + 1);
                selected[i] = false;
            }
        }
    }

    // 점수 계산
    private static int getTotalScore() {
        int total = 0;
        boolean[] visited = new boolean[N + 2];

        for (int i = 0; i < N; i++) {
            int index = comb[i] + 1;

            int l = index - 1;
            int r = index + 1;

            // 터트릴 풍선을 기준으로 왼쪽, 오른쪽 탐색
            // 왼쪽에 이미 터진 풍선이 있으면 l 값을 1씩 줄이면서 l이 0이 될 때까지 진행
            while (l > 0 && visited[l]) {
                l--;
            }

            // 오른쪽에 이미 터진 풍선이 있으면 r 값을 1씩 증가
            while (r < N + 1 && visited[r]) {
                r++;
            }

            // 현재 터트린 풍선 방문 처리
            visited[index] = true;

            // l,r 값이 만약 양쪽 끝 값이면 -> 현재 터트리는 풍선 이외의 풍선은 없는거임
            if (l == 0 && r == N + 1) {
                total += scores[index];
            } else {
                // l값이 0이고 r값이 오른쪽 끝값이 아닌 경우 -> 1개의 풍선이 남아있음
                // 위에서 scores[0] = 1로 설정했기 때문에 그냥 곱해버림
                total += scores[l] * scores[r];
            }

        }


        return total;
    }

}
