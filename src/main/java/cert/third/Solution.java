package cert.third;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
    
    ### 풀이3.
    - 위상정렬 공부
    - 위상정렬 사용
    - 김수석 해결
        -
     */
    static int T, N;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            N = Integer.parseInt(bf.readLine());
            for (int i = 1; i < N+1; i++) {
                st = new StringTokenizer(bf.readLine());
                int time = Integer.parseInt(st.nextToken());
                int check = Integer.parseInt(st.nextToken());
                ArrayList<Integer> depN = new ArrayList<>();
                if(check != 0){
                    depN.add(check);
                    while(st.hasMoreTokens()){
                        depN.add(Integer.parseInt(st.nextToken()));
                    }
                }
            }
        }
    }
}
