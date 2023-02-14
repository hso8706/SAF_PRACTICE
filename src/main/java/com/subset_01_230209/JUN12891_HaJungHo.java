package com.subset_01_230209;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JUN12891_HaJungHo {
    static int S, P;
    static String dna;
    static int[] acgt = new int[4];
    static String[] acgtDna = {"A", "C", "G", "T"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        P = sc.nextInt();
        dna = sc.next();
        for (int i = 0; i < 4; i++) {
            acgt[i] = sc.nextInt();
        }

        for (int i = 0; i <= S - P; i++) {
            String tempStr = dna.substring(i, i + P);
            System.out.println(tempStr);
        }
    }
}
