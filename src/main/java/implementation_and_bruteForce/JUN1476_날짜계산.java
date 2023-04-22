package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1476_날짜계산 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 날짜 계산
    - 다른 연도 표기법, 3개의 수 사용
    - E: 지구, S: 태양, M: 달
        - 1 ≤ E ≤ 15
        - 1 ≤ S ≤ 28
        - 1 ≤ M ≤ 19
    - 1년 == 1 1 1
        - 1년마다 각 자릿수가 1씩 증가
        - 각 자릿수의 한계를 넘어가면 1로 초기화
     */
    static int E, S, M;
    static int years;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        calYears();
        System.out.println(years);
    }

    private static void calYears() {
        int cE = 1;
        int cS = 1;
        int cM = 1;
        years = 1;

        while(true){
            if(cE == E && cS == S && cM == M) return;
            cE++;
            cS++;
            cM++;
            years++;
            if(cE > 15) cE = 1;
            if(cS > 28) cS = 1;
            if(cM > 19) cM = 1;
        }
    }
}
