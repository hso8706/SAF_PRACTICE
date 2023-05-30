package cert.second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 조건
    - N개의 지하철
    - 각 지하철 숫자 : 이용객 수
    - 2개의 직통 노선 건설(규칙 존재)
    - 타당도 검사(식 존재)
    - 직통 노선 조건
        - 2개의 직통 노선은 교차할 수 없음
        - 인접한 두 역을 연결하는 직통 노선은 불가
        - 도착지, 혹은 출발지가 인접한 두 직통 노선도 불가
        - 한 지점을 출발지로, 혹은 도착지로 공유 불가(하나의 역에 2개의 직통 노선 불가)

    ### 출력
    - 타당도가 최대일 때의 값을 출력

    ### 풀이1.
    - 조건을 하나하나 해결하여 구현
    - 4개를 조합으로 뽑음 => 출발, 도착이 바뀌는 경우는 하나로 쳐도 무관
    - 뽑은 역을 배열에 넣고, [0,1]+[2,3] or [3,0]+[1,2]의 경우만 생각
        => 교차를 해결
    - isSelected 사용
        => 순열 뽑기, 인접 문제 해결
        => 인접 문제 좌우 인덱스 처리, 끝 인덱스 주의, 백트래킹 필요
    - 하나의 역에 2개의 노선은 애초에 불가 => 4개를 뽑기 때문
    - 경우의 수가 완성된 순간 타당도 식에 대입 및 max 값 갱신
     */

    static int T, N;
    static int[] tStation;
    static boolean[] isSelected;
    static int[] sStation;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            N = Integer.parseInt(bf.readLine());
            tStation = new int[N];
            isSelected = new boolean[N];
            sStation = new int[4];
            maxValue = Integer.MIN_VALUE;

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                tStation[i] = Integer.parseInt(st.nextToken());
            }

            makeLines(0, 0);
            bw.write(maxValue + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void makeLines(int cnt, int start) {
        //기저 조건 : 4개가 뽑힌 순간, cnt==4
        if(cnt == 4){
            //완성된 경우의 수, 타당도 체크
            maxValue = Math.max(maxValue, (int) (Math.pow((sStation[0] + sStation[1]), 2) + Math.pow((sStation[2] + sStation[3]), 2)));
            maxValue = Math.max(maxValue, (int) (Math.pow((sStation[3] + sStation[0]), 2) + Math.pow((sStation[1] + sStation[2]), 2)));
            return;
        }

        for (int i = start; i < N; i++) {
            if(!isSelected[i]){
                isSelected[i] = true;
                int left = i-1;
                int right = i+1;
                left = left<0 ? N-1: left;
                right = right>N-1 ? 0: right;
                //백트래킹을 위해 left 와 right 의 변화가 있었는지 감지
                boolean isLeft = false;
                boolean isRight = false;
                if(!isSelected[left]){
                    isSelected[left] = true;
                    isLeft = true;
                }
                if(!isSelected[right]){
                    isSelected[right] = true;
                    isRight = true;
                }
                //선택된 배열에 넣기
                sStation[cnt] = tStation[i];
                makeLines(cnt+1, i+1);
                //백트래킹
                isSelected[i] = false;
                if(isLeft) isSelected[left] = false;
                if(isRight) isSelected[right] = false;
            }
        }
    }
}
