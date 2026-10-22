class Solution {
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        int n = source.length();
        int m = pattern.length();

        boolean[] target = new boolean[n];

        for (int idx : targetIndices) {
            target[idx] = true;
        }

        int[] dp = new int[m + 1];
        java.util.Arrays.fill(dp, -1000000);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int[] next = new int[m + 1];
            java.util.Arrays.fill(next, -1000000);

            for (int j = 0; j <= m; j++) {
                if (dp[j] < 0) {
                    continue;
                }

                next[j] = Math.max(next[j], dp[j]);

                if (target[i]) {
                    next[j] = Math.max(next[j], dp[j] + 1);
                }

                if (j < m && source.charAt(i) == pattern.charAt(j)) {
                    next[j + 1] = Math.max(next[j + 1], dp[j]);
                }
            }

            dp = next;
        }

        return dp[m];
    }
}