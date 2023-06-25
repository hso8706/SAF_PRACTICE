package topologysort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN1766_문제집 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 1 ~ N 문제 (난이도 하 -> 상)
    - N개의 문제 모두 해결해야함
    - 문제는 푸는 순서가 존재
    - 여러 경우가 있을 때에는 난이도가 낮은 것 부터 해결

    ### 풀이
    - 인덱스가 난이도
    - [to].add(from) 방식으로 최종으로 푸는 문제가 난이도가 제일 낮은 순으로 정렬되게끔 풀기
    - 인접 차수 인덱스도 변경 [to] => [from]
    - queue 내에서 문제 풀이 순서의 묶음을 나타내는 값이 존재해야함.
     */

    static int N, M;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        inDegree = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            inDegree[to]++;
        }

//        ArrayDeque<Integer>[] results = topologySort();
//        for (int i = 1; i < N+1; i++) {
//            if(results[i].isEmpty()) continue;
//            while(!results[i].isEmpty()){
//                bw.write(results[i].pollFirst()+" ");
//            }
//        }
        topologySort();

        bw.flush();
        bw.close();
    }

    private static void topologySort() throws IOException {
//        ArrayDeque<Integer>[] results = new ArrayDeque[N+1];
//        for (int i = 1; i < N+1; i++) {
//            results[i] = new ArrayDeque<>();
//        }
//        int idx = 1;
//        ArrayDeque<int[]> queue = new ArrayDeque<>();
//        for (int i = 1; i < N+1; i++) {
//            if(inDegree[i]==0) {
//                queue.offer(new int[]{i, idx});
//                results[idx].offerFirst(i);
//                idx++;
//            }
//        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < N+1; i++) {
            if(inDegree[i]==0){
                pq.offer(i);
            }
        }

        while(!pq.isEmpty()){
            int current = pq.poll();
            bw.write(current+" ");

            for (int next: adjList[current]){
                inDegree[next]--;
                if(inDegree[next]==0) {
                    pq.offer(next);
                }
            }
        }

//        while(!queue.isEmpty()){
//            int[] current = queue.poll();
//            int cVertex = current[0];
//            int cIdx = current[1];
//
//            for (int before: adjList[cVertex]){
//                inDegree[before]--;
//                if(inDegree[before]==0) {
//                    queue.offer(new int[]{before, cIdx});
//                    results[cIdx].offerFirst(before);
//                }
//            }
//        }

//        return results;
    }
}
