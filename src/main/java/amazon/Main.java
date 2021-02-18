package amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int waitingTime(int A[], int X, int Y, int Z){
        int time=0;
        Queue<Integer> queue=new LinkedList<>();
        return 1;

    }

    static int minimumDistance(int A[]) {
        if (A.length == 1) {
            return -2;
        }
        Arrays.sort(A);
        long minDis = Long.MAX_VALUE;
        for (int i = 1; i < A.length; i++) {
            long dis = (long) A[i] - A[i - 1];
            if (dis < minDis) {
                minDis = dis;
            }
        }
        return minDis > 100000000 ? -1 : (int) minDis;
    }


    public static void main(String[] args) {
        int[] A = {0, 3, 3, 7, 5, 3,11, 1};//{1,4,7,3,3,5};

        System.out.println(minimumDistance(A));
        System.out.println(Integer.valueOf(225)==Integer.valueOf(225));
    }
}
