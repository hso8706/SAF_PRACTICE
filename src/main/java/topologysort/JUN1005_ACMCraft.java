package topologysort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN1005_ACMCraft {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### ACM Craft
    - 매 게임 시작 시 건물을 짓는 순서가 정해짐 => 선행되는 작업이 존재 => 위상 정렬 문제
    - 각 건설마다 소요 시간 존재
    - 테케마다 목표 건물이 존재
    - 목표 건물을 짓는데 최소 시간을 출력

    - 목표 건물에서 역순으로 진행해야하므로 to => from 순으로 계산할 것
     */
    static int T, N, K; //T: 테케, N: 건물 수, K: 건설 순서 규칙 수
    static int[] D; // 각 건물마다 걸리는 시간
    static ArrayList<Integer>[] adjList; // 인접 리스트
    static int[] inDegrees; // 인접 차수
    static int W; // 목표 건물 번호
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            //1번줄
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            D = new int[N+1]; // 건물 번호를 1번부터 시작하도록 설정
            adjList = new ArrayList[N+1];
            inDegrees = new int[N+1];
            for (int i = 1; i < N+1; i++) {
                adjList[i] = new ArrayList<>();
            }
            //2번줄: 시간 배열 초기화
            st = new StringTokenizer(bf.readLine());
            for (int i = 1; st.hasMoreTokens(); i++) {
                D[i] = Integer.parseInt(st.nextToken());
            }
            //3번줄: 인접 리스트 및 인접 차수 초기화
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                inDegrees[to]++;
            }
            //4번줄: W
            W = Integer.parseInt(bf.readLine());

            bw.write(topologySort()+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static int topologySort() {
        int[] rTime = new int[N+1]; // 소요 시간 저장 배열

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            rTime[i] = D[i]; // 첫 시작 건물의 소요 시간으로 초기화
            if(inDegrees[i]==0) queue.offer(i);
        }

        while(!queue.isEmpty()){
            int current = queue.poll();

            for(int next : adjList[current]){
                //시간 계산
                rTime[next] = Math.max(rTime[next], (D[next] + rTime[current]));

                //위상 정렬
                inDegrees[next]--; //현재 조회된 노드의 다음 노드의 진입 차수
                if(inDegrees[next] == 0){ //다음 노드 진입 차수를 1 감소시켰을때, 0이면 queue 넣음
                    queue.offer(next);
                }
            }
        }

        //위상 정렬을 통해 소요 시간 배열을 누적 형식으로 갱신하고 W에 해당되는 시간을 반환
        return rTime[W];
    }
}
