class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;

        int[] stack = new int[k];
        int top = 0;

        for (int i = 0; i < n; i++) {

            // Remove larger elements if we can still
            // complete a subsequence of size k
            while (top > 0 &&
                   stack[top - 1] > nums[i] &&
                   top + (n - i) > k) {

                top--;
            }

            // Add current element if space is available
            if (top < k) {
                stack[top++] = nums[i];
            }
        }

        return stack;
    }
}