package worldquant;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(getMaxUnits(Arrays.asList(1L, 2L, 3L), Arrays.asList(3L, 2L, 1L), 3));
    }

    public static long getMaxUnits(List<Long> boxes, List<Long> unitsPerBox, long truckSize) {
        Queue<Long> queue = new PriorityQueue(Collections.reverseOrder());

        for (int i = 0; i < boxes.size(); i++) {
            long box = boxes.get(i);
            long unit = unitsPerBox.get(i);
            for (int j = 0; j < box; j++) {
                queue.add(unit);
            }
        }
        Long sum = 0L;
        for (int i = 0; i < truckSize; i++) {
            sum += queue.poll();
        }
        return sum;
    }

    public long maximumUnits(long[][] boxTypes, long truckSize) {
        PriorityQueue<long[]> heap=new PriorityQueue<long[]>((b1, b2)->(Long.valueOf(b2[1]-b1[1]).intValue()));
        heap.addAll(Arrays.asList(boxTypes));
        int ans=0;
        while(!heap.isEmpty()){
            long box[]=heap.poll();
            long boxType=box[0];
            long boxUnits=box[1];
            long min=Math.min(boxType,truckSize);
            ans+=min*boxUnits;
            truckSize=truckSize-min;
            if(truckSize==0){
                break;
            }
        }
        return ans;
    }

    public static long prison(int n, int m, List<Integer> h, List<Integer> v) {
        boolean[] xs = new boolean[n + 1];
        boolean[] ys = new boolean[m + 1];
        for (Integer hor : h) xs[hor] = true;
        for (int ver : v) ys[ver] = true;
        int xm = 0, ym = 0;
        for (int i = 1, j = 0; i <= n; i++) {
            if (!xs[i]) j = 0;
            else xm = Math.max(xm, ++j);
        }
        for (int i = 1, j = 0; i <= m; i++) {
            if (!ys[i]) j = 0;
            else ym = Math.max(ym, ++j);
        }
        return (long) (xm + 1) * (ym + 1);
    }
}
