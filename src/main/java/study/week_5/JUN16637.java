package study.week_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN16637 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    # 조건
    - 수식 길이: 1<= N <=19 (홀수)
    - 수식: 정수(0~9), 부호(+,-,*)
    - 괄호가 없으면 왼쪽부터 순서대로 계산
    - 괄호는 하나의 연산자만 포함
    - 중첩 괄호 불가
    - 괄호 개수 제한 없음
    - 적절한 괄호 추가로 식의 결과가 최대가 되도록 하기
    # 로직
    - 부호 인덱스 저장
    - 부분 집합 선택 사용
        - 인접 인덱스 제외하는 부분 집합
    - 부분 집합 완성 후 order 를 통해 계산 순서 정하기
    - 계산 후 maxValue 갱신
     */
    static int N;
    static String[] expression;
    static int[] index;
    static int len;
    static boolean[] isSelected;
    static int maxValue;
    public static void main(String[] args) throws IOException {

        init();
        findMaxValue();
        output();
    }

    private static void output() throws IOException {
        bw.write(maxValue+"");
        bw.flush();
        bw.close();
    }

    private static void findMaxValue() {
        //0, 1로 시작하는 부분 집합 실행 후 선택된 값으로 max 갱신
        subset(0, 0);
    }

    private static void init() throws IOException {

        N = Integer.parseInt(bf.readLine());
        expression = bf.readLine().split("");
        len = N/2;
        index = new int[len];
        isSelected = new boolean[len];
        for (int i = 0; i < N/2; i++) {
            index[i] = 2*i+1;
        }
        maxValue = Integer.MIN_VALUE;
    }

    private static void subset(int cur, int bef) {
        if(cur >= len){
            Deque<Integer> order = new ArrayDeque<>();
            //index[i]로 선택된 인덱스 우선 순위 설정
            for (int i = 0; i < len; i++) {
                if(isSelected[i]) {
                    order.offer(index[i]);
                }
            }
            //나머지 순서대로 순위 설정
            for (int i = 0; i < len; i++) {
                if(!isSelected[i]) {//index[i]로 선택된 인덱스 부호 확인
                    order.offer(index[i]);
                }
            }
            int diff = 0;//temp 에서 변화된 idx 보정값
            ArrayList<String> temp = new ArrayList<>();//수식 전체를 이용하여 계산과 값의 저장을 보관, 10 이상의 숫자를 위해 String 사용
            for(String s : expression){
                temp.add(s);
            }
            for(int idx : order){
                int value = 0;
                if(idx!=1) idx = (idx-diff) < 1 ? 1 : idx-diff;
                int a = Integer.parseInt(temp.remove(idx-1));
                String s = temp.remove(idx-1);
                int b = Integer.parseInt(temp.remove(idx-1));
                switch (s){
                    case "+":
                        value = (a+b);
                        break;
                    case "-":
                        value = (a-b);
                        break;
                    case "*":
                        value = (a*b);
                        break;
                }
                temp.add(idx-1, String.valueOf(value));
                diff += 2;
            }
            maxValue = Math.max(maxValue, Integer.parseInt(temp.get(0)));
            return;
        }

        isSelected[cur] = true;
        if(!isSelected[bef]) subset(cur+1, cur);
        else subset(cur+2, cur);
        isSelected[cur] = false;
        subset(cur+1, cur+1);
    }
}
