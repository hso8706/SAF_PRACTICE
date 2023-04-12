package solving;

import java.io.*;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN7568_덩치 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 덩치
    - (무게,키)
    - 무게와 키 모두 클 경우 덩치가 크다고 판단
    - 무게와 키 중 하나만 큰 경우 덩치 판단 불가, 덩치 등수 동일
    - 동일 등수가 있었으면 다음 등수는 (이전 등수+1)이 아닌 동일 등수 총 개수 이후의 수가 된다.
        - 1, 2, 2, 2, 5
    - 목표: 입력받은 사람 순서대로 등수를 출력할 것
    - 2<=N<=50
        - 완탐 가능
        - PQ 사용 => 둘 다 양수이면 양수 반환하게?
     */
    static class PersonInfo implements Comparable<PersonInfo>{
        int w; //weight
        int h; //height
        int r; //ranking

        public PersonInfo(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public void setR(int r) {
            this.r = r;
        }

        @Override
        public int compareTo(PersonInfo o) {
            int check = 0;
            if((this.w - o.w) > 0 && (this.h - o.h) > 0) check = -1;
            return check;
        }
    }
    static int N;
    static PersonInfo[] persons;
    static PriorityQueue<PersonInfo> pq;
    static Map<Integer, PersonInfo> rank;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        persons = new PersonInfo[N+1];
        pq = new PriorityQueue<>();

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            pq.offer(new PersonInfo(w,h));
//            persons[i] = new PersonInfo(w,h);
        }
        for (int i = 0; i < pq.size(); i++) {
            PersonInfo temp = pq.poll();
            System.out.println(temp.w+" "+ temp.h);
        }

//        int order = 1;
//        for (int i = 1; i < N+1; i++) {
//            PersonInfo current = pq.poll();
//            if(i==1){
//                rank.put(order, current);
//                order++;
//            }
//            else{
//                if((rank.get(i-1).w>current.w)&&(rank.get(i-1).h> current.h))
//            }
//
//        }
    }
}
