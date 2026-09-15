class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // pal[l][r] is true if s[l..r] is a palindrome
        boolean[][] pal = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            pal[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                pal[i][i + 1] = true;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                if (chars[l] == chars[r] && pal[l + 1][r - 1]) {
                    pal[l][r] = true;
                }
            }
        }

        // dp[end] = maximum number of valid palindromic substrings in s[0..end-1]
        int[] dp = new int[n + 1];

        for (int end = 1; end <= n; end++) {
            // Skip s[end - 1]
            dp[end] = dp[end - 1];

            // Try to take a palindrome s[start..end-1] of length at least k
            for (int start = 0; start <= end - k; start++) {
                if (pal[start][end - 1]) {
                    dp[end] = Math.max(dp[end], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}