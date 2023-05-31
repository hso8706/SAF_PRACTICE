package cert.third;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Prof {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T, N;
    static int[] times, inDegree, orgInDegree, D;
    static List<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");
            N = Integer.parseInt(bf.readLine());
            times = new int[N+1];
            orgInDegree = new int[N+1];
            inDegree = new int[N+1];
            D = new int[N+1]; // 작업량(누적된 시간)
            adjList = new List[N+1];
            for (int i = 1; i < N+1; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 1; i < N+1; i++) {
                st = new StringTokenizer(bf.readLine(), " ");
                times[i] = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());
                orgInDegree[i] = M;
                for (int j = 0; j < M; j++) {
                    adjList[Integer.parseInt(st.nextToken())].add(i);
                }
            }
            int result = solve();
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solve() {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N+1; i++) {
            int temp = times[i];
            times[i] = times[i]/2;
            int res = getTime();
            if(res == -1) return -1;
            min = Math.min(min, res);
            times[i] = temp;
        }
        return min;
    }

    private static int getTime() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            D[i] = 0;
            inDegree[i] = orgInDegree[i];
            if(inDegree[i] == 0) queue.offer(i);
        }
        int max = 0;
        int cnt = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            cnt++; // 하나 꺼냄
            D[cur] += times[cur];
            max = Math.max(max,D[cur]);
            for (int to : adjList[cur]){
                D[to] = Math.max(D[to], D[cur]);
                if(--inDegree[to]==0) queue.offer(to); // 위상정렬 로직
            }
        }
        return cnt == N? max : -1;
    }
}
