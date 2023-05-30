package cert.third;

import java.io.FileInputStream;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_Sumin {
    static class Task {
        int time;
        Set<Integer> in;
        Set<Integer> out;

        public Task(int time, Set<Integer> in, Set<Integer> out) {
            this.time = time;
            this.in = in;
            this.out = out;
        }
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("testcase/test3.txt")); //주석
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            // 노드들의 정보를 저장할 맵 선언
            Map<Integer, Task> allTasks = new HashMap<>();
            // 임무의 수 N 입력
            int N = Integer.parseInt(br.readLine());

            // 사람의 수 만큼 임무 수 초기화
            for (int task = 1; task <= N; task++) {
                // 임무완료 시간, 나에게 들어오는 노드, 나로부터 나가는 노드
                allTasks.put(task, new Task(0, new HashSet<>(), new HashSet<>()));
            }

            for (int task = 1; task <= N; task++) {
                String[] temp = br.readLine().split(" ");
                int time = Integer.parseInt(temp[0]);
                int numIn = Integer.parseInt(temp[1]);
                Task currentTask = allTasks.get(task);
                currentTask.time = time;

                for (int idx = 2; idx < numIn + 2; idx++) {
                    int inTask = Integer.parseInt(temp[idx]);
                    currentTask.in.add(inTask);
                    allTasks.get(inTask).out.add(task);
                }
            }

            // 위상정렬
            Queue<int[]> q = new LinkedList<>();

            for (Map.Entry<Integer, Task> entry : allTasks.entrySet()) {
                if (entry.getValue().in.isEmpty()) {
                    q.add(new int[]{entry.getKey(), 0});
                }
            }

            int answer = Integer.MAX_VALUE;

            for (int target = 1; target <= N; target++) {
                // 맵 복사
                Map<Integer, Task> taskList = new HashMap<>();
                for (Map.Entry<Integer, Task> entry : allTasks.entrySet()) {
                    taskList.put(entry.getKey(), new Task(entry.getValue().time, new HashSet<>(entry.getValue().in), new HashSet<>(entry.getValue().out)));
                }

                taskList.get(target).time /= 2;

                int[] beforeMaxTime = new int[N + 1];

                Queue<int[]> queue = new LinkedList<>(q);

                int sumTime = 0;

                // 위상정렬 시작
                while (!queue.isEmpty()) {
                    int[] info = queue.poll();
                    int tn = info[0];
                    int time = info[1];

                    // 최대시간
                    sumTime = Math.max(sumTime, time + taskList.get(tn).time);

                    for (int nextLoc : taskList.get(tn).out) {
                        taskList.get(nextLoc).in.remove(tn);
                        beforeMaxTime[nextLoc] = Math.max(beforeMaxTime[nextLoc], time + taskList.get(tn).time);

                        if (taskList.get(nextLoc).in.isEmpty()) {
                            queue.add(new int[]{nextLoc, beforeMaxTime[nextLoc]});
                        }
                    }
                }

                // 모두 방문시
                if (isCheck(taskList)) {
                    // 최소시간
                    answer = Math.min(sumTime, answer);
                }
            }

            if (answer == Integer.MAX_VALUE) {
                sb.append("#").append(test_case).append(" ").append(-1).append("\n");
            } else {
                sb.append("#").append(test_case).append(" ").append(answer).append("\n");
            }
        }

        // 결과 출력
        System.out.print(sb.toString());
    }


    public static boolean isCheck(Map<Integer, Task> table) {
        for (Task task : table.values()) {
            if (!task.in.isEmpty()) {
                return false;
            }
        }
        return true;
    }

}

