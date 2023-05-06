package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN2573_빙산 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 바다와 맞닿은 면적 개수만큼 빙산 숫자 감소
    - 빙하가 두 덩어리 이상으로 분리되는 시점에 종료
    
    - 덩어리를 구분할 맵과 빙하의 숫자를 구분할 맵을 설정
        - 덩어리 맵은 한 사이클이 끝났을 떄 확인 및 갱신 필요
     */
    static int N, M;//size
    static int[][] glacierNum;
    static int[][] glacierGroup;
    static int[][] glacierTemp;
    static boolean[][] groupVisited;
    static int years;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean except;
    public static void main(String[] args) throws IOException {
        //초기화
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        glacierNum = new int[N][M];
        glacierGroup = new int[N][M];

        fillNums();
        while(isThereOnlyOneGroup()){
            targetGlacier(); // 빙산만 순회하여 빙산을 지정하는 메서드
            years++;
        }
        if(except) System.out.println(0);
        else System.out.println(years);
    }

    private static void targetGlacier() {
        glacierTemp = new int[N][M]; // 체크 후 동시에 가라앉는 상황을 구현하기 위함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                glacierTemp[i][j] = glacierNum[i][j];
            }
        }
        // 상황 체크는 glacierNum 에서, 변화는 glacierTemp 에서 발생
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(glacierNum[i][j]!=0) sinkingGlacier(new Pair(i,j));
            }
        }

        glacierNum = glacierTemp; // 역으로 복사; 변경된 상황 갱신
    }

    private static void sinkingGlacier(Pair current) {
        //4방 확인 및 인접 바다 개수 체크
        int adjOcean = 0;
        int nx = current.x;
        int ny = current.y;

        for (int i = 0; i < 4; i++) {
            nx = current.x + dx[i];
            ny = current.y + dy[i];
            if(nx<0||ny<0||nx>=N||ny>=M) continue;
            if(glacierNum[nx][ny]==0) adjOcean++;
        }

        //체크된 인접 바다 개수만큼 가라앉는 로직, 변화하는 빙하의 값은 temp 의 값
        glacierTemp[current.x][current.y] -= adjOcean;
        if(glacierTemp[current.x][current.y]<0) glacierTemp[current.x][current.y] = 0;
    }

    private static boolean isThereOnlyOneGroup() {
        int groupCnt = 1;
        groupVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(glacierNum[i][j] != 0 && !groupVisited[i][j]) {
                    markingGroup(new Pair(i,j), groupCnt);
                    groupCnt++;
                }
            }
        }
        if (groupCnt == 1) except = true;

        return groupCnt == 2;
    }

    private static void markingGroup(Pair start, int cnt) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(start);
        groupVisited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            glacierGroup[cx][cy] = cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0||ny<0||nx>=N||ny>=M||glacierNum[nx][ny]==0) continue;
                if(!groupVisited[nx][ny]){
                    groupVisited[nx][ny] = true;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }

    }

    private static void fillNums() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < M; j++) {
                glacierNum[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
