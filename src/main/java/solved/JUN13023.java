package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN13023 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    N 명의 참가자, index: 0 ~ n-1
    친구 관계가 존재 여부 파악 문제
    입력 : 사람 수, 친구 관계 수 => 그래프 문제, 인접 리스트

    문제 조건
    1. dfs 사용하여 깊이가 5 이상이 되는 친구 관계 => 1, 아니면 0

    제한 조건
    시간 : 2초
    메모리 : 512mb
    => 무제한

    틀린 이유
    : 기존에 배운 방문체크를 지우지 않고 유지하는 dfs 는 전체가 이어져 있는지 확인하는 용도였음.
    : 지금 문제는 한 붓 그리기마냥 한 번에 5개의 정점을 지날 수 있는지에 대한 여부 문제였다.
    : 때문에 지나간 길로 한 붓 그리기가 실패하면, false 로 방문 체크를 다시 상태 변환하는 것이 필요했음.
     */
    static int N; // 사람의 수(== 정점 수)
    static int M; // 친구 관계의 수(== 간선 수)
    static ArrayList<Integer>[] adjList; // 인접 리스트 선언
    static int result;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N]; // 정점 수 만큼 인접 리스트 생성, 초기화 필요
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        int from, to; // 시작 정점, 종결 정점 선언
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);//무향그래프, 라고 내가 가정함...무향 친구 맞지?ㅠ
        }
        result = 0;
        if(M >= 4) {
            for (int i = 0; i < N; i++) {
                dfs(i, new boolean[N], 0);
                if (result == 1) break;
            }
        }
        bw.write(result + "");
        bw.flush();
        bw.close();


    }

    private static void dfs(int current, boolean[] visited, int depth) throws IOException {
        visited[current] = true;
        if (depth >= 4) {
            result = 1;
            return;
        }

        if (result == 1) return;


        for (int vertex : adjList[current]){
            if (!visited[vertex]){
                dfs(vertex, visited, depth+1);
                visited[vertex] = false;
            }
        }
    }
}
