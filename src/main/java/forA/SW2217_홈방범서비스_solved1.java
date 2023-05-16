package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW2217_홈방범서비스_solved1 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 마름모 꼴 서비스 영역
    - 운영 비용 계산식
    - 시작 지점을 특정할 수 없음 => 어느 지점이 유리한 지 모르기 때문
        - 완탐을 시행해야한다.

    - 완탐
        - 모든 좌표에서 모든 k를 고려
        - 1.map 2차원 배열 순회, 탐색 X
            - 별도의 자료구조에 집 좌표를 관리
        - 2.map 2차원 배열 순회, 탐색 O
    
    - 1.
        - 집(1)에 해당하는 부분 좌표 저장
        - 서비스 영역 지정(k 반복문)
        - map 전체 순회
        - 순회 지점마다 저장된 집 좌표를 호출하여 서비스 영역에 해당되는지 확인
            - 집 좌표 호출
            - map 순회 지점, 즉 (중심점과 집과의 거리의 차이)와 서비스 영역(k)를 비교
     */
    static int T, N, K, M, ans;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());

        for (int t = 1; t <= T; t++) {
            bw.write("#"+t+" ");
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            List<int[]> house = new ArrayList<>(); // 집 리스트

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if(num == 1) house.add(new int[] {i,j});
                }
            }

            int size = house.size();
            ans = 0;
            
            // K 서비스 길이는 1 ~ 얼마까지?
            // K = N % 2 == 0 ? N+1 : N; => 내부 가장 큰 영역에 해당되는 상황을 그리면서 설명해주심
            K = N % 2 == 0 ? N+1 : N;

            for (int k = 1; k <= K; k++) { // 서비스 영역을 미리 만든 후 시작 지점을 달리하며 체크 => x,y 반복문을 먼저 돌려도 무관
                int cost = k*k + (k-1)*(k-1);

                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {

                        int cnt = 0; // 서비스에 포함된 집의 수
                        //모든 집에 대해서 체크
                        for (int i = 0; i < size; i++) { // 서비스에 포함된 집의 수
                            if( (Math.abs( house.get(i)[0] - y) + Math.abs( house.get(i)[1] - x)) < k) cnt++;
                        }

                        //비용과 이익, 집의 수
                        if(cnt * M >= cost) ans = Math.max(ans, cnt);
                    }
                }
            }
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
    }
}