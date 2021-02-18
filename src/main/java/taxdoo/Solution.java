package taxdoo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        long n = Long.parseLong(bufferedReader.readLine().trim());
        bufferedReader.readLine();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            String line=bufferedReader.readLine().trim();
            lines.add(line);
        }
        List<String> result = Solution.parseCSV(lines);
        for (String s:result) {
            bufferedWriter.write(String.valueOf(s));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<String> parseCSV(List<String> data) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            String[] csv = line.split(",");
            Transaction t = new Transaction(Integer.parseInt(csv[0]),
                    csv[1], Integer.parseInt(csv[2]), Double.parseDouble(csv[3]),
                    csv[4], 
                    Arrays.stream(csv[5].split(" ")).filter(s -> s.contains("Inv-")).findFirst().get());
            transactions.add(t);
        }
        return distinctInvoice(transactions);
    }

    public static List<String> distinctInvoice(List<Transaction> transactions) {

        return transactions.stream().filter(distinctByKey(Transaction::getDescription)).map(Transaction::getDescription).sorted().collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}


class Transaction {
    int id;
    String paydate;
    int quantity;
    double price;
    String currency;
    String description;

    Transaction(int id, String paydate, int quantity, double price, String currency, String description) {
        this.id = id;
        this.paydate = paydate;
        this.quantity = quantity;
        this.price = price;
        this.currency = currency;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}