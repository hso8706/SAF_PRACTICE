//package aSAF.dijkstralTest_230302;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class JUN1753_HaJungHo {
//    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//    static StringTokenizer st;
//
//    /*
//    최단 경로
//    : 시작점에서 다른 모든 정점으로의 최단 경로를 출력
//     */
//    static int V, E; // V: 정점의 개수, E: 간선의 개수
//    static int start; // 시작 정점 번호
//    static int[] vertex; // 간선 번호 배열
//    static ArrayList<Node>[] nodes; // 인접 리스트
//    static int[] distance; // 거리 배열
//    static boolean[] visited; // 방문 여부 배열
//
//
//    public static void main(String[] args) throws IOException {
//        st = new StringTokenizer(bf.readLine());
//        V = Integer.parseInt(st.nextToken());
//        E = Integer.parseInt(st.nextToken());
//        vertex = new int[V+1];
//        visited = new boolean[V+1];
//        distance = new int[V+1];
//        nodes = new ArrayList[E+1];
//        start = Integer.parseInt(bf.readLine());
//        makeSet(); // 모든 배열 초기화
//
//        dijkstra();
//
//    }
//
//    private static void dijkstra() {
//        distance[start] = 0;
//        int min, current;
//
//        for (int i = 1; i < V+1; i++) {
//            current = -1; // 현재 정점, 정점받기 전 초기화
//            min = Integer.MAX_VALUE;
//
//            for (int j = 1; j < V+1; j++) {
//                if(!visited[i] && min > distance[i]){
//                    min = distance[i];
//                    current = i;
//                }
//            }
//            if(current == -1) break;
//            visited[current] = true;
//
//            for (int j = 1; j < V+1; j++) {
//                if(!visited[i])
//            }
//        }
//    }
//
//    static class Node{
//        int from;
//        int to;
//        int weight;
//
//        public Node(int from, int to, int weight) {
//            this.from = from;
//            this.to = to;
//            this.weight = weight;
//        }
//
//        public int getFromTO(int from) {
//            return this.to;
//        }
//        public int getFromWeight(int from) {
//            return this.weight;
//        }
//
//        public int getTo() {
//            return to;
//        }
//
//        public int getWeight() {
//            return weight;
//        }
//    }
//
//    private static void makeSet() throws IOException {
//        for (int i = 0; i < V+1; i++) {
//            vertex[i] = i;
//            distance[i] = Integer.MAX_VALUE;
//            nodes[i] = new ArrayList<>();
//        }
//
//        for (int i = 1; i < E+1; i++) {
//            st = new StringTokenizer(bf.readLine());
//            int from = Integer.parseInt(st.nextToken());
//            int to = Integer.parseInt(st.nextToken());
//            int weight = Integer.parseInt(st.nextToken());
//            nodes[from].add(new Node(from, to, weight));
//        }
//    }
//}
