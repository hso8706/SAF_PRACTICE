package Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN2504 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - stack 사용
    - 재귀함수 사용
        - value 전달
        - i 가 인덱스 범위를 넘으면 종료
        - error 처리인 경우 종료
    - 여는 괄호 (, [
        - ( -> ) : 2 반환, end flag(true) 전달
        - [ -> ] : 3 반환, end flag(true) 전달
        - (,[ -> ( : *=2 처리
        - (,[ -> [ : *=3 처리
    - end flag 후,
        - end -> ( : += 2처리, end flag(false) 변경
        - end -> [ : += 3처리, end flag(false) 변경

     */
    static String brackets;
//    static String flag;
    public static void main(String[] args) throws IOException {
        brackets = bf.readLine();

        Deque<String> stack = new ArrayDeque<>();
//        flag = "start";
        System.out.println(recursiveLogic(brackets, 0, 0));

    }

    private static int recursiveLogic(String input, int start, int end) {
        int result = 1;

        for (int i = start; i <= end; i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[') {
                // 여는 괄호일 경우 재귀 호출하여 내부 괄호열의 값을 계산
                int innerValue = recursiveLogic(input, i + 1, end);
                if (innerValue == -1) {
                    return -1; // 잘못된 괄호열인 경우
                }

                result += (ch == '(') ? 2 * innerValue : 3 * innerValue;
                i += innerValue + 1; // 검사한 괄호열은 건너뛰기
            } else if (ch == ')' || ch == ']') {
                // 닫는 괄호일 경우 종료
                return (ch == ')' && input.charAt(start) == '(') || (ch == ']' && input.charAt(start) == '[')
                        ? result
                        : -1; // 괄호 쌍이 맞지 않는 경우
            }
        }

        return result;
    }
}
