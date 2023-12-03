package test.tm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Three {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) {
        // 예시 테스트케이스
        int[][] stats1 = {{10, 7}, {7, 10}, {7, 6}};
        int result1 = solution(stats1);
        System.out.println("Result 1: " + result1); // 2

        int[][] stats2 = {{3, 3}, {1, 1}, {5, 5}, {7, 7}};
        int result2 = solution(stats2);
        System.out.println("Result 2: " + result2); // 4

        int[][] stats3 = {{1, 9}, {6, 6}, {7, 7}, {8, 8}, {8, 8}};
        int result3 = solution(stats3);
        System.out.println("Result 3: " + result3); // 3

        int[][] stats4 = {{2, 10}, {5, 9}, {8, 3}};
        int result4 = solution(stats4);
        System.out.println("Result 4: " + result4); // 1
    }

    public static int solution(int[][] stats) {
        int n = stats.length;
        List<List<Integer>> graph = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && stats[i][0] > stats[j][0] && stats[i][1] > stats[j][1]) {
                    // i가 j를 먹을 수 있는 경우, 그래프에 엣지 추가
                    graph.get(i).add(j);
                }
            }
        }

        // 각 노드에 도달할 수 있는 최대 길이 계산
        int maxChainLength = 1;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int currentLength = dfs(i, graph, dp);
            maxChainLength = Math.max(maxChainLength, currentLength);
        }

        // 어떤 몬스터도 먹이사슬 관계에 있지 않은 경우 1을 반환
        return maxChainLength;
    }

    private static int dfs(int no, List<List<Integer>> graph, int[] dp) {
        if(dp[no] != 0) return dp[no];
        int maxLength = 1;

        for (int neighbor : graph.get(no)) {
            int neighborLength = 1 + dfs(neighbor, graph, dp);
            maxLength = Math.max(maxLength, neighborLength);
        }

        dp[no] = maxLength;
        return maxLength;
    }
}
