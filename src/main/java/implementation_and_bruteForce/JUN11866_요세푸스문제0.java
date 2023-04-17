package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN11866_요세푸스문제0 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    deque 사용
    1~N까지 순서대로 입력
    앞 제거, 뒤 추가 + 카운팅
    카운팅이 조건에 맞을때마다 다른 덱에 추가
     */

    public static void main(String[] args) throws IOException {
        Deque<Integer> before = new ArrayDeque<>();
        Deque<Integer> after = new ArrayDeque<>();
        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt = 0;

        for (int i = 1; i < N+1; i++) {
            before.offerLast(i);
        }

        while(!before.isEmpty()){
            cnt++;
            if(cnt % K == 0) after.offerLast(before.pollFirst());
            else before.offerLast(before.pollFirst());
        }
        bw.write("<");
        while(!after.isEmpty()){
            if(after.size() == 1){
                bw.write(after.pollFirst()+ "");
            }
            else {
                bw.write(after.pollFirst()+ ", ");
            }
        }
        bw.write(">");
        bw.flush();
        bw.close();
    }
}
