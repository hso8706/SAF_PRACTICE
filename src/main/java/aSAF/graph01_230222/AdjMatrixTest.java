package aSAF.graph01_230222;

import java.util.Arrays;
import java.util.Scanner;

/*
### 인접 행렬
- 2차원 배열로 구현
- 2차원 세로축 : 시작 정점
- 2차원 가로축 : 도착 정점
- `0`과 `1`로 간선을 표현함
- 무향 그래프인 경우, `시작-도착`과 `도착-시작`이 같으므로 대각선을 기준으로 대칭적인 인접 행렬이 됨.
- 유향 그래프인 경우, `시작-도착`과 `도착-시작`이 다르므로 대각선을 기준으로 대칭이 아닐 수 있는 인접 행렬이 됨.
*/
public class AdjMatrixTest {
    static int[][] adjMatrix; // 인접 행렬 선언
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(); // 정점 수
        int E = sc.nextInt(); // 간선 수

        adjMatrix = new int[V][V]; // 인접 행렬 초기화

        int from, to; // 시작 정점, 도착 정점 선언
        for (int i = 0; i < E; ++i) { // 시작 정점, 도착 정점, 간선 초기화
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
