package cert.fifth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_bef {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;


    static int T, N;
    static int[] balloons;
    //	static ArrayList<Integer> temp;
    static int[] temp;
    //����
    static boolean[] isSelected;//isSelected �ʿ�
    static int[] shoot;//���� ���� ���� �ʿ�
    //��ȯ
    static int maxP;
    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(bf.readLine());

        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");

            N = Integer.parseInt(bf.readLine());
            balloons = new int[N+1];
            st = new StringTokenizer(bf.readLine());
            balloons[0] = -1;
            for (int i = 1; i < N+1; i++) {
                balloons[i] = Integer.parseInt(st.nextToken());
            }
            maxP = Integer.MIN_VALUE;
            //�Է� �Ϸ�

            //N���� ǳ���� N���� �Ѿ� => �ִ� ������ ��� ���ؼ� �������� ǳ���� �����ؾ��Ѵ�.
            //������ ���ؼ� ������ ���ϰ� ����
            isSelected = new boolean[N+1];
            shoot = new int[N+1];
            temp = new int[N+1];
            permutation(1);
            bw.write(maxP + "\n");
        }
        bw.flush();
        bw.close();

    }
    private static void permutation(int cnt) {
        if(cnt == N+1) {
            //�ϳ��� ����� ���� �ϼ��� ����
            //ǳ�� ���� ���� ����
            int sumP = game();
            maxP = Math.max(maxP, sumP);
            return;
        }

        for (int i = 1; i < N+1; i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                shoot[cnt] = i;
                permutation(cnt+1);
                isSelected[i] = false;
            }
        }
    }
    private static int game() {
        for (int i = 0; i < balloons.length; i++) {
            temp[i] = balloons[i];
        }
        int sum = 0;
        for (int i = 1; i < shoot.length; i++) {
            int targetIdx = shoot[i]; // Ÿ�� ǳ���� ��ȣ
            int leftIdx = targetIdx -1;
            int rightIdx = targetIdx +1;
            while(true) {
                if(rightIdx == temp.length) break;
                if(temp[rightIdx] != 0) {
                    break;
                }
                rightIdx++;
            }
            while(true) {
                if(temp[leftIdx] != 0) {
                    break;
                }
                leftIdx--;
            }
            //Ÿ�� ǳ���� ���� ���� ���� �� Ÿ�� ǳ�� ����
            //ǳ���� �ϳ��� ��� => ������ -1 && �������� range ��
            if(temp[leftIdx]==-1 && rightIdx==temp.length) {
                //target ǳ�� ���� �״�� ȹ��
                sum += temp[targetIdx];
            }
            //Ÿ�� �� �ʿ��� ǳ���� �ִ� ��� => ������ -1 || �������� range ��
            else if(temp[leftIdx]==-1) {
                //target ǳ�� ������ ���� ȹ��
                sum += temp[rightIdx];
            }
            else if(rightIdx==temp.length) {
                //target ǳ�� ���� ���� ȹ��
                sum += temp[leftIdx];
            }
            //Ÿ�� �� ���� ǳ���� �ִ� ��� => else
            else {
                //target ǳ�� �� �� ���� ������ ȹ��
                sum += (temp[leftIdx] * temp[rightIdx]);
            }
            temp[targetIdx] = 0;
        }

        return sum;
    }

}