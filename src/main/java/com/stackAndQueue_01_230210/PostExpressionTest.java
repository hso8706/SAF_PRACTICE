package com.stackAndQueue_01_230210;

import java.util.Stack;

//stack을 활용한 후위 표기식 처리
//2+3 => 23+
public class PostExpressionTest {
    public static void main(String[] args) {
        String exp = "6525-*2/+";

        //후위 표기식
        //: 피연산자(숫자)의 경우 스택에 push, 연산자의 경우 스택에서 값을 pop
        //: 연산 결과는 다시 push
        //: 최종 연산 결과 후 pop
        Stack<Integer> st = new Stack<>();
        int size = exp.length(); //문자열 길이
        
        int k = 0;
        for (int i = 0; i < size; i++) {
            char tmp = exp.charAt(i);
            //tmp를 체크
            k = tmp - '0'; // 유니코드를 이용
            // if(Character.isDigit(tmp)) 로 해결해도 됨
            if(k >= 0 && k <= 9){ //k가 숫자라면, stack push
                st.push(k);
            }
            else { //k가 숫자가 아니라면, k(tmp)==연산자
                int val2 = st.pop(); //먼저 꺼낸 숫자를 2번째 숫자 취급
                int val1 = st.pop(); //나중에 꺼낸 숫자를 1번째 숫자 취급
                
                switch (tmp) { //연산 후 stack push
                    case '+':
                        st.push(val1 + val2);
                        break;
                    case '-':
                        st.push(val1 - val2);
                        break;
                    case '*':
                        st.push(val1 * val2);
                        break;
                    case '/':
                        st.push(val1 / val2);
                        break;
                }
            }
        }
        System.out.println(st.pop()); // 최종 연산 결과를 pop 로 확인
    }
}
