package solved;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1759 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - C 개 중 L 개의 문자를 사용하여 문자열 구성
    - 3 <= L <= C <= 15
    - 문자열 조건 1 : 최소 한 개의 모음(a, e, i, o, u)
    - 문자열 조건 2 : 최소 두 개의 자음
    - 문자열 조건 3 : 정렬된 문자열(사전 오름차순)
    - 시간 제한 : 2초 => 꽤나 여유
    - 메모리 제한 : 128mb
     */
    static int L, C; // L: 사용할 알파벳 개수, C: 전체 알파벳 개수
    static String[] totalAlpha, selectedAlpha;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        totalAlpha = new String[C];
        selectedAlpha = new String[L];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < C; i++) {
            totalAlpha[i] = st.nextToken();
        }
        Arrays.sort(totalAlpha);

        combi(0, 0);
        bw.flush();
        bw.close();
    }

    private static void combi(int cnt, int start) throws IOException {
        if(cnt == L){
            int cntA =0, cntB = 0;
            for (String st : selectedAlpha) {
                if(st.equals("a") || st.equals("e") || st.equals("i") || st.equals("o") || st.equals("u"))
                    cntA++;
                else
                    cntB++;
            }
            if (cntA >=1 && cntB >= 2) print();
            return;
        }

        for (int i = start; i < C; i++) {
            selectedAlpha[cnt] = totalAlpha[i];
            combi(cnt+1, i+1);
        }
    }

    private static void print() throws IOException {
        for(String st : selectedAlpha){
            bw.write(st + "");
        }
        bw.write("\n");
    }
}
