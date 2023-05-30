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

public class Solution {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 조건
    - 업무
        - 업무 번호 존재
        - 업무 완료 소요 시간 고정
        - 독립 진행 업무 혹은 종속 진행 업무
        - 한 명이 하나의 업무
        - 가능한 많은 직원 투입 => 독립 업무인 경우 동시 진행 가능
        - 김수석은 단 하나의 업무를 돕고, 해당 업무는 소요시간이 반으로 줄어듬
    - 목표
        - 전체 업무를 완료시키기 위해 필요한 최소 소요시간 구하기

    ### 풀이1
    - 독립 업무는 동시 진행
    - 종속 업무는 한 명이 진행
    - 종속 업무가 순환되는 경우에는 -1 출력
    - T 50, N 50, java 1초 => 따로 풀이법이 존재할 듯 하다
    - 체크 리스트
        - 종속 관계가 순환하는지
        - 김수석이 어떤 업무를 도울지
    - 브랜치를 나누고, 나눠진 브랜치 중 가장 긴 브랜치에서 가장 긴 task 를 김수석이 도우면 좋을듯
        - 브랜치에 따른 저장 공간 => List
    - 업무에 대한 클래스 생성
        - 업무 번호, 종속 번호, 소요시간
    - 해당 업무를 배열(혹은 리스트)로 만들어 브랜치 구현

    ### 풀이2.
    - 브랜치 개념x
         - 종속 관계에 대한 리스트를 따로 구현해서 순서를 먼저 확인
    - 우선 입력을 위한 배열 별도 생성하고, 정렬을 이후에 진행
    
    ### 풀이3. =>위상정렬 해결
    - 위상정렬 공부
    - 위상정렬 사용
    - 김수석 해결
        - 모든 업무에 대해서 줄여보기 => 성공
        - 최종 시간이 가장 긴 라인(?)에 대해서 다루기
     */
    static int T, N;
    static ArrayList<Integer>[] adjList;//인접 리스트
    static int[] inDegree;//인접 차수
    static int[] tempDegree;//김수석 완탐 때 사용할 클론 차수
    static int[] time;//업무별 시간 배열
    static int minValue;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T + 1; t++) {
            bw.write("#" + t + " ");

            N = Integer.parseInt(bf.readLine());

            adjList = new ArrayList[N + 1];
            inDegree = new int[N + 1];
            tempDegree = new int[N + 1];
            time = new int[N + 1];
            minValue = Integer.MAX_VALUE;
            for (int i = 1; i < N+1; i++) {
                adjList[i] = new ArrayList<>(); //빈 리스트 생성
            }

            //시간, 선행 업무 수, 선행 업무 번호
            int tTime, amount, from;
            for (int i = 1; i < N + 1; i++) { // i가 to의 개념
                st = new StringTokenizer(bf.readLine());

                tTime = Integer.parseInt(st.nextToken());
                amount = Integer.parseInt(st.nextToken());
                //시간
                time[i] = tTime;
                //선행 업무
                if(amount != 0){
                    for (int j = 0; j < amount; j++) {
                        from = Integer.parseInt(st.nextToken());
                        adjList[from].add(i);//from 해결
                        inDegree[i]++; //인접 차수 해결
                    }
                }
            }

            //김수석 완탐
            for (int i = 1; i < N+1; i++) {
                int temp = time[i];
                time[i] = (int)(time[i]/2);
                for (int j = 1; j < N+1; j++) tempDegree[j] = inDegree[j];
                int tempValue = topologySort();
                if(tempValue != -1) minValue = Math.min(minValue, tempValue);
                time[i] = temp;
            }
            if(minValue == Integer.MAX_VALUE) bw.write(-1+"\n");
            else bw.write(minValue+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static int topologySort() {
        int[] result = new int[N+1];
        boolean[] isCycle = new boolean[N+1];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            result[i] = time[i];
            // 인접(진입) 차수가 0이면 해당 vertex(to)를 queue 에 제공
            if(tempDegree[i] == 0) queue.offer(i);
        }

        //queue 가 빌 때까지 작업
        while(!queue.isEmpty()){
            int current = queue.poll(); // to의 값이 꺼내짐
            isCycle[current] = true;
            for (int next : adjList[current]){
                result[next] = Math.max(result[next], (time[next] + result[current]));
                tempDegree[next]--; //현재 조회된 노드의 다음 노드의 진입 차수
                if(tempDegree[next] == 0){ //다음 노드 진입 차수를 1 감소시켰을때, 0이면 queue 넣음
                    queue.offer(next);
                }
            }

        }
        //TODO cycle 조건 해결 필요
        int max = 0;
        boolean flag = false;
        for (int i = 1; i < N+1; i++) {
            if(!isCycle[i]) flag = true;
            max = Math.max(max,result[i]);
        }

        if(flag) return -1;
        else return max;
    }
}

