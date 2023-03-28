package solving;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN2109 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ## 순회 강연
    ### 문제 해결
    - 강연료(p)와 강연 기한(d)를 저장하는 객체를 만든다.
    - 입력으로 주어지는 정보를 객체 형식으로 이를 pq에 저장한다.
    - pq에 저장할 때 정렬 순서=> 1순위: d 오름차순, 2순위: p 내림차순

    + 문제 잘못 이해함.
    - d가 강연 날짜가 아니라 강연 기한임. d일 안으로 하면 됨
    => 강연료로 내림차순 때리고, 같은 금액이면 날짜 오름차순 때리고
    => dayCnt 와 expiration 비교하면서 넘어가는 구간 설정
    => 마감 날짜 수 만큼만 받기
    
    + 같은 마감 기한에 여러 금액 강연
     */
    static int n;// n: 대학 수
//    static int expiration;
    static PriorityQueue<LectureInfo> pq = new PriorityQueue<>();
    static class LectureInfo implements Comparable<LectureInfo>{
        int p, d; // p: 강연료, d: 강연 기한

        public LectureInfo(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(LectureInfo o) {
            if(this.p == o.p){
                return this.d - o.d;
            }
            return o.p - this.p;
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(bf.readLine());
//        expiration = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
//            if(d > expiration) expiration = d; // 기한을 최대 날짜로 갱신
            LectureInfo temp = new LectureInfo(p, d);
            pq.offer(temp);
        }
        bw.write(maxMoney() + "");
        bw.flush();
        bw.close();
    }

    private static int maxMoney() {
        int dayCnt = 0;
        int money = 0;
        while(!pq.isEmpty()){
            LectureInfo temp = pq.poll();
            dayCnt++;
            if(temp.d < dayCnt) continue;
            money += temp.p;
        }
        return money;
    }
}
