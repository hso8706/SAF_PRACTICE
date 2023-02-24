package aSAF.graph03_230224;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
### 인접 리스트1
- 간선의 정보(현재 정점과 다음 정점 정보)를 리스토 구현
- 
 */
public class DFS_AdjListTest {
    static class Node{
        int vertex; // 값, 정점 번호
        Node link; // 다음 정점 정보

        public Node(int vertex, Node link) {
            super();
            this.vertex = vertex;
            this.link = link;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", link=" + link +
                    '}';
        }
    }

    static Node[] adjList; //head 개수, 다음 정점 정보의 배열
    static int V;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        int E = sc.nextInt();

        adjList = new Node[V]; //head가 모두 null

        int from, to;
        for (int i = 0; i < E; ++i) {
            from = sc.nextInt();
            to = sc.nextInt();
            //무향 그래프
            adjList[from] = new Node(to, adjList[from]); //원래의 첫째를 뒤로 세움, 새로운 노드의 값이 앞쪽으로 끼어든다
            adjList[to] = new Node(from, adjList[to]); //원래의 첫째를 뒤로 세움, 반대 방향
        }

//        bfs(0);
        dfs(0, new boolean[V]);
    }


    private static void dfs(int current, boolean[] visited){ // current : 탐색 정점

        visited[current] = true; // 선 방문체크
        System.out.println((char)(current+65)); // 방문 후 처리할 작업 위치

        // 자신의 인접리스트 확인, 인접 여부는 확인할 필요가 없어짐!
        for (Node temp = adjList[current]; temp != null; temp = temp.link) {//헤드 노드부터 시작, 노드가 null 아니면 순회, link 필드의 값을 데이터 필드 값에 할당
            if(!visited[temp.vertex]){
                dfs(temp.vertex, visited);
            }
        }
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

            for (Node temp = adjList[current]; temp != null; temp = temp.link) {// 인접 행렬과의 차이점
                if(!visited[temp.vertex]){ // 인접 행렬과의 차이점
                    queue.offer(temp.vertex);
                    visited[temp.vertex] = true;
                }
            }
        }
    }
}
