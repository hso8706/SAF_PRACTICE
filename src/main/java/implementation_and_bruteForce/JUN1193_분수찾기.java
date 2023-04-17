package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1193_분수찾기 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 분수 찾기
    - 배열로 정리 할 수 있을 듯 하다.
        - 계속 실패함... 수학적으로 생각하지말자 계산을 컴퓨터에게 맡기듯이 짜야한다.
     */
    static int X;
    public static void main(String[] args) throws IOException {
        X = Integer.parseInt(bf.readLine());

        int oneRow = 1; // 대각선 기준, 한 줄로 취급되는 값의 개수
        int sumRow = 0; // X-1 번째 분수까지 누적된 oneRow 개수, 사실 필요없음
        int idx = X; // oneRow 내에서 원하는 수가 몇 번째인지 파악하기 위한 인덱스

        while (true) { // 총 반복문 순회 개수를 수학적으로 구하지 않기 위해 무한 반복문 설정
            if(sumRow + oneRow >= X){
                // sumRow + oneRow : X 번째 Row 가 누적되었을때, 누적된 개수에 X가 포함되는지 확인하기 위함
                // ==> (oneRow >= idx) : 이와같이 대체 가능
                if (oneRow % 2 == 1){
                    // oneRow 의 개수가 홀수면 우상향 대각선으로 진행
                    int top = oneRow;
                    int bottom = 1;
                    for (int i = 1; i < idx; i++) {
                        top -= 1;
                        bottom += 1;
                    }
                    bw.write(top + "/" + bottom);
                    break;
                }
                else {
                    // oneRow 의 개수가 짝수면 좌하향 대각선으로 진행
                    int top = 1;
                    int bottom = oneRow;
                    for (int i = 1; i < idx; i++) {
                        top += 1;
                        bottom -= 1;
                    }
                    bw.write(top + "/" + bottom);
                    break;
                }
            }
            else{
                // 현재 oneRow 에 idx 가 포함되지 않은 경우
                // 누적된 sumRow 에 X 가 포함되지 않은 경우
                idx -= oneRow;

                sumRow += oneRow;
                oneRow++;
            }
        }
        bw.flush();
        bw.close();
    }
}
