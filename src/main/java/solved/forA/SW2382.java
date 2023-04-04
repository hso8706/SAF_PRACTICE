package solved.forA;

import java.io.*;
import java.util.StringTokenizer;

public class SW2382 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 미생물 격리
    - NxN 구역, K개 미생물 군집, 가장자리(약품 구역)는 접근 불가
    - 미생물 군집 정보: 위치, 미생물 수, 이동 방향
    - 이동
        - 1시간마다 이동
        - 약품 구역에 도착하면 미생물 절반 죽고 이동방향 반대로 전환
        - 미생물 수가 홀수인 경우, 2로 나누고 소수점 이하를 버림
        - 미생물 수가 0이 된 경우 군집은 사라진 것으로 간주
        - 두 개 이상의 군집이 만나면 미생물 수가 합쳐짐 => 한 공간에 둘 다 도착해야함
        - 합쳐진 군집의 이동방향은 합쳐지기 전 군집 중 미생물 수가 많은 쪽의 이동방향을 따라감
    - 목표 : M 시간 후 남아있는 미생물 수의 총 합
     */

    public static void main(String[] args) {
    }
}
