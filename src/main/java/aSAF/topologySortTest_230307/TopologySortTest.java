package aSAF.topologySortTest_230307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologySortTest {

    // static 영역
    // Node 클래스를 이용하여 인접 리스트 만들기
    // Node 클래스는 int vertex(다음 노드 번호)와 Node link(이전 노드의 정보)를 필드로 한다.
    static class Node {
        int vertex; // 다음 노트의 번호
        Node link; // 이전 노드의 정보

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }
    }
    static int N,M;
    // Node[] adjList 를 선언
    // 이는 입력을 받을때 from, to를 이용하여 만듬
    static Node[] adjList;
    // 진입차수 관리 배열 선언(int[] inDegree)
    // 입력받을때 inDegree[to]++;
    static int[] inDegree;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N+1]; // 1번부터 시작
        inDegree = new int[N+1]; // adjList 와 같은 길이의 배열로 선언


        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
            inDegree[to]++; // 간선을 받는 대상의 인접 차수 증가
        }

        ArrayList<Integer> list = topologySort();
        if(list.size() == N) {
            //위상 정렬 완성(cycle이 없음)
//            System.out.println(list);
            for (Integer vertex: list){
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
        else {
            //cycle이 존재
            System.out.println("cycle");
        }
    }

    //정렬된 결과를 List로 반환
    static ArrayList<Integer> topologySort(){

        // 반환할 순서를 담을 리스트
        ArrayList<Integer> result = new ArrayList<>();

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            // 인접(진입) 차수가 0이면 해당 vertex(to)를 queue 에 제공
            if(inDegree[i] == 0) queue.offer(i);
        }
        //queue 가 빌 때까지 작업
        while(!queue.isEmpty()){
            int current = queue.poll(); // to의 값이 꺼내짐
            result.add(current);

            //현재 정점 기준으로 인접정점 처리(간선 제거)
            //시작: 현재 vertex 의 노드 정보, 종료: null, 다음: 조회된 노드(temp)에 연결된 노드(link)
            for (Node temp = adjList[current]; temp != null; temp = temp.link){
                inDegree[temp.vertex]--; //현재 조회된 노드의 다음 노드의 진입 차수
                if(inDegree[temp.vertex] == 0){ //다음 노드 진입 차수를 1 감소시켰을때, 0이면 queue 넣음
                    queue.offer(temp.vertex);
                }
            }
        }

        //cycle이 없다면, 모든 정점이 들어있는 list 반환
        //cycle이 있었다면, cycle 때문에 못 들어간 정점을 제외한 list 반환
        return result;
    }
}
