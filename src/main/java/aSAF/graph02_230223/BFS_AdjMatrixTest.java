package aSAF.graph02_230223;

import java.util.ArrayDeque;
import java.util.Queue;
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
public class BFS_AdjMatrixTest {
    static int[][] adjMatrix; // 인접 행렬 선언
    static int V;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt(); // 정점 수
        int E = sc.nextInt(); // 간선 수

        adjMatrix = new int[V][V]; // 인접 행렬 초기화

        int from, to; // 시작 정점, 도착 정점 선언
        for (int i = 0; i < E; ++i) { // 시작 정점, 도착 정점, 간선 초기화
            from = sc.nextInt();
            to = sc.nextInt();
            //무향 그래프
            adjMatrix[to][from] = adjMatrix[from][to] = 1;
        }
        bfs(0);
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

            for (int i = 0; i < visited.length; i++) {// 인접 행렬의 행을 보는 과정
                if(adjMatrix[current][i] != 0 && !visited[i]){ // 간선이 있는지, 이미 방문이 됐는지 확인
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
