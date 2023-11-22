package DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN11725 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    # tree 자료 구조
    - 한 노드에서 시작해서 다른 정점들을 순회하여 자기 자신에게 돌아오는 순환이 없는 연결 그래프
     */
    static int N;
    static ArrayList<Integer>[] tree;
    static int[] parents;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());

        tree = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        parents = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree[n1].add(n2);
            tree[n2].add(n1);
        }
        dfs(1);

        for (int i = 2; i < N+1 ; i++) {
            bw.write(parents[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int parent) {
        visited[parent] = true;
        for(int child : tree[parent]){
            if(!visited[child]){
                parents[child] = parent;
                dfs(child);
            }
        }
    }
}
