package aSAF.graph02_230223;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN1697_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, K; // N: 수빈, K: 동생

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int minTime = 0;
        if(N != K) minTime = howFar();
        bw.write(minTime + "");
        bw.flush();
        bw.close();
    }

    private static int howFar() {
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> queueTime = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];
        int[] move;
        int sec = 0;

        queue.offer(N);
        queueTime.offer(sec);
        visited[N] = true;

        int current = 0;
        int currentTime = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            currentTime = queueTime.poll();
            move = new int[]{-1, 1, current};

            currentTime++;
            for (int i = 0; i < 3; i++) {
                if(current+move[i] < 0 || current+move[i]>=100001) continue;
                if (!visited[current + move[i]]) {
                    if (current + move[i] == K) return currentTime;
                    queue.offer(current + move[i]);
                    queueTime.offer(currentTime);
                    visited[current + move[i]] = true;
                }
            }
        }
        return sec;
    }

    /*
    1. 차이가 크면 순간이동으로 단축, 차이가 적으면; 미세조정이 필요할 땐 걷기 => x
    2. 모든 가능성을 파악, 각 가능성
    => bfs
    => 방문한 곳은 다시 가지 않는다. 헛걸음
    // 참고
    https://propercoding.tistory.com/15
     */

}

