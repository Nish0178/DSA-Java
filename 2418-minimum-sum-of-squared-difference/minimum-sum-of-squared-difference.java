class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
      int n = nums1.length;
        int maxDiff = 0;
        long totalOps = (long) k1 + k2;
        long sumDiff = 0;

        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            sumDiff += diff[i];
        }

        // All differences can become zero
        if (totalOps >= sumDiff) {
            return 0L;
        }

        // Count how many differences have each value
        int[] freq = new int[maxDiff + 1];

        for (int d : diff) {
            freq[d]++;
        }

        // Reduce the largest differences first
        for (int d = maxDiff; d > 0 && totalOps > 0; d--) {
            if (totalOps >= freq[d]) {
                totalOps -= freq[d];
                freq[d - 1] += freq[d];
                freq[d] = 0;
            } else {
                int operations = (int) totalOps;
                freq[d] -= operations;
                freq[d - 1] += operations;
                totalOps = 0;
            }
        }

        // Calculate the final squared sum
        long answer = 0;

        for (int d = 1; d < freq.length; d++) {
            answer += (long) d * d * freq[d];
        }

        return answer;   
    }
}