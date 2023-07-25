package ing;

import java.util.Arrays;

class TestSolution {
    public int solution(int[] A) {
        int result = 1;
        if (A == null) {
            return result;
        }
        A = Arrays.stream(A).filter(s -> s >= 0).toArray();
        if (A.length == 0) return result;

        int sumOfPositiveNumber = (A.length * (A.length + 1)) / 2;
        int sumOfElements = Arrays.stream(A).sum();

        int difference = sumOfPositiveNumber - sumOfElements;
        if (difference == 0) {
            result = A.length + 1;
        } else if (A.length % 2 == 0) {
            result = ((sumOfPositiveNumber - sumOfElements) + 1);
        } else {
            result = sumOfPositiveNumber - sumOfElements;
        }
        return result;
    }

    public static void main(String[] args) {
        new TestSolution().solution(new int[]{-1, -3});
        new TestSolution().solution(new int[]{1, 3, 6, 4, 1, 2});
        new TestSolution().solution(new int[]{1, 2, 3});
    }
}
