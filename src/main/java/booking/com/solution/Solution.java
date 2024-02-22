package booking.com.solution;

import java.util.*;

public class Solution {

    private Map<Integer, Map<String, Integer>> getFlights(Integer startDay, Integer endDay) {
        return Map.of(
                3, Map.of("price", 100),
                4, Map.of("price", 300),
                5, Map.of("price", 500),
                6, Map.of("price", 500),
                7, Map.of("price", 200),
                8, Map.of("price", 100)
        );
    }
    //3,4,5,6,7,8,9,10,11

    private Map<Integer, Map<String, Integer>> getCheapestFlights(Integer dayRange, Integer startDay, Integer endDay) {
        Map<Integer, Map<String, Integer>> cheapestFlights = new HashMap<>();
        Map<Integer, Map<String, Integer>> flights = getFlights(startDay, endDay);
        List<Map.Entry<Integer, Map<String, Integer>>> references = new LinkedList<>();
        PriorityQueue<Map.Entry<Integer, Map<String, Integer>>> minPriceHeap = new PriorityQueue<>(Comparator.comparing(a -> a.getValue().get("price")));

        if (flights.size() >= dayRange) {
            // first subset int the heap
            int firstSubsetIndex = startDay;
            int lastSubsetIndex = startDay + dayRange;
            for (int i = firstSubsetIndex; i < lastSubsetIndex; i++) {
                Map.Entry<Integer, Map<String, Integer>> flight = Map.of(i, flights.get(i))
                        .entrySet()
                        .iterator()
                        .next();
                references.add(flight);
                minPriceHeap.add(flight);
            }

            // first minimum from first subset
            cheapestFlights.put(minPriceHeap.peek().getKey(), minPriceHeap.peek().getValue());
            firstSubsetIndex = lastSubsetIndex;
            lastSubsetIndex = endDay;
            for (int i = firstSubsetIndex; i <= lastSubsetIndex; i++) {
                Map.Entry<Integer, Map<String, Integer>> firstFlight = references.removeFirst();
                minPriceHeap.remove(firstFlight);
                Map.Entry<Integer, Map<String, Integer>> nextFlight = Map.of(i, flights.get(i))
                        .entrySet()
                        .iterator()
                        .next();
                references.add(nextFlight);
                minPriceHeap.add(Map.of(i, flights.get(i))
                        .entrySet()
                        .iterator()
                        .next()
                );
                cheapestFlights.put(minPriceHeap.peek().getKey(), minPriceHeap.peek().getValue());
            }
        }
        return cheapestFlights;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getCheapestFlights(3,3,8));
    }

}
