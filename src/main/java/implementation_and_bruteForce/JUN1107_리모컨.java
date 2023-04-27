package implementation_and_bruteForce;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class JUN1107_리모컨 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 일부 버튼 고장
        - 입력으로 제공
    - 리모컨 버튼 : 0 ~ 9, +, -
    - 현재 채널 100
    - 채널 N으로 이동하기 위한 최소 버튼
     */

    static String N; // 목표 채널 번호
    static String[] target; // 목표 채널 번호(배열)
    static int M; // 고장 버튼 개수
    static Map<String, Boolean> remoteCon; // 리모컨
    static int minCnt;

    public static void main(String[] args) throws IOException {
        N = bf.readLine();
        minCnt = 0;
        if(Integer.parseInt(N) == 100) { // 100인 경우 조기 종료
            System.out.println(minCnt);
            System.exit(0);
        }
        target = N.split("");

        remoteCon = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            remoteCon.put(String.valueOf(i), true);
        }

        M = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            remoteCon.put(st.nextToken(), false); // 증복된 키가 있을 경우 덮어쓰기
        }

        for (int i = 0; i < target.length; i++) { // target 순회
            // target[]과 일치하는 키가 사용 가능한지 파악
            if(remoteCon.get(target[i])){ // 사용 가능한 경우
                minCnt++;
            }
            else { // 사용 불가능한 경우

            }

        }
    }
}
