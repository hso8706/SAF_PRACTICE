package aSAF.graph01_230222;

import java.io.*;
import java.util.StringTokenizer;

public class SW1247_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T; // TC 수
    static int N; // client 수
    static int minDistance;
    static int[] indexArr;
    static boolean[] isVisited;
//    static int sumDistance;
    static Coordinate[] coordinates; // 좌표 배열
    // index == 0 : 회사
    // index == 1 : 집
    // 2<= index <= N+2 : client
    static class Coordinate{
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int i = 1; i < T + 1; i++) {
            //초기화 단계 : 입력
            N = Integer.parseInt(bf.readLine());
            coordinates = new Coordinate[N+2];
            st = new StringTokenizer(bf.readLine());
            minDistance = Integer.MAX_VALUE;
            indexArr = new int[N+2];
            indexArr[0] = 0;
            indexArr[N+1] = 1;
            for (int j = 0; j < N+2; j++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                coordinates[j] = new Coordinate(x, y);
            }
            bw.write("#" + i + " ");

            //재귀 함수 호출(방문 여부, 저장된 거리, 인덱스)
            isVisited = new boolean[N+2];
            factorial(1);
//            route(isVisited, 0, 2);//cnt(index) 2부터 시작: 손님만 검색
            bw.write(minDistance + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void factorial(int cnt) throws IOException {
        if(cnt == N+1) {
//            System.out.println(Arrays.toString(indexArr));
            int sumDistance = 0;
//            sumDistance += Math.abs(coordinates[0].x - coordinates[indexArr[2]].x) + Math.abs(coordinates[0].y - coordinates[indexArr[2]].y);
            for (int i = 0; i < N+1; i++) {
                sumDistance += Math.abs(coordinates[indexArr[i]].x - coordinates[indexArr[i+1]].x) + Math.abs(coordinates[indexArr[i]].y - coordinates[indexArr[i+1]].y);
            }
//            sumDistance += Math.abs(coordinates[1].x - coordinates[indexArr[N+1]].x) + Math.abs(coordinates[1].y - coordinates[indexArr[N+1]].y);
            if (minDistance > sumDistance) {
                minDistance = sumDistance;
            }
            return;
        }
        for (int i = 2; i < N+2; i++) {
            if(!isVisited[i]){
                isVisited[i] = true;
                indexArr[cnt] = i;
                factorial(cnt+1);
                isVisited[i] = false;
            }
        }
    }

//    private static void route(boolean[] isVisited, int sumDistance, int cnt) {
//        //조건 1. cnt == N + 2 : 집과의 거리 비교 후 저장
//        //조건 2. sumDistance >= minDistance : 조기 종료
//        if(sumDistance >= minDistance){
//            return;
//        }
//        if(cnt == N+2){
//            //마지막 손님과 집과의 거리 계산
//            sumDistance += Math.abs(coordinates[cnt-1].x - coordinates[1].x) + Math.abs(coordinates[cnt-1].y - coordinates[1].y);
//            if (minDistance > sumDistance) minDistance = sumDistance;
//            return;
//        }
//
//        //isVisited[cnt] 방문여부 확인
//        //1. 방문 체크
//        //2. 거리 체크
//        for (int i = 2; i < N + 2; i++) {
////            if (cnt == 2){ // cnt가 첫 시작일 때 == 첫번째 손님 => 회사랑의 거리
////                sumDistance += Math.abs(coordinates[i].x - coordinates[0].x) + Math.abs(coordinates[i].y - coordinates[0].y);
////
////            }
//            if (!isVisited[cnt]){
//                isVisited[cnt] = true;
//                sumDistance += Math.abs(coordinates[i].x - coordinates[cnt].x) + Math.abs(coordinates[i].y - coordinates[cnt].y);
//                route(isVisited, sumDistance, cnt + 1);
//                sumDistance -= Math.abs(coordinates[i].x - coordinates[cnt].x) + Math.abs(coordinates[i].y - coordinates[cnt].y);
//                isVisited[cnt] = false;
//            }
//        }
//    }
    /*
    1. 모든 집 방문
    - 방문 여부 검사(재귀 사용)
    - 거리 누적 체크
        - 최종 거리 저장(배열 사용)
        - 누적 중 최종 거리보다 길어지면 재귀 중단

    2. 2<= N <=10 => 9!
    - 9!의 인덱스 집합 생성

     */
}
