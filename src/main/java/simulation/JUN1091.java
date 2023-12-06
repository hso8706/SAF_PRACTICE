package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
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
        int cIdx = 0;
        int pIdx = 0;
        int can = 0;
        while(true){
            if(cards[cIdx] == PD[P[pIdx]].peekFirst() && !used[cards[cIdx]]){//카드를 줄 수 있는 경우
                used[cards[cIdx]] = true;
                PD[P[pIdx]].pollFirst();
                pIdx++;
                if(pIdx>=3) pIdx %= 3;
                cIdx++;
                can = 0;
            }
            else {//카드를 줄 수 없는 경우
                //카드 섞기, cnt 증가
                int[] temp = new int[N];
                for (int i = 0; i < N; i++) {
                    temp[i] = S[cards[i]];
                }
                cards = cloneCards(temp);
                cnt++;
                can++;
            }
            if(can >= N) {
                cnt = -1;
                break;
            }
            if(pIdx == N) break;
        }
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
