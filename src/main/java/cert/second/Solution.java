package cert.second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 문제
    - N 개의 지하철역
    - 순환 => 원형
    - 각 노드의 숫자 : 하루 이용객 수
    - 직통 노선 : 두 개의 노드를 연결, 규칙 존재, 2개 생성
        - 1. 노선 간 서로 교차 불가
        - 2. 인접한 역끼리는 직통 노선 건설 불가
        - 3. 출발역이 인접하거나, 도착역이 인접한 경우도 건설 불가
        - 4. 1개의 역에 2개의 직통 노선 건설 불가
    - 타당도 식 존재

    ### 출력
    - 타당도가 가장 높을때의 직통 노선을 찾고, 그 때의 타당도를 출력

    ### 풀이1.
    - 지하철 역은 배열을 이용
    - 2쌍, 총 4개의 역 구하기 : 순서 필요없는 경우의 수, 조합
    - 조합 완성 후 조건 체크
    - 조건에 부합하면 max 타당도 갱신
        - 1. 4개 뽑기
            - 하나 뽑고 양쪽 0처리 (맨 끝 인덱스 주의) => true, false로 대체
            - 0이 아닌 숫자 차례로 뽑고 0처리 반복
            - 총 4중 반복문으로 뽑기 => 혹은 재귀
            - [0,1]+[2,3] || [3,0]+[1,2] 조합으로 타당도 구하기
        - 2. 인접 체크 : 맨 처음과 맨 끝 주의
            - 뽑은 4개의 역 중 하나라도 인접한 게 있으면 2,3 조건안에 걸러짐
        - 3. 4개를 뽑을 것이기에 1개에 역에 2개의 직통 노선 불가
     */
    static int T, N;
    static int[] stations;
    static int[] selected;
    static boolean[] visited;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            N = Integer.parseInt(bf.readLine());
            stations = new int[N];
            selected = new int[4];
            visited = new boolean[N];
            maxValue = Integer.MIN_VALUE;

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                stations[i] = Integer.parseInt(st.nextToken());
            }

            //4개 뽑기
            selectStations(0, 0);
            bw.write(maxValue + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void selectStations(int cnt, int start) {
        if(cnt == 4){
//            System.out.println(Arrays.toString(selected));
            maxValue = Math.max(maxValue, (int)(Math.pow((selected[0]+selected[1]), 2) + Math.pow((selected[2]+selected[3]), 2)));
            maxValue = Math.max(maxValue, (int)(Math.pow((selected[0]+selected[3]), 2) + Math.pow((selected[1]+selected[2]), 2)));
            return;
        }

        for (int i = start; i < N; i++) {
            if(!visited[i]) {
                selected[cnt] = stations[i];
                int left = i-1;
                int right = i+1;
                if(left < 0) left = N-1;
                if(right > N-1) right = 0;
                boolean l = false;
                boolean r = false;

                if(!visited[left]){
                    visited[left] = true;
                    l = true;
                }
                if(!visited[right]){
                    visited[right] = true;
                    r = true;
                }
                selectStations(cnt+1, i+1);
                if(l) visited[left] = false;
                if(r) visited[right] = false;
            }
        }
    }
}
