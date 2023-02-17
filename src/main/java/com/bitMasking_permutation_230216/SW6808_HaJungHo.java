package com.bitMasking_permutation_230216;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//18장의 카드를 9장씩 나눈다
//9라운드를 진행, 한 라운드당 1장씩 제출
//높은 수가 이김. 이긴 사람은 두 카드 숫자의 합을 점수로 획득
//낮은 수가 짐. 진 사람은 아무것도 없음
//최종 점수가 큰 사람이 이김, 같으면 무승부
//규영이 고정, 인영이 9!
//규영이가 이길 경우, 질 경우 구하기
public class SW6808_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T, totalWinScore, totalLoseScore, winTimes, loseTimes;
    static Integer[] hersCards, hisCards;
    static Integer[] hersSelectedCards;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#" + tc + " ");
            st = new StringTokenizer(bf.readLine());
            hisCards = new Integer[9];
            for (int i = 0; i < 9; i++) {
                hisCards[i] = Integer.parseInt(st.nextToken());
            }
            hersCards = new Integer[9];
            hersSelectedCards = new Integer[9];
            isSelected = new boolean[9];
            winTimes = 0;
            loseTimes = 0;
            int idx = 0;
            for (int i = 1; i < 19; i++) {
                if (!Arrays.asList(hisCards).contains(i)){
                    hersCards[idx] = i;
                    idx++;
                }
            }
            perm(0);
            bw.write(winTimes + " " + loseTimes + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void perm(int cnt) {
        if(cnt == 9){
            totalWinScore = 0;
            totalLoseScore = 0;
            for (int i = 0; i < 9; i++) {
                if (hisCards[i] > hersSelectedCards[i])
                    totalWinScore += hisCards[i] + hersSelectedCards[i];
                else
                    totalLoseScore += hisCards[i] + hersSelectedCards[i];
            }
            if (totalWinScore > totalLoseScore) winTimes++;
            else if (totalLoseScore > totalWinScore) loseTimes++;
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!isSelected[i]){
                isSelected[i] = true;
                hersSelectedCards[cnt] = hersCards[i];
                perm(cnt + 1);
                isSelected[i] = false;
            }
        }

    }
}
