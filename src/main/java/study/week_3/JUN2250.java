package study.week_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.Icon;

public class JUN2250 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - dfs 방식으로 설계할 것
    - 2차원으로 입력 받아도 될 듯 함.
     */
    static int N;
    static int[][] trees; //초기 정보
    static int[] cnt; // root 찾기 위한 cnt
    static ArrayList<Integer>[] width; // depth 별 col 정보를 저장하는 리스트
    static boolean[] visited; // 이미 저장한 idx 는 건너뛰기
    static int root; // root idx
    static int ll; // col num
    public static void main(String[] args) throws IOException {

        init();
        dfs(root, 1);
        output();
    }

    private static void output() throws IOException {
        int maxD = 0;
        int maxW = 0;
        for (int i = 1; i < N+1; i++) {
            if(width[i].size()==0) continue;
            int w = Math.abs(width[i].get(0) - width[i].get(width[i].size()-1))+1;
            if(w > maxW){
                maxD = i;
                maxW = w;
            }
        }
        bw.write(maxD + " " + maxW);
        bw.flush();
        bw.close();
    }

    private static void dfs(int idx, int x) {
        if(idx == -1){
            return;
        }

        dfs(trees[idx][0], x+1);
        ll++;
        width[x].add(ll);
        visited[idx] = true;
        dfs(trees[idx][1], x+1);
        if(!visited[idx]) { //이미 col num 이 저장된 idx 는 패스
            ll++;
            width[x].add(ll);
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        trees = new int[N+1][2];
        cnt = new int[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            trees[idx][0] = left;
            trees[idx][1] = right;
            //root 파악
            cnt[idx]++;
            if(left!=-1) cnt[left]++;
            if(right!=-1) cnt[right]++;
        }
        //root 파악
        for (int i = 1; i < N+1; i++) {
            if(cnt[i]!=2) root = i;
        }
        width = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            width[i] = new ArrayList<>();
        }
        ll = 0;
    }
}
