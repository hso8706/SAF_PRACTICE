package aSAF.backtTracking_230221;

import java.io.*;
import java.util.StringTokenizer;

public class JUN6987_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static boolean impossible;

    static int[][] nation = new int[6][3];
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            impossible = false;
            st = new StringTokenizer(bf.readLine());
            label: for (int n = 0; n < 6; n++) {
                int tempSum = 0;
                for (int j = 0; j < 3; j++) {
                    nation[n][j] = Integer.parseInt(st.nextToken());
                    tempSum += nation[n][j];
                }
                if (tempSum != 5) {
                    bw.write(0 + " ");
                    impossible = true;
                    break label;
                }
            }
            if(!impossible){
                out: for (int j = 0; j < 6; j++) {
                    if(nation[j][0] != 0){
                        for (int k = j; k <6; k++) {
                            if (nation[k][2] != 0){
                                nation[j][0]--;
                                nation[k][2]--;
                                if (nation[j][0] < 0 && nation[k][2] < 0){
//                                    bw.write(0 + " ");
                                    impossible = true;
                                    break out;
                                }
                            }
                        }
                        if(nation[j][0] != 0){
//                            bw.write(0 + " ");
                            impossible = true;
                            break;
                        }
                    }
                }
            }
            if(!impossible){
                out: for (int j = 0; j < 6; j++) {
                    if(nation[j][1] != 0){
                        for (int k = 0; k < 6; k++) {
                            if (j == k) continue;
                            if (nation[k][1] != 0){
                                nation[j][1]--;
                                nation[k][1]--;
                                if(nation[j][1] < 0 && nation[k][1] < 0){
//                                    bw.write(0 + " ");
                                    impossible = true;
                                    break out;
                                }
                            }
                        }
                        if(nation[j][1] != 0){
//                            bw.write(0 + " ");
                            impossible = true;
                            break;
                        }
                    }
                }
            }
            if (impossible) bw.write(0 + " ");
            else bw.write(1 + " ");

        }
        bw.flush();
        bw.close();
    }
}
