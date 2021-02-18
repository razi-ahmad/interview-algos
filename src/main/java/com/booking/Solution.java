package com.booking;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'awardTopKHotels' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING positiveKeywords
     *  2. STRING negativeKeywords
     *  3. INTEGER_ARRAY hotelIds
     *  4. STRING_ARRAY reviews
     *  5. INTEGER k
     */

    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords, List<Integer> hotelIds, List<String> reviews, int k) {


        if(k == 0 || reviews.isEmpty())
            return Arrays.asList();
        List<Integer> result = new ArrayList<>();
        Set<String> positiveKeys = new HashSet<>(Arrays.asList(positiveKeywords.split(" ")));
        Set<String> negativeKeys = new HashSet<>(Arrays.asList(negativeKeywords.split(" ")));
        Queue<Entry<Integer, Integer>> max_heap = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
        Map<Integer, Integer> idByReview = new HashMap<>();
        for(int x=0; x < hotelIds.size(); x++) {
            if(containsAny(positiveKeys, reviews.get(x))) {
                addToMap(3, hotelIds.get(x), idByReview);
            } else if(containsAny(negativeKeys, reviews.get(x))) {
                addToMap(-1, hotelIds.get(x), idByReview);
            }
        }
        max_heap.addAll(idByReview.entrySet());
        for(int x=0; x<k && !max_heap.isEmpty() ; x++) {
            result.add(max_heap.remove().getKey());
        }
        return result;

    }
    private static void addToMap(int score, Integer hotelId, Map<Integer, Integer> idByReview) {
        if(idByReview.containsKey(hotelId)) {
            idByReview.put(hotelId, idByReview.get(hotelId) + score);
        } else {
            idByReview.put(hotelId, score);
        }
        
    }
    
    public static boolean containsAny(Set<String> keys, String paragraph) {
        return keys.stream().parallel().anyMatch(x -> paragraph.indexOf(x) > -1);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String positiveKeywords = bufferedReader.readLine();

        String negativeKeywords = bufferedReader.readLine();

        int hotelIdsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> hotelIds = IntStream.range(0, hotelIdsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int reviewsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> reviews = IntStream.range(0, reviewsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> result = Result.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, k);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}