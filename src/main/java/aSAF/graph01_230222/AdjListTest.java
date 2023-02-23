package aSAF.graph01_230222;

import java.util.Scanner;

/*
### 인접 리스트1
- 간선의 정보(현재 정점과 다음 정점 정보)를 리스토 구현
-
 */
public class AdjListTest {
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
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
        print();
    }

    private static void print() {
        for (Node node : adjList){
            System.out.println(node);
        }
    }
}
