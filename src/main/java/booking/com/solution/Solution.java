package booking.com.solution;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    private Map<Integer, Map<String, Integer>> getCheapestFlights(Integer dayRange, Integer startDay, Integer endDay) {
        Map<Integer, Map<String, Integer>> flights = getFlights(startDay, endDay);

        if (endDay - startDay >= dayRange) {

        }
        return Collections.EMPTY_MAP;
    }

    //ON

    //3,4 -> 100
    //3,4,5->100
    //4,5,->
}
