package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN11723_집합 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 집합
    - 구현
        - find: 전체 순회, 해당 요소 찾기
        - add: find false => 추가
        - remove: find true => 삭제
        - check: find ? 1 : 0
        - toggle: find ? remove : add
        - empty: all delete
        - all: empty => {1,2,...,20}
     */
    static int N;
    static ArrayList<Integer> set;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        set = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            String checkOpt = st.nextToken();
            int x=0;
            if(st.hasMoreTokens()) x = Integer.parseInt(st.nextToken());

            switch (checkOpt){
                case "add" :
                    add(x);
                    break;
                case "remove":
                    remove(x);
                    break;
                case "check":
                    bw.write(check(x)+"\n");
                    break;
                case "toggle":
                    toggle(x);
                    break;
                case "empty":
                    empty();
                    break;
                case "all":
                    all();
                    break;
            }
        }

        bw.flush();
        bw.close();
    }

    private static void all() {
        set = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            set.add(i);
        }
    }

    private static void empty() {
        set.clear();
    }

    private static void toggle(int x) {
        if(find(x)) remove(x);
        else add(x);
    }

    private static int check(int x) {
        return find(x) ? 1 : 0;
    }

    private static void remove(int x) {
        if(find(x)) set.remove(Integer.valueOf(x));
    }

    private static void add(int x) {
        if(!find(x)) set.add(x);
    }

    private static boolean find(int x) {
        for (int i = 0; i < set.size(); i++) {
            if(set.get(i) == x) return true;
        }
        return false;
    }
}
