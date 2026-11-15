class Solution {
    public int getMoneyAmount(int n) {

        int[][] dp = new int[n + 2][n + 2];

        // length = size of current interval
        for (int length = 2; length <= n; length++) {

            for (int left = 1; left + length - 1 <= n; left++) {

                int right = left + length - 1;

                dp[left][right] = Integer.MAX_VALUE;

                // Try every possible guess
                for (int guess = left; guess <= right; guess++) {

                    int leftCost = dp[left][guess - 1];
                    int rightCost = dp[guess + 1][right];

                    int cost = guess + Math.max(leftCost, rightCost);

                    dp[left][right] =
                            Math.min(dp[left][right], cost);
                }
            }
        }

        return dp[1][n];
    }
}