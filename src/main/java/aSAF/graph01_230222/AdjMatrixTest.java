package aSAF.graph01_230222;

import java.util.Arrays;
import java.util.Scanner;

public class AdjMatrixTest {
    static int[][] adjMatrix;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(); // 정점수
        int E = sc.nextInt(); // 간선수

        adjMatrix = new int[V][V];

        int from, to;
        for (int i = 0; i < E; ++i) {
            from = sc.nextInt();
            to = sc.nextInt();
            //무향 그래프
            adjMatrix[to][from] = adjMatrix[from][to] = 1;
            //유향 그래프
            adjMatrix[from][to] = 1;
        }
        print();
    }

    private static void print() {
        for (int[] am: adjMatrix){
            System.out.println(Arrays.toString(am));
        }
    }
}
