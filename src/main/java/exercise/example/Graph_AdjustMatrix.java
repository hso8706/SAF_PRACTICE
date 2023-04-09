package exercise.example;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Graph_AdjustMatrix {
    // 인접 행렬
    // 행: 시작 정점
    // 열: 도착 정점
    // 0: 간선(연결) 없음, 1: 간선(연결) 있음
    static int[][] adjMatrix; //인접 행렬 선언
    static int N; // 정점 수
    static int M; // 간선 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        adjMatrix = new int[N][N]; // 모두 0으로 초기화 된 행렬
        int from, to;
        for (int i = 0; i <M; i++) {//간선을 채우기 위한 반복문, 간선 수 만큼만 반복
            from = sc.nextInt();
            to = sc.nextInt();

            adjMatrix[from][to] = adjMatrix[to][from] = 1; // 무향 그래프
        }
//        bfs(0); // 시작 정점 : 0
        dfs(0, new boolean[N]);
    }

    private static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        queue.offer(start);
        visited[start] = true;

        int current = 0;
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.println(current + 1);

            for (int i = 0; i < visited.length; i++) {
                if(adjMatrix[current][i] != 0 && !visited[i]){
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
    private static void dfs(int current, boolean[] visited){
        visited[current] = true;
        System.out.println(current + 1);

        for (int i = 0; i < N; i++) {
            if(adjMatrix[current][i] != 0 && !visited[i]) {
                dfs(i, visited);
            }
        }
    }
}
