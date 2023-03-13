package solved;

import java.io.*;
import java.util.*;

public class JUN1181 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 단어 정렬
    - 길이가 짧은 순
    - 길이가 같으면 사전 순
    + 중복 단어 제거
    
    ### 문제 해결
    - Comparator 사용
     */
    static ArrayList<String> inputArr = new ArrayList<>();
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            String input = bf.readLine();
            if(!inputArr.contains(input)) inputArr.add(input);
        }
        inputArr.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        for(String st : inputArr){
            bw.write(st + "\n");
        }
        bw.flush();
        bw.close();
    }
}
