package exercise.prac03;

public class SubSet {
    static int N, totalCount;
    static int[] totalArr;
    static boolean[] isSelected;

    private void subset(int cnt){
        if(cnt == N){
            return;
        }

        for (int i = 0; i < N; i++) {
            isSelected[cnt] = true;
            subset(cnt + 1);
            isSelected[cnt] = false;
            subset(cnt + 1);
        }
    }
}
