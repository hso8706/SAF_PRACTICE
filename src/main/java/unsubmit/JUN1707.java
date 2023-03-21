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
    
    ### 문제 해결 1.
    - 이분 : 부분 집합을 이용, 공집합과 본인 집합은 제외
    - 탐색 : bfs 혹은 dfs
    => 머리가 복잡해서 포기

    ### 문제 해결 2.인터넷 서칭함
    - 이분
    : 두 가지 색으로 그룹을 구분
    : 시작 지점 색칠, bfs를 통해서 순회하며 인접 지점을 본인과 다른 색으로 색칠
    : 모두 위와 같은 방법으로 칠해지면 이분 그래프, 인접 그래프가 이미 본인과 같은 색으로 칠해져 있으면 이분 그래프 안 됨
     */
    static int K; //K: test case
    static int V, E; //V: 정점의 개수, E: 간선의 개수
    static ArrayList<Integer>[] adjList;
    static String[] division; // 분할 그룹을 표시할 배열, 정점 수 만큼 존재

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            //초기화 파트
            st = new StringTokenizer(bf.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[V + 1]; // idx = 1 부터 사용
            division = new String[V+1];
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
            boolean result = true;
            //bfs 실시
            for (int j = 1; j < V+1; j++) { // 비연결 그래프도 고려
                if(!result) break; // result 갱신을 막기 위해 이분 그래프가 아님이 확인되면 바로 종료
                if(division[j] == null) result = bfs(j);
            }
            if (result) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        division[start] = "g1"; // 시작 그룹: g1

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int vertex : adjList[current]){//현재 정점에 인접 정점 순회
                if(division[current].equals(division[vertex])){
                    return false; // 이분 그래프가 아님
                }
                else{
                    if(division[vertex] != null) continue; // null 아닌데 다른 그룹인 경우는 이미 방문 한 곳
                    if(division[current].equals("g1")) division[vertex] = "g2";
                    else division[vertex] = "g1";
                    queue.offer(vertex);
                }
            }
        }
        return true; // 이분 그래프임
    }
}
