package com.subset_01_230209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JUN12891_HaJungHo_misu {
    static int S, P;
    static List<String> dna;
    static List<String> dnaRe;
    static int[] acgt = new int[4];
    static String[] acgtDna = {"A", "C", "G", "T"};
    static int sumOfacgt;
    static int totalCnt_O = 0;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        P = sc.nextInt();
        dna = new ArrayList<>();
        dnaRe = new ArrayList<>();

        String[] dnaString = sc.next().split("");
        dna.addAll(Arrays.asList(dnaString));
        dnaRe.addAll(Arrays.asList(dnaString));
        sumOfacgt = 0;

        for (int i = 0; i < 4; i++) {
            acgt[i] = sc.nextInt();
            sumOfacgt += acgt[i];
        }
        S -= sumOfacgt;
        P -= sumOfacgt;
        isSelected = new boolean[S];
        renewal();
    }

    private static void renewal() {
        int idx = 0;
        while (idx < 4){
            if(acgt[idx] == 0) {
                idx++;
                continue;
            }
            if(dnaRe.contains(acgtDna[idx])){
                dnaRe.remove(acgtDna[idx]);
                acgt[idx]--;
            }
            else{
                System.out.println(0);
                return;
            }
        }
        makeSentence(0);
        System.out.println(totalCnt_O);
    }

    private static void makeSentence(int cnt) {
        if(cnt == P){
            totalCnt_O++;
            return;
        }
        for (int i = 0; i < P; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;

                makeSentence(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}
