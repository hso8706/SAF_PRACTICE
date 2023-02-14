package com.recur_01_230206;

// 반복문: Cpu 연산만 사용. 속도나 메모리 면에서 유리
// 재귀 함수: 반복한다는 기능은 똑같음. 코드가 간결하고 가독성이 좋음(순수 재귀에 한해서...)
public class RecursiveTest {
    public static void main(String[] args) {
        // 반복문
        for(int i=0; i<=4; i++){
            System.out.printf("%3d", i);
        }
        System.out.println();

        // 재귀로 바꾼 형태
        // 1. start, end, step 을 입력받아서 반복형태로 출력
        // 함수명 : re()
        re(0, 4, 1);
        re(0, 10, 2);

        // sum 재귀 함수 만들기
        // 2. sum() : start, end 를 입력받아 start 부터 end 까지의 합을 출력
        sum(1, 100);

        // 3. sum2() : start, end 를 입력받아 start 부터 end 까지의 합을 리턴
        System.out.println(sum2(1, 100));

        // 4. getPower(n, m) : n, m 정수 두개를 입력 받아 n의 m 제곱을 리턴
//        System.out.println(getPower(2, 3));

        // 5. getLen(str): 문자 한 개를 입력받아 그 길이를 리턴
        System.out.println(getLen("hello"));
    }

    private static void re(int start, int end, int step) {
        // 1번 조건 혹은 2번 조건이 선택되어 실행되게끔 구현
        // 1. 종료 조건
        if(start > end) {
            System.out.println(""); // 종료 시 줄바꿈
        }
        // 2. 반복 조건(재귀 호출 발생)
        else {
            System.out.printf("%3d", start); // 숫자 한 개 출력
            re(start+step, end, step);
        }
    }

    static int tot = 0;
    private static void sum(int start, int end){
        if (start > end){
            System.out.println(tot);
        }
        else{
            tot += start;
            sum(++start, end);
        }
    }

    private static int sum2(int start, int end) {
        if (start > end){
            return 0;
        }
        return start + sum2(start + 1, end);
    }

//    private static int getPower(int i, int i1) {
//    }
    // 5번
    private static int getLen(String str) {
        if(str.equals("")){
            return 0;
        }
        else {
            return 1 + getLen(str.substring(1));
        }
    }

}
