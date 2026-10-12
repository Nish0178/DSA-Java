import java.util.*;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        
        HashMap<Integer, Integer> map = new HashMap<>();

        // Store frequency of elements in nums1
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        ArrayList<Integer> result = new ArrayList<>();

        // Check elements of nums2
        for (int num : nums2) {
            if (map.getOrDefault(num, 0) > 0) {
                result.add(num);

                // Use one occurrence
                map.put(num, map.get(num) - 1);
            }
        }

        // Convert ArrayList<Integer> to int[]
        int[] ans = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}