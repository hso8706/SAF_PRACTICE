package aSAF.greedy_230217;

public class GreedyTest_Online {

    private static class Meeting implements Comparable<Meeting>{
        int start, end;
        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
