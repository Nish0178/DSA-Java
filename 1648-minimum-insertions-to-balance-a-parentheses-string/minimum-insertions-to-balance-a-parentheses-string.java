class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                open++;
            } else {

                // No '(' available for this ')'
                if (open == 0) {
                    ans++;
                    open++;
                }

                // This ')' is the first closing parenthesis
                open--;

                // Need another ')' immediately
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert the missing ')'
                    ans++;
                }
            }
        }

        // Every remaining '(' needs two ')'
        ans += open * 2;

        return ans;
    }
}