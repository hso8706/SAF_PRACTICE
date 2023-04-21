package implementation_and_bruteForce;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class JUN1475_방번호 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 방 번호
    - 0~9 한 세트의 번호
    - 누적된 상태로 다음 번호에 쓸 수 있음
    - 6과 9는 번갈아 쓰기 가능
     */
    static int[] roomNum; // 입력 방번호
    static int[][] numSet; // 번호 세트, 넉넉히 0~7개, 각 인덱스엔 0~9 존재
    static int minSet;
    public static void main(String[] args) throws IOException {
        numSet = new int[8][];
        for (int i = 0; i < 8; i++) {
            numSet[i] = new int[]{0,1,2,3,4,5,6,7,8,6};
        }

        char[] input = bf.readLine().toCharArray();
        roomNum = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int temp = input[i] - '0';
            if(temp == 9) roomNum[i] = 6;
            else roomNum[i] = temp;
        }
        /*
        1. 번호 세트 순회
        2. 사용될 번호 있는지 파악
            2-1. 사용할 번호가 있다면, 사용 처리(-1)하고 continue(혹은 break) + 사용 처리 카운팅
            2-1. 사용할 번호가 없다면, 다음 세트 번호로 값을 올리고 1번 진행
        3. 모든 번호가 완성되면 종료

        + if roomNum == 6 or 9
        - 초기화(+input)에서 해결
         */
        int setIdx = 0;
        int roomNumIdx = 0;
        while(true){
            int flag = 0;
            for (int i = 0; i < 10; i++) {
                if(roomNum[roomNumIdx] == numSet[setIdx][i]){
                    numSet[setIdx][i] = -1;
                    break;
                }
                else flag++;
            }
            if(flag == 10) setIdx++; // 해당 setIdx 에서 사용할 번호가 없는 경우
            else {// roomNumIdx 에 맞는 번호를 세트에서 꺼내어 채운 경우
                roomNumIdx++;
                minSet = Math.max(setIdx+1, minSet); // set 사용 개수==setIdx+1
                setIdx = 0; // setIdx 초기화
            }
            if (roomNumIdx == roomNum.length) break;
        }
        System.out.println(minSet);
    }
}
