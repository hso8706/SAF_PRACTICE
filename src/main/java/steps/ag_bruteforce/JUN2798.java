package steps.ag_bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2798 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /*
    - 조합을 이용하여 3개 뽑기
    - 뽑은 카드의 총합 구하기
    - M과의 차이(절대값) 구하기 => M을 넘지 않아야함
    - 구한 절대값과 절대값이 가장 작을 때의 총합을 저장
     */
    static int N, M;
    static int[] cards;
    static int[] selected;
    static int sumOfCards;
    static int minValue; // 절대값
    static int maxValue; // 절대값 차이가 최소일 때의 총합
    public static void main(String[] args) throws IOException {

        init();
        selectCards(0, 0);
        System.out.println(maxValue);
    }

    private static void selectCards(int start, int idx) {
        if(idx == 3) { // 3장의 카드를 뽑은 상태
            //카드 총합
            sumOfCards = calculateSum();
            //차이값 구하기
            if(M < sumOfCards) return;
            if(minValue > M-sumOfCards){
                minValue = M-sumOfCards;
                maxValue = sumOfCards;
            }
            return;
        }

        for (int i = start; i < N; i++) {
            selected[idx] = cards[i];
            selectCards(i+1, idx+1);
        }
    }

    private static int calculateSum() {
        int result = 0;
        for(int card : selected){
            result += card;
        }
        return result;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cards = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        selected = new int[3];
        sumOfCards = 0;
        minValue = Integer.MAX_VALUE;
    }
}
