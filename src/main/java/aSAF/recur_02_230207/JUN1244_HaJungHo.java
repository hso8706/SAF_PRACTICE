//package com.recur_02;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class JUN1244_HaJungHo {
//    public static void main(String[] args) {
//        // 스위치 켜고 끄기
//        // 스위치 칸 : switchNum (1부터 시작)
//        // 스위치 켜짐 : 1, 스위치 꺼짐 : 0
//        // 학생 수 : studentsNum, 1 <= studentsNum <= 100
//        // 각 학생에게 자연수 제공(n), 1 <= n <= switchNum
//        // 남자 자연수 사용 (남자: 1)
//            // 본인이 받은 수의 배수인 스위치 상태를 변경
//        // 여자 자연수 사용 (여자: 2)
//            // 본인의 수 기준으로 좌우대칭으로 상태가 같은 스위치 확인
//            // 확인된 스위치들의 범위 중 가장 큰 범위 선택
//            // 좌우대칭이 없다면 본인이 받은 수에 해당하는 스위치만 상태 변환
//            // 범위 내 존재하는 스위치 상태 모두 변환 (=> 홀수개의 스위치 범위)
//
//        Scanner sc = new Scanner(System.in);
//        //입력 1. 스위치 개수
//        int switchNum = sc.nextInt();
//        int[] switchArr = new int[switchNum];
//        //입력 2. 스위치 상태
//        for (int i = 0; i < switchNum; i++) {
//            switchArr[i] = sc.nextInt();
//        }
//        //입력 3. 학생 수
//        int studentsNum = sc.nextInt();
//        int[][] studentsArr = new int[studentsNum][2]; // 전체 길이: 학생 수, 요소 길이: 성별, 자연수
//        //입력 4. 성별 자연수; 학생 수 만큼 제공받음(배열 필요)
//        for (int i = 0; i < studentsNum; i++) {
//            studentsArr[i][0] = sc.nextInt();
//            studentsArr[i][1] = sc.nextInt();
//        }
//
//        onOff(switchArr, studentsArr);
//
//    }
//
//    private static void onOff(int[] switchArr, int[][] studentsArr) {
//        for (int i = 0; i < studentsArr.length; i++) {
//            if (studentsArr[i][0] == 1){
//                for (int j = 1; j <= switchArr.length/studentsArr[i][1]; j++) {
//                    if (switchArr[(j * studentsArr[i][1])-1] == 0) switchArr[(j * studentsArr[i][1])-1] = 1;
//                    else if (switchArr[(j * studentsArr[i][1])-1] == 1) switchArr[(j * studentsArr[i][1])-1] = 0;
//                }
//            }
//            else {
//                if (studentsArr[i][1] == 1) {
//                    if (switchArr[0] == 1) switchArr[0] = 0;
//                    else switchArr[0] = 1;
//                } else if (studentsArr[i][1] == switchArr.length) {
//                    if (switchArr[switchArr.length - 1] == 1) switchArr[switchArr.length - 1] = 0;
//                    else switchArr[switchArr.length - 1] = 1;
//                }
//                else{
//                    while(true){
//                        //좌우대칭 확인할 차례
//                        //범위를 넘어서면 종료
//                        if (studentsArr[i][1])
//                    }
//                }
//            }
//        }
//    }
//
//
//}
