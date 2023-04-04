package solved;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN11724 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static int N, M;
    static int cnt;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        visited = new boolean[N+1];
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
        cnt = 0;
        for (int i = 1; i < N+1; i++) {
            if(visited[i]) continue;
            bfs(i);
            cnt++;
        }
        bw.write(cnt+"");
        bw.flush();
        bw.close();
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int vertex : adjList[current]){
                if(!visited[vertex]){
                    visited[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
    }
}
