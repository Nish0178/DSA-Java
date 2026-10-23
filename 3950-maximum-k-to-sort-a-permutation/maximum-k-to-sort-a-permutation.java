class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                if (ans == -1) {
                    ans = nums[i];
                } else {
                    ans &= nums[i];
                }
            }
        }

        // Already sorted
        if (ans == -1) {
            return 0;
        }

        return ans;
    
    }
}