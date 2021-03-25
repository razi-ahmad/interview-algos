package hackerranck;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Integer[] f = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        Integer[] t = {2, 3, 3, 3, 4, 1, 1, 1, 1, 3, 4, 4, 1, 1, 1, 1, 1, 1, 2, 2, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3};
        Integer[] w = {72, 8, 34, 45, 77, 3, 48, 64, 71, 18, 29, 77, 3, 23, 27, 42, 46, 65, 5, 43, 64, 66, 93, 2, 11, 30, 41, 60, 75, 94, 43, 65, 76, 91, 22, 24, 79};

        System.out.println(shared(37, Arrays.asList(f), Arrays.asList(t), Arrays.asList(w)));
    }

    public static int shared(int friendsNodes, List<Integer> from, List<Integer> to,
                             List<Integer> w) {
        Map<Integer, Set<Integer>> weightNodes = new HashMap<>();
        for (int i = 0; i < from.size(); i++) {
            weightNodes.computeIfAbsent(w.get(i), integer -> new HashSet<>());
            weightNodes.get(w.get(i)).add(from.get(i));
            weightNodes.get(w.get(i)).add(to.get(i));
        }

        int max = Integer.MIN_VALUE;
        int maxProduct = Integer.MIN_VALUE;
        Map<Pair, Integer> pairCounts = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> integerSetEntry : weightNodes.entrySet()) {
            Set<Integer> s = integerSetEntry.getValue();
            int[] v = s.stream().mapToInt(value -> value).toArray();
            int size = v.length;
            for (int i = 0; i < size - 1; i++) {
                Pair key = new Pair(v[i], v[i + 1]);
                if (pairCounts.containsKey(key)) {
                    pairCounts.put(key, pairCounts.get(key) + 1);
                } else {
                    pairCounts.put(key, 1);
                }
                if (max < pairCounts.get(key)) max = pairCounts.get(key);

            }
            if (size > 2) {
                Pair key = new Pair(v[0], v[size - 1]);
                if (pairCounts.containsKey(key)) {
                    pairCounts.put(key, pairCounts.get(key) + 1);
                } else {
                    pairCounts.put(key, 1);
                }
                if (max < pairCounts.get(key)) max = pairCounts.get(key);

            }
        }

        for (Map.Entry<Pair, Integer> entry : pairCounts.entrySet()) {
            Pair pr = entry.getKey();
            if (entry.getValue() == max) {
                if (maxProduct < pr.from * pr.to) maxProduct = pr.from * pr.to;
            }
        }

        return maxProduct;
    }
}

class Pair {
    int from;
    int to;

    public Pair(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return from == pair.from && to == pair.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
