package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN2292 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 벌집
    - 일정한 규칙을 통해 숫자가 마킹된 벌집
    - 1번 벌집(정중앙 벌집)에서 주어진 숫자의 벌집까지 최소 거리 구하기

    1 | 2,3,4,5,6,7 | 8,9,10,11,12,13,14,15,16,17,18,19 | 20,21,22,23,24,...
    1개 | 6개 | 12개 | ... 6(n-1)개 => 등차수열의 합 : S = n*(첫항+끝항)/2
    1 => 7 (1+6) => 19 (1+6+12) => 37 (1+6+12+18) => 계차 수열
    - 매 끝 지점은 6의 배수만큼 더해지며 증가하므로 이를 기준삼아 나이테 위치 파악하기(해당되는 범위 파악)
    - 나이테 위치(범위)를 파악하면 해당 나이테까지의 거리가 최소 거리
     */
    static int N;
    static ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        int n = 1;
        if(N==1) bw.write(1 + "");
        else {
            out: while(true){
                for (int i = 3*(n-1)*(n-1)+3*(n-1)+2; i <= 3*n*n+3*n+1; i++) {
                    if(N == i) {
                        bw.write(n+1 +""); // 1번 벌집 포함
                        break out;
                    }
                }
                n++;
            }
        }
        bw.flush();
        bw.close();
    }
}
