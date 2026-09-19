class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] a : accounts) {
            int sum = 0;
            for (int x : a) sum += x;
            max = Math.max(max, sum);
        }
        return max;
    }
}