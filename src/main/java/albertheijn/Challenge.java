package albertheijn;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Challenge {
    private static final Map<Character, Character> complements = Map.of('A', 'T', 'C', 'G', 'T', 'A', 'G', 'C');

    public static void main(String[] args) {
        System.out.println(streams(10));

    }

    public static String dnaComplement(String dna) {
        if (dna == null) return "";
        StringBuilder complement = new StringBuilder();
        for (char c : dna.toCharArray()) {
            System.out.println(c);
            complement.append(complements.get(c));
        }
        return complement.toString();
    }

    public static String fizzBuzz(int n) {
        if (n < 1) return "";
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (int i = 2; i <= n; i++) {
            builder.append("\n");
            if (i % 3 == 0 && i % 5 == 0) {
                builder.append("FizzBuzz");
            } else if (i % 3 == 0) {
                builder.append("Fizz");
            } else if (i % 5 == 0) {
                builder.append("Buzz");
            } else {
                builder.append(i);
            }
        }


        return builder.toString();

    }

    public static int streams(Integer number) {
        if (number == null || number < 2) return 0;
        return IntStream.rangeClosed(2, number).filter(n -> n % 3 == 0).sum();
    }

    public static List<String> getAllNames(List<Customer> customers, int minAge) {
        if (customers == null) return Collections.emptyList();

        return customers
                .stream()
                .filter(c -> c.getAge() >= minAge)
                .map(c -> c.getLastName() + ", " + c.getFirstName())
                .sorted()
                .collect(Collectors
                        .toList());
    }
}

class Customer {
    private final String firstName;
    private final String lastName;
    private final Integer age;

    Customer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }


}