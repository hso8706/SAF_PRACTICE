package solved;

import java.io.*;
import java.util.*;

public class JUN5430 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### AC
    - AC: 정수 배열은 연산하기 위한 언어
    - R: 배열 순서 뒤집는 함수
    - D: 배열의 첫 번째 수를 버리는 함수, 비어있는 배열일 경우 에러
    
    ### 문제 해결1.
    - R, D 함수를 메서드로 따로 구현
    - 입력대로 실시
    => 시간 초과 + 연속 sort가 안 됨
    
    ### 문제 해결2.
    - 덱 자료 구조 활용 => 배열의 길이를 줄여야만 했음
    
    ### 에러
    1. 배열을 문자열 배열로 받을때 실수
     */
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bf.readLine()); // Test case
        String[] p; // AC Functions
        int n; // intArr 개수
        ArrayDeque<Integer> intArr; // 정수 배열
        boolean direction; // true == 정방향, false == 역방향

        label: for (int i = 0; i < T; i++) {
            p = bf.readLine().split(""); // AC Functions
            n = Integer.parseInt(bf.readLine());
            intArr = new ArrayDeque<>();
            String[] temp = bf.readLine().replace("[", "").replace("]", "").split(",");

            for (int j = 0; j < n; j++) {
                intArr.offer(Integer.parseInt(temp[j]));
            }
            direction = true;

            for (int j = 0; j < p.length; j++) {
                if (p[j].equals("R")) { // 순서 뒤집기
                    if (intArr.size() == 0 && j == p.length-1){
                        bw.write("[]\n");
                        continue label;
                    }
                    direction = !direction;
                } else { // 첫 번째 수 지우기
                    if (intArr.size() == 0) {
                        bw.write("error\n");
                        continue label;
                    } else {
                        if(direction) intArr.pollFirst();
                        else intArr.pollLast(); // 첫 번째 인덱스 제거\
                        if (intArr.size() == 0 && j == p.length-1) {
                            bw.write("[]\n");
                            continue label;
                        }
                    }
                }
            }
            bw.write("[");
            while(!intArr.isEmpty()) {
                int cnt = intArr.size();
                if (cnt == 1) {
                    bw.write(intArr.pollFirst() + "]\n");
                } else {
                    if(direction) bw.write(intArr.pollFirst() + ",");
                    else bw.write(intArr.pollLast() + ",");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
