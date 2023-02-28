package aSAF.MST_230228;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN17471_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] sectionIdx; //구역의 번호를 담을 배열 1 ~ N
    static int[] sectionPop; //구역의 인구수를 담을 배열
    static boolean[] isSelected;
    static PriorityQueue<Integer> pq;

    //    static class Info implements Comparable<Info>{
//        int idx;
//        int value;
//
//        public Info(int idx, int value) {
//            this.idx = idx;
//            this.value = value;
//        }
//
//        @Override
//        public int compareTo(Info o) {
//            return o.value - this.value;
//        }
//    }
    /*
    1. 그래프를 두 그룹으로 나누기
    2. 두 그룹의 인구합 차이가 최소일 때를 구함.
        - 차이가 최소려면 어떤 식의 조합이 짜져야 할까.
        - 두 그룹으로 나눌 때 정확히 이분할 필요는 없다. 한 그룹이 최소 한 개 이상의 구획을 갖으면 된다.

    // 문제 해결
    1. 조합 경우의 수
    2. 뽑힌 경우의 수에서 그룹핑이 되어있는지 확인
    3. 1,2번을 통과하면 Pq에 넣기

    // 그룹 나누기
    - 내림차순으로 인구수 sorting
     */
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        sectionIdx = new int[N + 1];
        sectionPop = new int[N + 1];
        isSelected = new boolean[N + 1];
        pq = new PriorityQueue<>();

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < N + 1; i++) {
            sectionIdx[i] = i;
            sectionPop[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int adj = Integer.parseInt(st.nextToken());
            for (int j = 0; j < adj; j++) {
                int temp = Integer.parseInt(st.nextToken());
                canUnion(i, temp);
            }
        }

        for (int i = 1; i < (N + 1) / 2; i++) {
            combi(1, 1, i);
        }

    }

    private static void combi(int cnt, int start, int end) {
        if (cnt == end) {
            int sum1 = 0; //뽑힌 것
            int sum2 = 0; //안 뽑힌 것
            int pre1 = -1;
            int pre2 = -1;
            for (int i = 1; i < N + 1; i++) {
                if (isSelected[i]) sum1 += sectionPop[i];
                else sum2 += sectionPop[i];

                if (sectionIdx[i] == i) pre1 = i;
                if (sectionIdx[i] == i && i != pre1) pre2 = i;
            }
            return;
        }

        for (int i = start; i < N + 1; i++) {
            isSelected[cnt] = true;
            combi(cnt + 1, i + 1, end);
        }
    }

    private static boolean canUnion(int a, int b) {
        int aroot = find(a);
        int broot = find(b);

        if (aroot == broot)
            return false;
        sectionIdx[broot] = aroot;
        return true;
    }

    private static int find(int a) {
        if (sectionIdx[a] == a)//자기가 대표자
            return a;

//        return find(sectionIdx[a]); // 못찾으면 부모의 대표자 찾기(재귀 호출)
        return sectionIdx[a] = find(sectionIdx[a]);

    }
}
