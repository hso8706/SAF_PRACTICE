package solved;

import java.io.*;
import java.util.*;

public class JUN1325 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // N: 접점, M: 간선
    static ArrayList<Integer>[] adjList;
    static int[] maxArr;
    static int maxVertex;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1]; // 인덱스 1부터 시작
        maxArr = new int[N+1]; // 각 정점에서 시작했을때 가능한 최대 해킹 컴퓨터 수
        maxVertex = Integer.MIN_VALUE;
        Arrays.fill(maxArr, Integer.MIN_VALUE);
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[to].add(from);// 단방향
        }

        for (int i = 1; i < N+1; i++) {
            maxArr[i] = bfs(i, new boolean[N+1]);
            if (maxArr[i] > maxVertex) maxVertex = maxArr[i];
        }

        for (int i = 0; i < N+1; i++) {
            if(maxArr[i] == maxVertex)
                bw.write(i+" ");
        }
        bw.flush();
        bw.close();
    }

    private static int bfs(int start, boolean[] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        int cnt = 0;
        queue.offer(new int[]{start, cnt});
        visited[start] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int nv = current[0];
            visited[nv] = true;
            cnt = current[1];

            for(int vertex: adjList[nv]){
                if(!visited[vertex]){
                    queue.offer(new int[]{vertex, cnt+1});
                }
            }
        }
        return cnt;
    }
}
