package exercise.prac03;

public class Permutation {
    static int N, R;
    static int[] totalNumbers, selectedNumbers;
    static boolean[] isSelected;

    public void permutation(int cnt){
        if(cnt == R){
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!isSelected[i]) {
                selectedNumbers[cnt] = totalNumbers[i];
                isSelected[i] = true;
                permutation(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}
