package aSAF.DynamicProgramming5_230403;

import java.util.Scanner;

public class FloydTest {

    static int INF = 99999;
    static int N, dist[][];
    static String src = "5\n" +
            "0 4 2 5 0\n" +
            "0 0 1 0 4\n" +
            "1 3 0 1 2\n" +
            "2 0 0 0 2\n" +
            "0 3 3 1 0";

    public static void main(String[] args) {
        Scanner sc = new Scanner(src);
        N = sc.nextInt();//정점의 개수
        dist = new int[N][N];

        //값 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = sc.nextInt();
                if (i != j && dist[i][j] == 0) {
                    //i != j :대각선 값 제외
                    dist[i][j] = INF;
                }
            }
        }
        System.out.println("입력값");
        print();

        //경유지 -> 출발지 -> 도착지 순으로
        for (int k = 0; k < N; k++) { // 경유지
            for (int i = 0; i < N; i++) { // 출발지
                if (i == k) continue;// 경유지랑 출발지가 같은 상태 제외

                for (int j = 0; j < N; j++) { // 도착지
                    if (i == j || k == j) continue;// 경유지랑 목적지랑 같거나, 출발지랑 목적지가 같은 상태 제외
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
            System.out.println("경유지: " + k);
            print();
        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("=================================");
    }
}
