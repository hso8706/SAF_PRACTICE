package aSAF.solution01_230307;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN17281_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 야구
    - N : 이닝 수
    - 1 이닝에 3 아웃까지 제한, 3아웃 당하면 해당 이닝 종료 및 공수 전환
    - 각 이닝에 아웃인 사람이 최소 1명은 존재한다.
    - 각 이닝의 최대 점수만
    
    ### 아인타 타순
    - 1 ~ 9 번 선수
    - 1번 선수 => 무조건 4번째 타순

    ### 입력
    1. 이닝 수
    2. 각 이닝에 각 선수가 하는 행동
        - 1: 안타
        - 2: 2루타
        - 3: 3루타
        - 4: 홈런
        - 0: 아웃
    ### 문제 해결
    - 1 이닝 점수 파악
        - 순서 정하기
        - 종료 시점 파악
            - cnt와 반복문
            - cnt가 3의 배수일 때 마다 다른 조건으로 변경
        -
     */
    static int score; // 총 점수
    static int[][] act = new int[51][10]; // 1 ~ 50 이닝마다 1 ~ 9번 선수의 행동
    static int[] base = new int[]{-1, 0, 0 , 0 , 0}; // 0번 인덱스: 빈 곳
    static int[] order = new int[10]; // 0번 인덱스 비움
    public static void main(String[] args) {

    }
}
