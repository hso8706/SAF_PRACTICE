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

    /*
    - N: 2 ~ 3,000,000 // k: 2 ~ 3,000
    - idea1 : 이중으로 반복문을 만들면 시간초과 나오니까 deque 을 사용해서 1차 반복문으로 해결하자
        - 실패 => contains 메서드가 의심된다 => ArrayDeque.contains 가 순회하는 것 같음 => Hash 를 사용해자, Hash 류의 contains 는 시간 복잡도 O(1)
     */

    private static void lookAtTheCase() {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < N + k - 1; i++) {
            boolean isContained = false; // 쿠폰 번호 포함 여부
            int leftIdx = i-k;
            int rightIdx = i; // 원형의 배열을 만드는 로직을 위한 변수
            // 0 ~ k-1 까지 저장
            if (i < k) {
                if (temp.containsKey(sushiList[i])) temp.put(sushiList[i], temp.get(sushiList[i]) + 1);
                else temp.put(sushiList[i], 1);
            }
            // left 제거, right 추가
            else {
                // left 제거 과정
                if (temp.get(sushiList[leftIdx]) - 1 == 0) temp.remove(sushiList[leftIdx]);
                else temp.put(sushiList[leftIdx], temp.get(sushiList[leftIdx]) - 1);

                // right 추가 과정
                if (rightIdx >= N) rightIdx -= N; // 0 ~ N-1 범위를 넘어서는 순간 0 인덱스로 회귀
                if (temp.containsKey(sushiList[rightIdx])) temp.put(sushiList[rightIdx], temp.get(sushiList[rightIdx]) + 1);
                else temp.put(sushiList[rightIdx], 1);
            }

            if (temp.containsKey(c)) isContained = true;
            if (!isContained) maxValue = Math.max(maxValue, temp.keySet().size()+1);
            else maxValue = Math.max(maxValue, temp.keySet().size());
        }
    }
}
