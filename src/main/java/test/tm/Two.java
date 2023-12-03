package test.tm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Two {
    public static void main(String[] args) {
        int n1 = 30, m1 = 10;
        String[] records1 = {"1 enter", "5 enter", "8 cancel 0", "22 enter", "24 cancel 2", "27 enter"};
        int[] result1 = solution(n1, m1, records1);
        printResult(result1); // [1,3]
        System.out.println(Arrays.toString(result1));

        int n2 = 23, m2 = 5;
        String[] records2 = {"2 enter", "4 enter", "12 enter", "14 cancel 2", "21 enter"};
        int[] result2 = solution(n2, m2, records2);
        printResult(result2); // [0,1]
    }

    public static int[] solution(int n, int m, String[] records) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> canceledTickets = new HashSet<>();

        int lastTicket = 0;

        for (String record : records) {
            String[] split = record.split(" ");
            int t = Integer.parseInt(split[0]);

            while (!queue.isEmpty() && t - queue.peek() >= m) {
                int processedTicket = queue.poll();
                if (!canceledTickets.contains(processedTicket)) {
                    result.add(processedTicket);
                }
            }

            if (split[1].equals("enter")) {
                queue.offer(lastTicket);
                lastTicket++;
            } else {  // cancel
                int cancelNumber = Integer.parseInt(split[2]);
                canceledTickets.add(cancelNumber);
            }
        }

        while (!queue.isEmpty()) {
            int processedTicket = queue.poll();
            if (!canceledTickets.contains(processedTicket)) {
                result.add(processedTicket);
            }
        }

        Collections.sort(result);

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private static void printResult(int[] result) {
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}