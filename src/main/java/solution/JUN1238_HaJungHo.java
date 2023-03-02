package solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN1238_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 파티
    - 문제 이해
        - N 명의 학생수, N 개의 마을 => 1명의 1마을로 간주
        - 각각의 학생이 특장 마을 X에 도달하기 위해서 최소 거리로 진행한다고 가정할 때, 그 중 가장 긴 최소 거리를 출력
    - 체크 조건
        - N: 학생수(마을수) 및 번호, M: 도로(간선)수, X: 도착 지점 마을 번호
        - 단방향 그래프
        - (1 ≤ N ≤ 1,000), (1 ≤ M ≤ 10,000)
    - 문제 해결
        - 최소신장트리(MST)로 해결 예정
     */
    static int N, M, X; // N: 학생수, M: 단방향 도로수, X: 마을 번호

    public static void main(String[] args) {

    }
}
