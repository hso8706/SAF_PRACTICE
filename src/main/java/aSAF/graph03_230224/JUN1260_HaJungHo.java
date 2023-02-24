package aSAF.graph03_230224;

import java.io.*;
import java.util.*;

public class JUN1260_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M, V; //N: 정점, M: 간선, V: 시작 정점 번호
//    static class Node{
//        int vertex;
//        Node link;
//
//        public Node(int vertex, Node link) {
//            this.vertex = vertex;
//            this.link = link;
//        }
//    }
//    static Node[] nodes;
    static ArrayList<Integer>[] nodes;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
//        nodes = new Node[N+1];
        nodes = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            nodes[i] = new ArrayList<>();
        }

        int from, to;
        for (int i = 0; i <M; i++) { // 간선 개수만큼 순회
            st = new StringTokenizer(bf.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
//            nodes[from] = new Node(to, nodes[from]);
//            nodes[to] = new Node(from, nodes[to]);
            nodes[from].add(to);
            nodes[to].add(from);
        }
        for (int i = 0; i < N+1; i++) {
            Collections.sort(nodes[i]);
        }

        dfs(V, new boolean[N+1]);
        System.out.println();
        bfs(V);
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        queue.offer(start);
        visited[start] = true;

        int current = 0;
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.print(current + " ");

//            for (Node temp = nodes[current]; temp != null; temp = temp.link){
//                if(!visited[temp.vertex]){
//                    queue.offer(temp.vertex);
//                    visited[temp.vertex] = true;
//                }
//            }
            for (int vertex : nodes[current]) {// 인접 행렬과의 차이점
                if(!visited[vertex]){ // 인접 행렬과의 차이점
                    queue.offer(vertex);
                    visited[vertex] = true;
                }
            }
        }

    }

    private static void dfs(int current, boolean[] visited) {
        visited[current] = true;
        System.out.print(current + " ");

//        for (Node temp = nodes[current]; temp != null; temp = temp.link) {
//            if(!visited[temp.vertex]){
//                dfs(temp.vertex, visited);
//            }
//        }
        for (int vertex : nodes[current]) {//헤드 노드부터 시작, 노드가 null 아니면 순회, link 필드의 값을 데이터 필드 값에 할당
            if(!visited[vertex]){
                dfs(vertex, visited);
            }
        }
    }
}
