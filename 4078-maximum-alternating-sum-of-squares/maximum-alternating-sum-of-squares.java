import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            long x = nums[i];
            squares[i] = x * x;
        }

        Arrays.sort(squares);

        int positiveCount = (nums.length + 1) / 2;
        long score = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i >= nums.length - positiveCount) {
                score += squares[i];
            } else {
                score -= squares[i];
            }
        }

        return score;
    }
}