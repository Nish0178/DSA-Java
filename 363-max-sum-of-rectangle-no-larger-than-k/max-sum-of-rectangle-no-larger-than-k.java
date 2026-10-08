class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = Integer.MIN_VALUE;

        // If columns are fewer, fix columns.
        // Otherwise, fix rows to reduce complexity.
        if (m <= n) {
            for (int top = 0; top < m; top++) {

                int[] colSum = new int[n];

                for (int bottom = top; bottom < m; bottom++) {

                    // Compress rows top..bottom into 1D array
                    for (int col = 0; col < n; col++) {
                        colSum[col] += matrix[bottom][col];
                    }

                    ans = Math.max(ans, maxSubarrayNoLargerThanK(colSum, k));

                    if (ans == k) {
                        return k;
                    }
                }
            }
        } else {
            // Follow-up optimization:
            // If rows >> columns, fix columns instead.
            for (int left = 0; left < n; left++) {

                int[] rowSum = new int[m];

                for (int right = left; right < n; right++) {

                    // Compress columns left..right into 1D array
                    for (int row = 0; row < m; row++) {
                        rowSum[row] += matrix[row][right];
                    }

                    ans = Math.max(ans, maxSubarrayNoLargerThanK(rowSum, k));

                    if (ans == k) {
                        return k;
                    }
                }
            }
        }

        return ans;
    }

    private int maxSubarrayNoLargerThanK(int[] arr, int k) {
        TreeSet<Integer> set = new TreeSet<>();

        // Prefix sum 0
        set.add(0);

        int prefix = 0;
        int best = Integer.MIN_VALUE;

        for (int num : arr) {
            prefix += num;

            // Need previous prefix >= prefix - k
            Integer prev = set.ceiling(prefix - k);

            if (prev != null) {
                best = Math.max(best, prefix - prev);
            }

            set.add(prefix);
        }

        return best;
    }
}