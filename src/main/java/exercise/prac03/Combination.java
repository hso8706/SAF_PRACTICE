package exercise.prac03;

public class Combination {
    static int N, R;
    static int[] totalNumbers, selectedNumbers;

    public void combination(int cnt, int start){
        if(cnt == R){
            return;
        }
        for (int i = start; i < N; i++) {
            selectedNumbers[cnt] = totalNumbers[i];
            combination(cnt + 1, i + 1);
        }
    }
}
