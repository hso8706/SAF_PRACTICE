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

    /*
    ### 위상정렬을 사용한 시간 문제 정리
    - 선언 및 초기화
        - 인접리스트: ArrayList<Integer>[], 배열 인덱스는 from 이나 to 중 하나를 선택, 나는 from 으로 선택, 내용에는 to에 해당하는 값을 넣어줌
        - 인접 차수 배열: int[], vertex 수 만큼(혹은 +1)의 길이를 설정하고 to에 해당하는 인덱스를 ++함. => 해당 vertex(to)로 들어오는 간선의 수를 나타냄
        - 시간 배열: int[], 업무별 시간을 저장할 배열을 설정
        - 클론 차수 배열: 인접 차수 배열을 로직 처리시 변화가 생김, 여러번의 반복을 사용하기 위해 클론 배열 사용
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

            adjList = new ArrayList[N + 1]; //1부터 시작
            inDegree = new int[N + 1]; // 인접 차수 배열
            tempDegree = new int[N + 1]; // 클론 인접 차수 배열
            time = new int[N + 1]; // 업무 시간
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
                if(amount != 0){//
                    for (int j = 0; j < amount; j++) {
                        from = Integer.parseInt(st.nextToken());
                        adjList[from].add(i);//from 인덱스에 접근하여 내부를 순회하며 어떤 to 업무가 있는지 확인할 예쩡
                        inDegree[i]++; //인접 차수 해결, to 인덱스를 주의
                    }
                }
            }

            //김수석 완탐 => 모든 업무를 하나씩 반절 줄여가며 확인
            for (int i = 1; i < N+1; i++) {
                int temp = time[i]; // 백트래킹을 위한 임시 저장
                time[i] = (int)(time[i]/2); // 김수석이 도와주는 업무
                for (int j = 1; j < N+1; j++) tempDegree[j] = inDegree[j]; // clone
                int tempValue = topologySort(); // 로직 처리
                if(tempValue != -1) minValue = Math.min(minValue, tempValue); // 로직 처리 결과에 따른 처리
                time[i] = temp; // 백트래킹
            }
            if(minValue == Integer.MAX_VALUE) bw.write(-1+"\n");
            else bw.write(minValue+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static int topologySort() {
        int[] result = new int[N+1]; // 누적되는 업무 시간을 저장할 배열
        boolean[] isCycle = new boolean[N+1]; // cycle 유무를 확인할 배열
        //TODO cycle 이 있으면 그냥 붕괴해도 되나?

        Queue<Integer> queue = new ArrayDeque<>(); // 인접 차수가 0, 즉 선행 업무가 없는 시작점을 넣을 queue
        for (int i = 1; i < N+1; i++) {
            result[i] = time[i]; // 누적 시간 배열 초기화
            // 인접(진입) 차수가 0이면 해당 vertex 를 queue 에 제공
            if(tempDegree[i] == 0) queue.offer(i);
        }

        //queue 가 빌 때까지 작업
        while(!queue.isEmpty()){
            int current = queue.poll(); // from 값이 꺼내짐
            isCycle[current] = true; // cycle 이 없다면 모든 배열의 요소가 true 로 바뀌게 설정
            for (int next : adjList[current]){ // from 에서 시작되는 to 를 순회
                result[next] = Math.max(result[next], (time[next] + result[current])); // 시간 누적 로직: to(next)인덱스의 값을 확인, 현재 꺼내진 from(current)에 누적된 값과 이후 더해질 next(to)의 값을 더하고 확인
                tempDegree[next]--; //현재 조회된 노드의 다음 노드의 진입 차수 감소(간선 제거)
                if(tempDegree[next] == 0){ //다음 노드 진입 차수를 감소시켰을때, 0이면 queue 넣음
                    queue.offer(next);
                }
            }
        }

        int max = 0;
        boolean flag = false;
        for (int i = 1; i < N+1; i++) {
            if(!isCycle[i]) flag = true; // cycle 확인 배열의 모든 값이 true 인지 확인
            max = Math.max(max,result[i]); // 누적된 시간 배열 중 가장 큰 값 반환
        }

        if(flag) return -1; // cycle 이 있다면 -1 반환
        else return max; // cycle 이 없다면 최대 시간 누적 값 반환
    }
}

