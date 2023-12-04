package steps.ae_rectangle_triangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN10101 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int sum = 0;
        int[] angles = new int[3];
        for (int i = 0; i < 3; i++) {
            angles[i] = Integer.parseInt(bf.readLine());
            sum += angles[i];
        }
        if (sum == 180) {
            if (angles[0] == angles[1] && angles[1] == angles[2]) {
                System.out.println("Equilateral");
            }
            else if (angles[0] != angles[1] && angles[1] != angles[2] && angles[2] != angles[0]){
                System.out.println("Scalene");
            }
            else {
                System.out.println("Isosceles");
            }
        }
        else {
            System.out.println("Error");
        }
    }
}
