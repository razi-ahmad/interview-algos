package ing;

public class Solution {

    public int[] solution(String R, int[] V) {
        int initialA = 0;
        int balanceA = 0;

        int initialB = 0;
        int balanceB = 0;

        if (V == null || V.length == 0) {
            return new int[]{initialA, initialB};
        }

        System.out.println("------------------------+---+--");
        for (int i = 0; i < R.length(); i++) {
            char transferTo = R.charAt(i);
            int amount = V[i];
            if (transferTo == 'A') {
                int difference = balanceB - amount;
                if (difference < 0) {
                    initialB += Math.abs(difference);
                    balanceB = 0;
                }
                balanceA += amount;
                System.out.println("transfer " + amount + " from B to A  |" + balanceA + " | " + balanceB);
            } else if (transferTo == 'B') {

                int difference = balanceA - amount;
                if (difference < 0) {
                    initialA += Math.abs(difference);
                    balanceA = 0;
                }
                balanceB += amount;
                System.out.println("transfer " + amount + " from A to B  |" + balanceA + " | " + balanceB);
            }
        }

        System.out.println(initialA + ","+ initialB);
        return new int[]{initialA, initialB};
    }

    public static void main(String[] args) {
        new Solution().solution("BAABA", new int[]{2, 4, 1, 1, 2});
    }
}

