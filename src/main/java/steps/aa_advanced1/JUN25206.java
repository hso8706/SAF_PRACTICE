package steps.aa_advanced1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN25206 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static double sumCredit;
    static double sumGrade;
    static double avg;
    public static void main(String[] args) throws IOException {
        
        init();

        System.out.printf("%.6f", calculateAvg());
    }

    private static double calculateAvg() {
        return sumGrade/sumCredit;
    }

    private static void init() throws IOException {

        String subject = "";
        double credit = 0.0;
        String grade = "";
        sumCredit = 0.0;
        sumGrade = 0.0;

        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(bf.readLine());
            subject = st.nextToken();
            credit = Double.parseDouble(st.nextToken());
            grade = st.nextToken();

            if(grade.equals("P")) continue;
            sumCredit+=credit;

            switch (grade) {
                case "A+":
                    sumGrade += credit * 4.5;
                    break;
                case "A0":
                    sumGrade += credit * 4.0;
                    break;
                case "B+":
                    sumGrade += credit * 3.5;
                    break;
                case "B0":
                    sumGrade += credit * 3.0;
                    break;
                case "C+":
                    sumGrade += credit * 2.5;
                    break;
                case "C0":
                    sumGrade += credit * 2.0;
                    break;
                case "D+":
                    sumGrade += credit * 1.5;
                    break;
                case "D0":
                    sumGrade += credit * 1.0;
                    break;
                case "F":
                    sumGrade += credit * 0.0;
                    break;

            }
        }
    }

}
