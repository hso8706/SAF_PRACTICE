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
    public static void main(String[] args) throws IOException {

        init();
        findOrigin();
        output();
    }

    private static void output() throws IOException {
        for (int i = 0; i < groups.size(); i++) {
            if(i== groups.size()-1) bw.write(groups.get(i));
            else bw.write(groups.get(i)+":");
        }
        bw.flush();
        bw.close();
    }

    private static void findOrigin() {
        String temp = "";
        int cnt = 0;
        int splitCnt = 0;
        for (int i = 0; i <= input.length(); i++) {
            if(i == input.length() || input.charAt(i) == ':'){
                splitCnt++;
                if(temp.length() < 4) {
                    error[cnt] = true;
                    int diff = temp.length();
                    if(diff == 0) {
                        index = cnt;
                    }
                    if(splitCnt==2) continue;
                    for (int j = 0; j < 4 - diff; j++) {
                        temp = "0"+temp;
                    }
                }
                groups.add(temp);
                cnt++;
                temp = "";
            }
            else {
                splitCnt = 0;
                temp += String.valueOf(input.charAt(i));
            }
        }
        if(cnt!=8){
            for (int i = 0; i < 8-cnt; i++) {
                groups.add(index, "0000");
                index++;
            }
        }
    }

    private static void init() throws IOException {
        input = bf.readLine();
        groups = new ArrayList<>();
        error = new boolean[9];
        index = -1;
    }
}
