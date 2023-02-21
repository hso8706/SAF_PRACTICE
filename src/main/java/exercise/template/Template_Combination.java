package exercise.template;

public class Template_Combination {
    static int N, R, totalCount;
    static int[] totalArr, selectedArr;
    private void combi(int checkPoint, int startPoint){
        if(checkPoint == R){
            totalCount++;
            return;
        }
        for (int i = startPoint; i < N; i++) {//startPoint를 지정함으로써 중복를 배제함(순열처럼 인덱스를 다시 돌아오지 않는다)
            selectedArr[checkPoint] = totalArr[i];
            combi(checkPoint + 1, i + 1);
        }
    }
}
