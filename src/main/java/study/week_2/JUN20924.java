package study.week_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN20924 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    /*
    - 기가노드: 루트 노트부터 시작했을 경우 처음으로 size>1 인 것
    - root 노드가 줄기 중간임을 대비해서 bfs 로 진행
     */
    static class Node {
        int node;
        int len;

        public Node(int node, int len) {
            this.node = node;
            this.len = len;
        }
    }
    static int N, R, G;
    static ArrayList<Node>[] nodes;
    static boolean[] visited;
    static int rootToGiga, gigaToLeaf;
    static int gigaFlag;

    public static void main(String[] args) throws IOException {

        init();
        findRootToGiga(R);
        findGigaToLeaf(G);
        output();
    }

    private static void findGigaToLeaf(int g) {
        if(G==0) return;
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(g, 0)); // node 번호, 길이
        visited[g] = true;


        while(!deque.isEmpty()){
            Node c = deque.poll();

            if(nodes[c.node].size() == 1){
                gigaToLeaf = Math.max(gigaToLeaf, c.len);
                continue;
            }

            for(Node next : nodes[c.node]){
                if(!visited[next.node]){
                    visited[next.node] = true;
                    deque.offer(new Node(next.node, c.len+next.len));
                }
            }
        }
    }

    private static void findRootToGiga (int r) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(r); // node 번호, 길이
        visited = new boolean[N+1];
        visited[r] = true;

        while(!deque.isEmpty()){
            int cr = deque.poll();
            if(nodes[cr].size() >= 2) {
                int cnt = 0;
                for(Node nr : nodes[cr]){
                    if(!visited[nr.node]) cnt++;
                }
                if(cnt>=2) {
                    G = cr;
                    continue;
                }
            }
            for(Node next : nodes[cr]){
                if(!visited[next.node]){
                    visited[next.node] = true;
                    rootToGiga += next.len;
                    deque.offer(next.node);
                }
            }
        }
    }

    private static void output() throws IOException {
        bw.write(rootToGiga + " " + gigaToLeaf);
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(bf.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            nodes[pre].add(new Node(next, len));
            nodes[next].add(new Node(pre, len));
        }

        rootToGiga = 0;
        gigaToLeaf = 0;
        gigaFlag = 0;
    }
}