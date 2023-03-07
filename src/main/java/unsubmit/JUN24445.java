package unsubmit;

import java.io.*;
import java.util.*;

public class JUN24445 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static ArrayList<Integer>[] adjList;
    static int[] output;
    static int cnt;
    static int N, M, R;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        output = new int[N+1];
        cnt = 1;
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
        for (int i = 1; i < N+1; i++) {
            Collections.sort(adjList[i], Collections.reverseOrder());
        }

        bfs(R, new boolean[N+1]);
        for(int i = 1; i<N+1; i++){
            bw.write(output[i] + "\n");
        }
        bw.flush();
        bw.close();

    }

    private static void bfs(int start, boolean[] visitied) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visitied[start] = true;

        while (!queue.isEmpty()){
            int current = queue.poll();
            output[current] = cnt;
            cnt++;

            for(int vertex : adjList[current]){
                if(!visitied[vertex]){
                    visitied[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
    }
}
