import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] answer = {};
        int[] first = {1,2,3,4,5};
        int[] second = {2,1,2,3,2,4,2,5};
        int[] third = {3,3,1,1,2,2,4,4,5,5};

        int fIdx, fCnt = 0;
        int sIdx, sCnt = 0;
        int tIdx, tCnt = 0;
        fCnt = 3;
        sCnt = 3;

        System.out.println(Math.max(fCnt, sCnt));
    }
}
