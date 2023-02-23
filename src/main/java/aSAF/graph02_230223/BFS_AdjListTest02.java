package aSAF.graph02_230223;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_AdjListTest02 {

    static ArrayList<Integer>[] adjList; //head 개수
    static int V;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        int E = sc.nextInt();

        adjList = new ArrayList[V]; //초기화 필요, AdjListTest와 다른 점은 ArrayList 안에 Node가 들어가느냐, arrayList가 들어가느냐의 차이이다.
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }

        int from, to;
        for (int i = 0; i < E; ++i) {
            from = sc.nextInt();
            to = sc.nextInt();
            //무향 그래프
            adjList[from].add(to); // 1차 arrayLIst 를 선택하여, 2차 ArrayList 에 값을 넣음
            adjList[to].add(from);
        }
        bfs(0);
    }
    private static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[V];

        queue.offer(start); // 시작 정점 == start
        visited[start] = true;

        int current = 0;
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.println((char)(current+65)); // 탐색하고 할 일

            for (int vertex : adjList[current]) {// 인접 행렬과의 차이점
                if(!visited[vertex]){ // 인접 행렬과의 차이점
                    queue.offer(vertex);
                    visited[vertex] = true;
                }
            }
        }
    }
}
