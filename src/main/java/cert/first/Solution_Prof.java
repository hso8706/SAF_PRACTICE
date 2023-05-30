package cert.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_Prof {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T, N, M, min;
    static int[][] map, apples;
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1}; //북, 동, 남, 서

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");
            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            apples = new int[10+1][2]; // 사과 좌표 저장
            M = 0; // 전체 사과 개수

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine()," "); // 이게 더 빠름
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) {
                        apples[map[i][j]][0] = i;
                        apples[map[i][j]][1] = j;
                        M++;
                    }
                }
            }

            min = Integer.MAX_VALUE;
            // 다음 사과 좌표, 진행 방향, 회전 횟수, 다음 사과 번호
            dfs(apples[1][0], apples[1][1], 1, 1, 2); //=>0이 우, 1 증가하면 오른쪽 90회전, 이미 1번 사과를 먹고 1회 회전했다는 가정하에 시작
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int r, int c, int d, int cnt, int nxt) {
        if(cnt>=min) return; // 백트래킹
        if(nxt == M+1){ // 기저조건
            min = Math.min(min, cnt);
            return;
        }

        // 방향과 4분면으로 해결
        if(d==0){ //우0
            if(r<apples[nxt][0]){ // 밑
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 1, cnt+1, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 2, cnt+2, nxt+1);
                }
            }
            else { // 위
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 3, cnt+3, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 3, cnt+3, nxt+1);
                }
            }
        }
        else if(d==1){//하1
            if(r<apples[nxt][0]){ // 밑
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 0, cnt+3, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 2, cnt+1, nxt+1);
                }
            }
            else { // 위
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 0, cnt+3, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 3, cnt+2, nxt+1);
                }
            }
        }
        else if(d==2){ //좌2
            if(r<apples[nxt][0]){ // 밑
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 1, cnt+3, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 1, cnt+3, nxt+1);
                }
            }
            else { // 위
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 0, cnt+2, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 3, cnt+1, nxt+1);
                }
            }
        }
        else if(d==3){ //상3
            if(r<apples[nxt][0]){ // 밑
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 1, cnt+2, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 2, cnt+3, nxt+1);
                }
            }
            else { // 위
                if(c<apples[nxt][1]){// 오른쪽
                    dfs(apples[nxt][0], apples[nxt][1], 0, cnt+1, nxt+1);
                }
                else {//왼쪽
                    dfs(apples[nxt][0], apples[nxt][1], 2, cnt+3, nxt+1);
                }
            }
        }
    }
}