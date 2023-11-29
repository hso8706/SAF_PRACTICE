public class Solution {

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int rounds = 0;
        int cardIdx = 0;

        while (cardIdx < n) {
            // 현재 라운드에서 가능한 카드 두 장의 합
            int targetSum = n + 1;

            // 카드를 두 장 뽑아 합이 targetSum이 되도록 하기
            while (cardIdx < n && cards[cardIdx] + cards[cardIdx + 1] != targetSum) {
                // 동전이 남아있으면 카드를 가지고, 아니면 버림
                if (coin > 0) {
                    coin--;
                }
                cardIdx += 2;  // 다음 카드 두 장으로 이동
            }

            // 두 장의 합이 targetSum이 되도록 뽑을 수 있는 경우
            if (cardIdx < n) {
                rounds++;
                // 동전을 소모해서 카드를 가지거나, 동전을 소모하지 않고 카드를 버릴 수 있음
                coin = Math.max(coin - 1, 0);
                cardIdx += 2;  // 다음 라운드로 진행
            }
        }

        return rounds;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 예시 테스트
        System.out.println(solution.solution(4, new int[]{3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4}));  // 5
        System.out.println(solution.solution(3, new int[]{1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12}));  // 2
        System.out.println(solution.solution(2, new int[]{5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7}));  // 4
        System.out.println(solution.solution(10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}));  // 1
    }
}
