package steps.ac_generalMath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class JUN11005 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /*
    - 문자를 인덱싱하기 위해 Map 을 사용하여 값을 미리 저장
    - 주어진 진법의 자릿수로 곱하여 10진법 변환
     */
    static Map<Integer, Character> storage;
    static int N;
    static int B;
    static ArrayList<Character> result;
    public static void main(String[] args) throws IOException{
        
        init();

        transform(N);

        output();
    }

    private static void output() throws IOException {
        for(char c : result){
            bw.write(c+"");
        }
        bw.flush();
        bw.close();
    }

    private static void transform(int N) {
        if(N < B){
            result.add(storage.get(N));
            return;
        }

        int quotient = N/B;
        int remainder = N%B;
        transform(quotient);
        result.add(storage.get(remainder));
    }

    private static void init() throws IOException {
        //storage
        storage = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            storage.put(i, (char)(i+'0'));
        }
        for (int i = 65; i <= 90; i++) {
            storage.put(i-55, (char)(i));
        }
        //N, B
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        //result
        result = new ArrayList<>();
    }
}
