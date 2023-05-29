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

public class JUN2056_작업 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static int[] time;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        adjList = new ArrayList[N+1];
        inDegree = new int[N+1];
        time = new int[N+1];

        int amount, to;
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();

            st= new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            amount = Integer.parseInt(st.nextToken());
            if(amount != 0){
                for (int j = 0; j < amount; j++) {
                    to = Integer.parseInt(st.nextToken());
                    adjList[to].add(i);
                    inDegree[i]++;
                }
            }
        }

        System.out.println(topologySort());
    }

    private static int topologySort() {

        int[] result = new int[N+1];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            result[i] = time[i];
            // 인접(진입) 차수가 0이면 해당 vertex(to)를 queue 에 제공
            if(inDegree[i] == 0) queue.offer(i);
        }

        //queue 가 빌 때까지 작업
        while(!queue.isEmpty()){
            int current = queue.poll(); // to의 값이 꺼내짐

            for (int next : adjList[current]){
                result[next] = Math.max(result[next], (time[next] + result[current]));
                inDegree[next]--; //현재 조회된 노드의 다음 노드의 진입 차수
                if(inDegree[next] == 0){ //다음 노드 진입 차수를 1 감소시켰을때, 0이면 queue 넣음
                    queue.offer(next);
                }
            }

        }

        int max = 0;
        for (int i = 1; i < N+1; i++) {
            max = Math.max(max,result[i]);
        }
        return max;
    }
}
