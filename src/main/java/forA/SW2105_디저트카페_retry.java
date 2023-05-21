package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW2105_디저트카페_retry {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 디저트 투어
    - N * N map
    - 숫자 == 디저트의 종류
    - 대각선 길 존재
        - 한 지점에서 시작하고 사각형 모양을 그리며 원점으로 복귀
        - 직선 혹은 한 점은 사각형으로 여기지 않음.
    - 같은 종류의 디저트는 먹지 않음
        - 사각형 경로 내에 같은 숫자가 포함되면 안 됨
    - 되도록 많은 디저트 + 임의의 한 카페에서 출발 => 완탐
        - 가장 많이 먹는 경로일 때의 디저트 수 출력
        - 먹을 수 없는 경우는 -1
        
    - 가장 많이 먹는 경로 ==> dfs
    - 첫 지점을 정하는 완전 탐색 반복문
    - 사각형을 그리는 방향은 상관없음
        - 직진 및 방향 전환
        - 현재 방향과 다음 방향에 대한 규칙이 필요함
     */

    //Pair 클래스 생성
    static class Pair {
        int x;
        int y;
        int d; // 좌표(x,y)와 현재 진행 방향 d를 필드로 설정

        public Pair(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int T, N;
    static int[][] map;

    //dfs 진행 방향 : 좌하, 우하, 우상, 좌상
    static int[] dx = {1,1,-1,-1};
    static int[] dy = {-1,1,1,-1};
    //사각형 경로(디저트 정보)를 저장하는 list 정보 필요
    static List<Integer> cafes;
    //최대 카페 수를 할당할 cnt 변수 필요
    static int maxValue;

    public static void main(String[] args) throws IOException {
        //T 입력
        T = Integer.parseInt(bf.readLine());
        //반복문 설정(T만큼 순회)
        for (int t = 1; t < T+1; t++) {
            //N 입력
            N = Integer.parseInt(bf.readLine());
            //map 입력
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //maxValue 초기화
            //아무런 디저트 카페를 못 간 경우에 -1을 반환
            maxValue = -1;
            //map 전체를 순회하는 반복문 설정 => 사각형이 만들어지는 최소 범위만 순회
            for (int i = 0; i < N-2; i++) { // 아래에서 두번째
                for (int j = 1; j < N-1; j++) { // 좌우 끝 제거
                    //해당 한 지점(x,y)을 시작점으로 하고 d=0으로 dfs 로직 인자로 제공
                    //종점에 대한 좌표 정보를 인자로 제공
                    //카페 리스트 갱신
                    Pair start = new Pair(i,j,0);
                    int ex = i;
                    int ey = j;
                    cafes = new ArrayList<>();
                    dfs(start, ex, ey);
                }
            }
            System.out.println("#"+t+" "+maxValue);
        }
    }

    //dfs 로직 인자 1. 시작 정보, 2. 종점 정보
    private static void dfs(Pair start, int ex, int ey) {
        // 종료 조건
        if(start.x == ex && start.y == ey && cafes.size() != 0) {
            // 종점으로 돌아오면 종료, + 카페 리스트 길이가 0인 상황 제외
            // 종점으로 돌아온 순간의 list 길이로 디저트 카페 수 갱신
            maxValue = Math.max(maxValue, cafes.size());
            return;
        }

        //반복문 생성 => 직진 혹은 90도 전환만 고려하는 방향 설정
        for (int i = start.d; i <= start.d+1; i++) {
            if(i==4) return;
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];
            // 1. map 외부로 나가면 종료 => 끝 지점에 닿은 경우 방향 전환 후 진행하는 로직으로 대신 구현
            if(nx<0 || ny<0 || nx>=N || ny>=N) continue; // 다음 방향으로 진행
            // 2. 다음 지점의 디저트 종류가 list 에 존재하는지 확인
            if(cafes.contains(map[nx][ny])) continue; // 다음 방향으로 진행
            //있다면 rollback+방향 전환, 없다면 진행
            cafes.add(map[nx][ny]);
            dfs(new Pair(nx,ny,i), ex, ey);
            cafes.remove(cafes.size()-1); // 마지막 제거
        }
    }
}