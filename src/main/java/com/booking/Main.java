package com.booking;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void awardTopKHotels(String positiveKeywords, String negativeKeywords, List<Integer> hotelIds, List<String> reviews, int k) {
        String pWords[] = positiveKeywords.split(" ");
        String nWords[] = negativeKeywords.split(" ");

        Map<Integer, Integer> scores = new HashMap<>();
        for (int i = 0; i <scores.size(); i++) {
            Integer hotel = hotelIds.get(i);
            String review = reviews.get(i);
        }
    }

    private static void countPositiveScore(String[] words, String review) {
        int sum = 0;
        for (String w : words) {
            if (w.contains(review)) {
                sum += 3;
            }
        }
    }

    public static String getShiftedString1(String s, int leftShifts, int rightShifts) {
        if (Objects.isNull(s) || leftShifts < 0 || rightShifts < 0) {
            return null;
        }
        ArrayDeque<Character> deque = new ArrayDeque<>();
        s.chars().forEach(x -> deque.add(Character.valueOf((char) x)));
        for (int x = 0; x < leftShifts; x++) {
            char first = deque.removeFirst();
            deque.addLast(first);
        }
        for (int x = 0; x < rightShifts; x++) {
            char last = deque.removeLast();
            deque.addFirst(last);
        }
        return deque.stream().map(x -> String.valueOf(x)).collect(Collectors.joining());

    }

    public static void main(String[] args) {
        getShiftedString("abcd",2,2);
    }

    public static void getShiftedString(String s, int leftShifts, int rightShifts) {
        char a[] = s.toCharArray();
        reverseLeftArray(a, 0, leftShifts - 1);
        reverseLeftArray(a, leftShifts, a.length - 1);
        reverseLeftArray(a, 0, a.length - 1);

        reverseLeftArray(a, 0, a.length-1-rightShifts);
        reverseLeftArray(a, leftShifts, a.length-rightShifts - a.length-1);
        reverseLeftArray(a, 0, a.length - 1);
        System.out.println(a);
    }

    public static void reverseLeftArray(char[] a, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void reverseRightArray(char[] a, int start, int end) {
        for (int i = end, j = start; i > j; i--, j++) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
