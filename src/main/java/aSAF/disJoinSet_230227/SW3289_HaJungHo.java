package aSAF.disJoinSet_230227;

import java.io.*;
import java.util.StringTokenizer;

public class SW3289_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T; // Test case
    static int n, m; //n: , m: 연산 개수
    static int[] parents;


    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#" + tc + " ");
            st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            parents = new int[n+1];
            makeSet(); // 초기화
            int[] temp = new int[3];
                    
            for (int i = 1; i < m+1; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < 3; j++) {
                    temp[j] = Integer.parseInt(st.nextToken()); // 3개의 수 입력
                }
                if (temp[0] == 0){
                    union(temp[1], temp[2]);
                }
                else if(temp[0] == 1){
                    int first = find(temp[1]);
                    int second = find(temp[2]);
                    bw.write((first == second ? 1 : 0) + "");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        int aroot = find(a);
        int broot = find(b);

        if (aroot < broot){
            parents[broot] = aroot;
        }
        else parents[aroot] = broot;
    }

    private static int find(int a) {
        if(parents[a] == a){
            return a;
        }

        return find(parents[a]);
    }

    private static void makeSet() {
        for (int i = 1; i < n+1; i++) {
            parents[i] = i;
        }
    }
}
