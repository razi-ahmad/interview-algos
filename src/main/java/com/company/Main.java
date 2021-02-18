package com.company;

import java.util.*;

public class Main {


    public int solution(String S) {
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

    public int solution1(String S) {
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

    static int minPos = 0;
    static double min = Double.MIN_VALUE;

    public static int solution4(int A[]) {
        if (Objects.isNull(A) || A.length <= 4) {
            return 0;
        }
        int MIN_COST = Integer.MAX_VALUE;

        for (int x = 1; x < A.length - 1; x++) {
            for (int y = x + 2; y < A.length - 1; y++) {
                MIN_COST = Math.min(MIN_COST, A[x] + A[y]);
            }
        }

        return MIN_COST;
    }

    private static void process(double value, int a) {
        if (value < min) {
            minPos = a;
            min = value;
        }
    }

    public static void main(String[] args) {
        /*System.out.println(solution4(new int[] {5,2,1,4,6,3,7}));
        System.out.println();
        System.out.println(solution4(new int[] {1,7,4,7,2,3,2,1,2,8}));*/
        String str1 = "geeksforgeeks";
        String str2 = "korgeeksgeeks";

        // Function call
        if (isAnagram(str1, str2))
            System.out.print("The two strings are " +
                    "anagram of each other");
        else
            System.out.print("The two strings are not " +
                    "anagram of each other");
    }

    static boolean isAnagram(String c, String d) {
        if (c.length() != d.length())
            return false;
        int count = 0;
        for (int i = 0; i < c.length(); i++) {
            count += c.charAt(i) - d.charAt(i);
        }
        return (count == 0);
    }
}
