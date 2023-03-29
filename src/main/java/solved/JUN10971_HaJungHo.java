package solved;

import java.io.*;
import java.util.StringTokenizer;

public class JUN10971_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 외판원 순회; Traveling Salesman problem (TSP)
    - 1 ~ N 번호가 매겨진 도시들
    - 도시 간 길 존재 => 그래프
    - 어느 한 도시에서 시작하여 N개의 모든 도시를 거쳐서 원래 도시로 돌아오는 모든 경우 중 가장 비용이 적은 경로를 구하는 문제
    - 조건
        - 유향 그래프
        - 모든 비용은 양의 정수
        - 본인 정점으로 들어오는 경로의 비용은 0 => 길이 없다고 생각하자
        - 모든 정점끼리 다 간선이 존재하는 것은 아님
        - 입력에서 매트릭스 형태로 비용값이 제공됨
        
     ### 문제 해결
     1. 순열로 모든 상황을 만들고, 상황마다 해당 접점을 잇는 경로의 비용 총합 구하기 + 최소 비용 갱신
     2. 시작 지점을 순회하며 dfs로 모든 경로 비용 총합 구하기 + 최소 비용 갱신
     */
    static int N; // N: 도시 수
    static int[][] cities; // 도시 간 경로 비용 배열
    static boolean[] visited;
    static int minVal;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        cities = new int[N][N];
        minVal = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true; // 시작 지점 방문
            dfs(i, i, 0); // i: 시작 지점, 0: 합계
        }
        bw.write(minVal + "");
        bw.flush();
        bw.close();

    }
    // 해결 2.
    private static void dfs(int current, int start, int sumVal){
        if(AllVisited()){
            if(cities[current][start] != 0 ) {
                sumVal += cities[current][start];
                if(minVal > sumVal) minVal = sumVal;
            }
            return;
        }

        for (int k = 0; k < N; k++) {
            if(cities[current][k] != 0 && !visited[k]){
                visited[k] = true;
                dfs(k, start, sumVal + cities[current][k]);
                visited[k] = false;
            }
        }
    }

    private static boolean AllVisited() {
        for (int i = 0; i < N; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
}
