package exercise.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Graph_AdjustList {

    // 인접 리스트
    // 시작 정점과 도착 정점의 정보가 저장되어 있음.
    // LinkedList 와 개념적으로 유사성을 띈다.
    // Node 클래스를 만들거나 ArrayList[] 로 주로 구현
    // ArrayList 로 된 배열을 쓰는 이유
    // : 2차원 형태의 ArrayList 를 쓰기 위해서

    static ArrayList<Integer>[] adjList; // 인접 리스트 선언
    static int N; // 정점 수
    static int M; // 간선 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        adjList = new ArrayList[N]; // 정점(0 ~ N-1)에 해당하는 ArrayList 배열을 생성, 이 인덱스는 시작 정점(head)을 의마하게 된다.
        for (int i = 0; i < N; i++) { // 선언 시 null 이 할당되기 때문에 빈 ArrayList 로 초기화해주는 과정
            adjList[i] = new ArrayList<>();
        }

        int from, to;
        for (int i = 0; i < M; i++) { // 간선 수, 즉 제공되는 정점의 쌍을 입력받아 저장하는 반복문
            from = sc.nextInt();
            to = sc.nextInt();
            //무향 그래프
            adjList[from].add(to);
            adjList[to].add(from);
            //[head]에서 시작하여, (tail)로 도착하는 정보를 저장
        }

//        bfs(0); // 시작 정점 : 0
        dfs(0, new boolean[N]);
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        queue.offer(start);
        visited[start] = true;

        int current = 0;
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.println(current + 1);

            for (int vertex : adjList[current]){
                if(!visited[vertex]){
                    queue.offer(vertex);
                    visited[vertex] = true;
                }
            }
        }
    }

    private static void dfs(int current, boolean[] visited){
        visited[current] = true;
        System.out.println(current + 1);

        for(int vertex : adjList[current]){
            if(!visited[vertex]){
                dfs(vertex, visited);
            }
        }
    }
}
