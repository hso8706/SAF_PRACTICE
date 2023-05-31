package cert.forth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_Prof {
    //바라보는 방향순서 오0        앞1        왼2        뒤3
    static int[][] dr={{1,0,-1,0},{0,-1,0,1},{-1,0,1,0},{0,1,0,-1}};
    static int[][] dc={{0,1,0,-1},{1,0,-1,0},{0,-1,0,1},{-1,0,1,0}};
    //                  오앞왼뒤   오앞왼뒤   오앞왼뒤   오앞왼뒤
    static int N,M,max;
    static int[][] map,cop;

    static int[][] seedCnt; // 3일후 수확가능
    static List<int[]> seeds; // 씨 뿌린 목록 저장

    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("testcase/test4.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st=null;

        int T=Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st=new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            map=new int[N][N];
            cop=new int[N][N];
            for(int i=0; i<N; i++){
                st=new StringTokenizer(br.readLine()," ");
                for(int j=0; j<N; j++){
                    map[i][j]=Integer.parseInt(st.nextToken());// 0빈 1산
                }
            }
            //for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
            max=Integer.MIN_VALUE;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j]==1) continue; // 1산
                    for(int d=0; d<4; d++){ // 오0 앞1 왼2 뒤3
                        for(int r=0; r<N; r++){
                            for(int c=0; c<N; c++){
                                cop[r][c]=map[r][c];
                            }
                        }
                        start(i,j,d);
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    // 0빈 1산 2싹 3곡식
    static void start(int r, int c, int d) {
        seedCnt=new int[N][N];
        seeds=new ArrayList<>();
        int cnt=0;
        int m=M;
        while(m!=0){
            m--;
            // 3곡식 표시 3일이 지나면
            int size=seeds.size();
            for(int i=size-1; i>=0; i--){
                int[] rc=seeds.remove(i);
                if(rc[2]!=(3+seedCnt[rc[0]][rc[1]])){
                    rc[2]++;
                    if(rc[2]==(3+seedCnt[rc[0]][rc[1]])){
                        cop[rc[0]][rc[1]]=3; // 3곡식
                    }else{
                        seeds.add(rc);
                    }
                }
            }
            //다음 방향 이동가능한
            int nd=-1;
            for(int i=0; i<4; i++){ // 오0 앞1 왼2 뒤3
                int nr=r+dr[d][i];
                int nc=c+dc[d][i];
                if(nr<0||N<=nr || nc<0||N<=nc) continue;
                //오후1: 이동 가능한 곳은 빈 농지, 또는 곡식이 열린 농지이다. 산이거나 싹이 나는 농지인 경우 이동이 불가능하다.
                if(cop[nr][nc]==1 || cop[nr][nc]==2) continue; // 1산 2싹
                //오후2: 만약, 이동 가능한 곳이 여러 개인 경우, 로봇의 오른쪽, 앞쪽, 왼쪽, 뒤쪽의 순서로 가장 먼저인 이동 가능한 곳으로 이동한다.
                if(nd==-1) nd=i;
                else       nd=Math.min(nd,i);
            }
            if(nd==-1){
                //오전3: 현재 농지에 곡식이 열린 경우 수확을 한다. 수확을 하면 농지는 빈 농지가 된다.
                if(cop[r][c]==3){ // 3곡식
                    cnt++;
                    cop[r][c]=0; // 0빈
                }
                //오전2: 현재 농지가 빈 농지이고, 로봇이 다음 농지로 이동할 수 없을 경우 아무것도 하지 않고 현재 위치에서 머무른다.
                //오후3: 만약 이동 가능한 곳이 없는 경우 로봇은 이동하지 않고 현재 위치에 머무른다.
                continue;
            }else{
                //오전1: 현재 농지가 빈 농지이고 로봇이 다음 농지로 이동할 수 있는 경우 씨를 심는다.
                if(cop[r][c]==0){ // 0빈
                    cop[r][c]=2; // 2싹
                    seedCnt[r][c]++;
                    seeds.add(new int[]{r,c,-1});
                    //오전3: 현재 농지에 곡식이 열린 경우 수확을 한다. 수확을 하면 농지는 빈 농지가 된다.
                }else if(cop[r][c]==3){ // 3곡식
                    cnt++;
                    cop[r][c]=0; // 0빈
                }
            }
            r=r+dr[d][nd];
            c=c+dc[d][nd];
            d=convert(d,nd); //항상 앞쪽방향 기준으로 방향결정
        }
        max=Math.max(max,cnt);
    }
    static int convert(int d, int nd) {
        int di=dr[d][nd];
        int dj=dc[d][nd];
        int cd=-1;
        for(int i=0; i<4; i++){
            if(di==dr[1][i] && dj==dc[1][i]){
                cd=i;
                break;
            }
        }
        return cd;
    }
}



