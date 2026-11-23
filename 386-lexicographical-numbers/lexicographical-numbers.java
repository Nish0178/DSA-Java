class Solution {
    public List<Integer> lexicalOrder(int n) {

        List<Integer> ans = new ArrayList<>();

        int current = 1;

        for (int i = 0; i < n; i++) {

            ans.add(current);

            // Try to go deeper: 1 -> 10 -> 100 ...
            if (current * 10 <= n) {
                current *= 10;
            }
            else {
                // If we cannot go deeper, move to next number
                while (current % 10 == 9 || current + 1 > n) {
                    current /= 10;
                }

                current++;
            }
        }

        return ans;
    }
}