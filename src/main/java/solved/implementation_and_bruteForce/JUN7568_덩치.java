package solved.implementation_and_bruteForce;

import java.io.*;
import java.util.*;

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
        - PQ 이용 혹은 원리 이용해서 정리된 배열 만들기
     */
    static class PersonInfo{
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
    }
    static int N;
    static PersonInfo[] persons;
    static PersonInfo[] rank;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        persons = new PersonInfo[N+1];
        rank = new PersonInfo[N+1];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            persons[i] = new PersonInfo(w,h);
        }

        /*
        완전 탐색
        - 이중 반복문 사용
        - 완전 탐색이 종료되는 매 시점마다 각각의 사람에게 rank가 매겨진다.
        - 완전 탐색 중 본인보다 덩치가 큰 사람이 나오면 rank가 증가한다(밀린다). 그 외의 경우에는 rank가 유지된다.
         */
        for (int i = 1; i < N+1; i++) {
            int rank = 1;
            for (int j = 1; j < N+1; j++) {
                if(i == j) continue;
                if(persons[i].w < persons[j].w && persons[i].h < persons[j].h) ++rank;
            }
            persons[i].setR(rank);
        }

        for (int i = 1; i < N+1; i++) {
            bw.write(persons[i].r + " ");
        }
        bw.flush();
        bw.close();
    }
}
