package study.week_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN3107 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    # 기본
    - `:`기준으로 순회 => 총 8개 그룹, `:`은 7개
    - 각 그룹당 4개의 문자로 이루어진 문자열
    # 예외
    - 개별 그룹 문자열이 4개가 아닌 경우 => 앞자리 0이 생략됨
    - 8개 그룹이 안되는 경우 => 연속적으로 0으로만 이루어진 그룹이 생략됨, `::`으로 표기
    # 로직
    - 그룹 개수 카운팅
        - 1번 예외에 해당하는 인덱스를 체크(List<Boolean>)
        - 카운팅 결과가 8이 아니면 체크된 인덱스를 수정함
            - `::`이 체크된 인덱스부터 부족한 개수만큼
    - 1번 예외 카운팅 체크 경우, 4개 문자가 되도록 앞에 `0`추가
    - 2번 예외 경우, 예외 인덱스 범위만큼 `0000` 그룹 추가
     */
    static String input;
    static ArrayList<String> groups;
    static boolean[] error;
    static int index;
//    static ArrayList<Integer> index;
    public static void main(String[] args) throws IOException {

        init();
        findOrigin();
    }

    private static void findOrigin() {
        String temp = "";
        int cnt = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ':' || i == input.length()-1){
                groups.add(temp);
                if(temp.length() < 4) {
                    error[cnt] = true;
                    if(temp.length() == 0) {
                        index = i;
                    }
                }
                cnt++;
            }
            else {
                temp += String.valueOf(input.charAt(i));
            }
        }
    }

    private static void init() throws IOException {
        input = bf.readLine();
        groups = new ArrayList<>();
        error = new boolean[9];
        index = -1;
//        index = new ArrayList<>();
    }
}
