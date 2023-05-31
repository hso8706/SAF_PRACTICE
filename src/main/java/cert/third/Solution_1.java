package cert.third;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 조건
    - 업무 처리에 정해진 소요 시간
    - 업무 번호 존재, 1부터 시작
    - 독립 업무와 선행 업무가 필요한 업무가 존재
    - 많은 수의 직원을 사용하여 독립적인 업무는 따로 처리 가능 => 그냥 위상정렬 외 신경쓰지 말라는 뜻
    - 김수석의 도움 : 하나의 업무의 소요 시간을 반절로 줄여줌. 임의 선택 => 완탐 예정

    ### 출력
    - 전체 업무를 완료하는데 필요한 최소 시간 출력
    - 전체 업무를 완료하지 못하는 경우에는 -1 출력

    ### 풀이
    - 선언부
        - 인접 리스트
        - 인접 차수 배열 + 클론 배열
        - 업무 시간 배열
        - minValue
     */

    static int T, N;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree, tempDegree;
    static int[] time;
    static int minValue;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            N = Integer.parseInt(bf.readLine());
            adjList = new ArrayList[N+1];
            for (int i = 1; i < N+1; i++) {
                adjList[i] = new ArrayList<>();
            }
            inDegree = new int[N+1];
            tempDegree = new int[N+1];
            time = new int[N+1];
            minValue = Integer.MAX_VALUE;

            int taken, amount, to;
            for (int i = 1; i < N+1; i++) {
                st = new StringTokenizer(bf.readLine(), " ");
                taken = Integer.parseInt(st.nextToken());
                amount = Integer.parseInt(st.nextToken());
                time[i] = taken;
                for (int j = 0; j < amount; j++) {
                    to = Integer.parseInt(st.nextToken());
                    adjList[i].add(to);
                    inDegree[to]++;
                }
            }

            //김수석
            for (int i = 1; i < N+1; i++) {
                int temp = time[i];
                time[i] = (int) time[i]/2;
                for (int j = 1; j < N+1; j++) tempDegree[j] = inDegree[j];
                int tempMax = topologySort();
                if(tempMax != -1) minValue = Math.min(minValue, tempMax);
                else {
                    bw.write(tempMax + "\n");
                    break;
                }
                time[i] = temp;
            }
            if(minValue != Integer.MAX_VALUE) bw.write(minValue + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int topologySort() {

        Queue<Integer> queue = new ArrayDeque<>();
        int[] sTime = new int[N+1];
        boolean[] cycles = new boolean[N+1];

        for (int i = 1; i < N+1; i++) {
            sTime[i] = time[i];
            if(tempDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()){
            int current = queue.poll();
            //cycle 체크
            cycles[current] = true;

            for (int next : adjList[current]){
                //시간 로직
                sTime[next] = Math.max(sTime[next], time[next] + sTime[current]);
                //기본 위상정렬 로직
                tempDegree[next]--;
                if(tempDegree[next]==0) queue.offer(next);
            }
        }

        boolean flag = false;
        int max = 0;
        for (int i = 1; i < N+1; i++) {
            if(!cycles[i]) flag = true;
            max = Math.max(max, sTime[i]);
        }

        if(flag) return -1;
        else return max;
    }
}
