package aSAF.graph01_230222;

import java.util.ArrayList;
import java.util.Scanner;

public class AdjListTest02 {

    static ArrayList<Integer>[] adjList; //head 개수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
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
        print();
    }

    private static void print() {
        for (ArrayList<Integer> list : adjList){
            System.out.println(list);
        }
    }
}
