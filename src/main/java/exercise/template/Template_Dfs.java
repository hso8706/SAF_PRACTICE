package exercise.template;

//미완
public class Template_Dfs {
    static int V;
    static int[][] adjMatrix;
    public static void main(String[] args) {
        V = 10; // 정점 수
        adjMatrix = new int[V][V];


        dfs(0, new boolean[V]);
    }

    private static void dfs(int current, boolean[] visited){ // current : 탐색 정점

        visited[current] = true; // 선 방문체크
        System.out.println((char)(current+65)); // 방문 후 처리할 작업 위치

        // 자신의 인접행렬 확인
        for (int i = 0; i < V; i++) {
            if(adjMatrix[current][i] != 0 && !visited[i]){
                dfs(i, visited);
            }
        }
    }

}
