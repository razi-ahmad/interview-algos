package mollie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Result {

    /*
     * Complete the 'fetchItemsToDisplay' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY items
     *  2. INTEGER sortParameter
     *  3. INTEGER sortOrder
     *  4. INTEGER itemsPerPage
     *  5. INTEGER pageNumber
     */

    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber) {
        Stream<List<String>> listStream = items.stream();
        List<List<String>> result;
        if (sortParameter == 0) {
            listStream = listStream.sorted((o1, o2) -> {
                if (sortOrder == 0) {
                    return o1.get(0).compareTo(o2.get(0));
                } else {
                    return o2.get(0).compareTo(o1.get(0));
                }
            });
        } else {
            if (sortOrder == 0) {
                listStream = sortByIntegerAscending(listStream, sortParameter);
            } else {
                listStream = sortByIntegerDescending(listStream, sortParameter);
            }
        }
        result = listStream.collect(Collectors.toList());
        //int pages = (int) Math.ceil(result.size() / itemsPerPage);
        List<String> displayItems = new ArrayList<>();
        int index = pageNumber * itemsPerPage;
        for (int i = 0; i < itemsPerPage && index < result.size(); i++) {
            displayItems.add(result.get(index++).get(0));
        }
        return displayItems;
    }

    private static Stream<List<String>> sortByIntegerAscending(Stream<List<String>> listStream, int sortParameter) {
        return listStream.sorted(Comparator.comparing(o -> Integer.valueOf(o.get(sortParameter))));
    }

    private static Stream<List<String>> sortByIntegerDescending(Stream<List<String>> listStream, int sortParameter) {
        return listStream.sorted((o1, o2) -> Integer.valueOf(o2.get(sortParameter)).compareTo(Integer.valueOf(o1.get(sortParameter))));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        List<List<String>> data=Arrays.asList(
                Arrays.asList("item1", "10", "15"),
                Arrays.asList("item2", "3", "4"),
                Arrays.asList("item3", "17", "8")
        );
        System.out.println(
                Result.fetchItemsToDisplay(data,1,0,2,1)
        );
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int itemsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int itemsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> items = new ArrayList<>();

        IntStream.range(0, itemsRows).forEach(i -> {
            try {
                items.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int sortParameter = Integer.parseInt(bufferedReader.readLine().trim());

        int sortOrder = Integer.parseInt(bufferedReader.readLine().trim());

        int itemsPerPage = Integer.parseInt(bufferedReader.readLine().trim());

        int pageNumber = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> result = Result.fetchItemsToDisplay(items, sortParameter, sortOrder, itemsPerPage, pageNumber);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();*/
    }
}
