package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN1091 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    //P[i] 정렬 다시하기, deque 사용
    static int N;
    static int[] P;
    static Deque<Integer>[] PD;
    static int[] S;
    static int[] cards;
    static boolean[] used;
    static int cnt;
    public static void main(String[] args) throws IOException {

        init();
        serving();
        System.out.println(cnt);
    }

    private static void serving() {
        while(true){
            if(isSameOrder()){
                break;
            }
            else {//카드를 줄 수 없는 경우
                //카드 섞기, cnt 증가
                int[] temp = new int[N];
                for (int i = 0; i < N; i++) {
                    temp[S[i]] = cards[i];
                }
                cards = cloneCards(temp);
                cnt++;
            }
            if(cnt>0 && isSame()) {
                cnt = -1;
                break;
            }
        }
    }

    private static boolean isSame() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (cards[i]==i) cnt++;
        }
        return cnt==N;
    }

    private static boolean isSameOrder() {
        int[] order = new int[N];
        int idx = 0;
        for(int i: cards){
            order[i] = idx;
            idx++;
            if(idx>2) idx = 0;
        }
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            if(order[i] != P[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private static int[] cloneCards(int[] temp) {
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    private static void init() throws IOException {
        
        N = Integer.parseInt(bf.readLine());
        P = new int[N];
        PD = new ArrayDeque[3];
        for (int i = 0; i < 3; i++) {
            PD[i] = new ArrayDeque<>();
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            P[i] = idx;
            PD[idx].offerLast(i);
        }
        S = new int[N];
        cards = new int[N];
        used = new boolean[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            cards[i] = i;
        }
        cnt = 0;
    }
}
