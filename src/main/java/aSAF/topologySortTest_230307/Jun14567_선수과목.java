package aSAF.topologySortTest_230307;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jun14567_선수과목 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 인접 리스트 생성
        - adjList[to] = list[from]
    - 진입 차수 행렬
        - isDegree[]
     */
    static int N, M;
    static ArrayList<Integer>[] adjList;
    static int[] isDegree, reDegree, result;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        isDegree = new int[N+1];
        reDegree = new int[N+1];
        result = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[to].add(from);
            isDegree[to]++;
            reDegree[to]++;
        }
        
        tplSort();
        bw.flush();
        bw.close();

    }

    private static void tplSort() throws IOException {

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            if(isDegree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            bw.write((reDegree[cur]+1) + " ");

            //인접 처리
            for(int vertex : adjList[cur]){
                isDegree[vertex]--;
                if(isDegree[vertex] == 0) queue.offer(vertex);
            }
        }
    }
}
