package aSAF.graph02_230223;

import java.util.Scanner;

// 간선 리스트 표현, EdgeList
// : 시작 정점, 끝 정점의 정보를 객체로 표현하여 리스트에 저장
public class EdgeListTest {

    //간선 정보를 저장하는 클래스
    //이후에 아래 Edge 클래스를 다양하게 활용할 수 있음(예시. 가중치 추가)
    static class Edge {
        int from, to;
//        int from, to, weight;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
        
//        public Edge(int from, int to, int weight) {
//            this.from = from;
//            this.to = to;
//            this.weight = weight;
//        }
        //위처럼 가중치를 입력받고 Comparable 등을 이용해서 가중치 정렬을 활용할 수 있음
        
        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

    static int N; // 정점의 개수
    static Edge[] edgeList; // 간선 정보(Edge)를 저장할 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 정점 수
        int C = sc.nextInt(); // 간선 수

        edgeList = new Edge[C]; // 간선의 수만큼 배열 초기화(간선의 정보를 담는 배열이니까)
        for (int i = 0; i < C; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            edgeList[i] = new Edge(from, to);
        }
        print();
    }

    private static void print() {
        for (Edge edge : edgeList) {
            System.out.println(edge);
        }
    }
}
