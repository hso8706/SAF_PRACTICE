package aSAF.solution02_230405;

import java.io.*;
import java.util.*;

public class JUN15961_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 회전 초밥
    - 회전판 위에 같은 초밥 있을 가능성 존재
    - 할인 이벤트
        - 정액 할인제
            - 특정 초밥부터 주어진 k 값 만큼 연속으로 먹음 
        - 초밥 무료 쿠폰
            - 쿠폰으로 받은 초밥은 무료로 먹을 수 있음 => 정액 할인제에 포함시키지 않는 것이 유리
    - 목표 : 초밥 가짓수의 최댓값 구하기
        - c를 포함하지 않으면서 k만큼 연속된 접시내의 가짓수가 다양한 경우
        - c는 초밥 벨트위에 없어도 무관
    - 시간 초과 해결
        - 연속으로 뽑은 초밥을 저장하는 배열을 만듬
        - 다음 윈도우로 슬라이딩 할 땐 맨 앞을 지우고 맨 뒤에 더하기
     */
    static int N, d, k, c; //N: 초밥 접시 수, d: 초밥 가짓수, k: 연속으로 먹는 접시 수, c: 쿠폰 번호
    static int[] sushiList;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushiList = new int[N];
        for (int i = 0; i < N; i++) {
            sushiList[i] = Integer.parseInt(bf.readLine());
        }
        maxValue = Integer.MIN_VALUE;
        lookAtTheCase();
        bw.write(maxValue + "");
        bw.flush();
        bw.close();
    }

    private static void lookAtTheCase() {
        Deque<Integer> temp = new ArrayDeque<>();
        int[] cnt = new int[N];
        for (int i = 0; i < N + k-1; i++) {
            boolean isContained = false;
            int cntIdx = i-(k-1);
            if(i < k) {
                if(!temp.contains(sushiList[i])) cnt[0]++;
                temp.offer(sushiList[i]);
            }
            else {
                if(!temp.contains(temp.pollFirst())) cnt[cntIdx] = cnt[cntIdx-1]-1;
                else cnt[cntIdx] = cnt[cntIdx-1];
                int nextIdx = i;
                if (nextIdx >= N) nextIdx -= N;
                if(!temp.contains(sushiList[nextIdx])) cnt[cntIdx]++;
                temp.offerLast(sushiList[nextIdx]);
            }

            if(temp.contains(c)) isContained = true;
            if(i == k-1) cntIdx = 0;
            if(i >= k-1) {
                if (!isContained) maxValue = Math.max(maxValue, cnt[cntIdx] + 1);
                else maxValue = Math.max(maxValue, cnt[cntIdx]);
            }
        }
    }
}
