package zalando;

import java.util.*;

public class Solution {
    public int solution1(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        } else if (S.length() <= 2) {
            return Integer.parseInt(S);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < S.length() - 1; i++) {
            max = Math.max(max, Integer.parseInt(S.substring(i, i + 2)));
        }
        return max;
    }

    public int solution2(String S) {
        if (Objects.isNull(S) || S.length() == 0) {
            return 0;
        }

        int count = 0;
        Map<Character, Integer> charToCount = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            Character c = S.charAt(i);
            if (charToCount.containsKey(S.charAt(i))) {
                charToCount.put(c, charToCount.get(c) + 1);
            } else {
                charToCount.put(c, 1);
            }
        }

        Queue<Integer> maxHeap = new PriorityQueue<>(charToCount.size(), Collections.reverseOrder());
        maxHeap.addAll(charToCount.values());
        while (!maxHeap.isEmpty() && maxHeap.peek() != 0) {
            int current = maxHeap.poll();
            if (maxHeap.isEmpty()) {
                break;
            }
            if (current == maxHeap.peek()) {
                count++;
                maxHeap.add(current - 1);
            }
        }
        return count;
    }

    public int solution3(int[] A) {
        if (Objects.isNull(A) || A.length <= 4) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;
        int p = 1, q = A.length - 2;

        while (p < q - 1) {
            minCost = Math.min(minCost, A[p] + A[q]);

            if (A[p] > A[q]) {
                p++;
            } else {
                q--;
            }
        }
        return minCost;
    }
}
