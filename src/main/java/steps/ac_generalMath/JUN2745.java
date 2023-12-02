package steps.ac_generalMath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class JUN2745 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /*
    - 문자를 인덱싱하기 위해 Map 을 사용하여 값을 미리 저장
    - 주어진 진법의 자릿수로 곱하여 10진법 변환
     */
    static Map<Character, Integer> storage;
    static char[] N;
    static int B;
    public static void main(String[] args) throws IOException{
        
        init();

        System.out.println(transform());
    }

    private static int transform() {
        int result = 0;

        for (int i = 0; i < N.length; i++) {
            int exponent = (N.length-1)-i;
            int medium = (int) Math.pow(B, exponent);
            result += (storage.get(N[i]) * medium);
        }

        return result;
    }

    private static void init() throws IOException {
        //storage
        storage = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            storage.put((char)(i+'0'), i);
        }
        for (int i = 65; i <= 90; i++) {
            storage.put((char)(i), i-55);
        }
        //N, B
        st = new StringTokenizer(bf.readLine());
        N = st.nextToken().toCharArray();
        B = Integer.parseInt(st.nextToken());
    }
}
