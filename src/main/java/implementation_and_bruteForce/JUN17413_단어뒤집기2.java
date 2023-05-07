package implementation_and_bruteForce;

import java.io.*;
import java.util.*;

public class JUN17413_단어뒤집기2 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 태그는 단어 취급 안함
    - 단어는 알파벳 대소문자와 숫자로만 이루어짐
    - 공백을 통해 단어 구분
     */
    static char[] string;
    static ArrayList<ArrayList<Character>> stringList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        string = bf.readLine().toCharArray();
        
        findWord();
        bw.flush();
        bw.close();
    }

    private static void findWord() throws IOException {
        boolean tag = false;
        Deque<Character> portion = new ArrayDeque<>();
        for (int i = 0; i < string.length; i++) {
            //태그 체크
            if(string[i] == '<') {
                if(!portion.isEmpty()) { //portion 이 있는 경우 태그 시작 == 단어 체크 지점
                    int endPoint = portion.size();
                    for (int j = 0; j < endPoint; j++) {
                        bw.write(portion.pollFirst());
                    }
                }
                //portion 이 없는 경우 or portion 을 모두 소모한 경우
                tag = true; // 태그 시작점
                bw.write(string[i]);
            }
            else if(string[i] == '>') {
                tag = false; // 태그 종착점
                bw.write(string[i]);
            }
            else if(tag) {
                // 태그가 종료되기 전에는 모두 패스
                bw.write(string[i]);
            }
            //공백 체크, 공백 == 태그 외 공백이 있는 경우 패스
            else if(string[i] == ' ') {
                int endPoint = portion.size();
                for (int j = 0; j < endPoint; j++) {
                    bw.write(portion.pollFirst());
                }
                bw.write(string[i]);
            }
            //이 외 == 단어
            //단어인 경우, portion에 저장
            else {
                portion.offerFirst(string[i]); // 역순으로 정렬되도록 offerFirst
                if(i == string.length-1){ // 입력값의 끝 지점인 경우 종료(출력)
                    int endPoint = portion.size();
                    for (int j = 0; j < endPoint; j++) {
                        bw.write(portion.pollFirst());
                    }
                }
            }

        }
    }
}
