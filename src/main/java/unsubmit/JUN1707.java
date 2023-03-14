package unsubmit;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN1707 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 이분 그래프
    - 정점의 집합을 둘로 분할했을때, 각 집합 내의 정점들이 인접하지 않는 경우가 있으면 이분 그래프
    
    ### 문제 해결
    - 이분 : 부분 집합을 이용, 공집합과 본인 집합은 제외
    - 탐색 : bfs 혹은 dfs
     */
    static int K; //K: test case
    static int V, E; //V: 정점의 개수, E: 간선의 개수
    static ArrayList<Integer>[] adjList;
    static String result;
    // subset
    static int[] total;
    static ArrayList<Integer> groupA, groupB;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            //초기화 파트
            st = new StringTokenizer(bf.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[V + 1]; // idx = 1 부터 사용
            for (int j = 0; j < V + 1; j++) {
                adjList[j] = new ArrayList<>(); // 초기화
            }
            //그래프 생성 파트
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                adjList[to].add(from); // 무향 그래프
            }
            //분할 파트: 부분 집합
            total = new int[V + 1];
            groupA = new ArrayList<>();
            groupB = new ArrayList<>();
            isSelected = new boolean[V + 1];
            for (int j = 1; j < V + 1; j++) {
                total[j] = j; // 접점 수 만큼 초기화
            }
            subset(0, new boolean[V + 1]);
            if(result != null) bw.write(result + "\n");
            else bw.write("NO\n");
        }
        bw.flush();
        bw.close();
    }

    private static void subset(int cnt, boolean[] isSelected) throws IOException {
        if (cnt == V + 1) {
            int ch = 0;
            for (int i = 1; i < V + 1; i++) {
                if (isSelected[i]) {
                    groupA.add(total[i]);
                } else {
                    groupB.add(total[i]);
                    ch++;
                }
            }
            if (ch == V || ch == 0) return; // group 중 하나가 공집합인 경우
            if(bfsS(groupA.get(0), new boolean[V+1]) && bfsB(groupB.get(0), new boolean[V+1])) result = "YES";
            return;
        }

        isSelected[cnt] = true;
        subset(cnt + 1, isSelected);
        isSelected[cnt] = false;
        subset(cnt + 1, isSelected);
    }

    private static boolean bfsB(int start, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        int cnt = 0;

        while(!queue.isEmpty()){
            int current = queue.poll();
            if(!groupB.contains(current)) {
                cnt++;
                continue;
            }
            for(int vertex : adjList[current]){
                if(!visited[vertex]){
                    visited[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
        if(cnt == groupB.size()) return true;
        return false;
    }

    private static boolean bfsS(int start, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        int cnt = 0;

        while(!queue.isEmpty()){
            int current = queue.poll();
            if(!groupA.contains(current)) {
                cnt++;
                continue;
            }
            for(int vertex : adjList[current]){
                if(!visited[vertex]){
                    visited[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
        if(cnt == groupA.size()) return true;
        return false;
    }
}
