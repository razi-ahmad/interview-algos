package worldline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


class Result {

    /*
     * Complete the 'ways' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER total
     *  2. INTEGER k
     */

    public static int ways(int total, int k) {
        // Write your code here
        return backtrack(IntStream.rangeClosed(1, k).boxed().mapToInt(i -> i).toArray(), 0, total, new ArrayList()) % 1000000007;
    }


    private static int backtrack(int[] cand, int start, int target, List<Integer> list) {
        if (target < 0)
            return 0;
        if (target == 0)
            return 1;

        int count = 0;
        for (int i = start; i < cand.length; i++) {
            list.add(cand[i]);
            count += backtrack(cand, i, target - cand[i], list);
            list.remove(list.size() - 1);
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(ways(8, 2));
        System.out.println(ways(5, 3));
        System.out.println(ways(4, 2));
        //842, 91 -> 143119619
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int total = Integer.parseInt(bufferedReader.readLine().trim());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.ways(total, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
