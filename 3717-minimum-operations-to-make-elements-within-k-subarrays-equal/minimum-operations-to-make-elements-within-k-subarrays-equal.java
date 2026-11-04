
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> low;
    TreeMap<Integer, Integer> high;

    int lowSize, highSize;
    long lowSum, highSum;

    private void addToMap(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private void removeFromMap(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }

    private void add(int val) {
        if (low.isEmpty() || val <= low.lastKey()) {
            addToMap(low, val);
            lowSize++;
            lowSum += val;
        } else {
            addToMap(high, val);
            highSize++;
            highSum += val;
        }

        balance();
    }

    private void remove(int val) {
        if (low.containsKey(val)) {
            removeFromMap(low, val);
            lowSize--;
            lowSum -= val;
        } else {
            removeFromMap(high, val);
            highSize--;
            highSum -= val;
        }

        balance();
    }

    private void balance() {
        while (lowSize > highSize + 1) {
            int val = low.lastKey();

            removeFromMap(low, val);
            lowSize--;
            lowSum -= val;

            addToMap(high, val);
            highSize++;
            highSum += val;
        }

        while (lowSize < highSize) {
            int val = high.firstKey();

            removeFromMap(high, val);
            highSize--;
            highSum -= val;

            addToMap(low, val);
            lowSize++;
            lowSum += val;
        }
    }

    private long getCost() {
        long median = low.lastKey();

        long leftCost = median * lowSize - lowSum;
        long rightCost = highSum - median * highSize;

        return leftCost + rightCost;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        low = new TreeMap<>();
        high = new TreeMap<>();

        lowSize = 0;
        highSize = 0;
        lowSum = 0;
        highSum = 0;

        // Calculate minimum cost for every window of size x.
        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            remove(nums[i - x]);
            add(nums[i]);

            cost[i - x + 1] = getCost();
        }

        // DP: minimum cost of selecting exactly j windows
        // from the first i elements.

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Selecting zero windows costs zero.
        Arrays.fill(prev, 0);

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip the current element.
                curr[i] = curr[i - 1];

                // Option 2: Select a window ending at i - 1.
                if (i >= x) {
                    long candidate =
                        prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], candidate);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
