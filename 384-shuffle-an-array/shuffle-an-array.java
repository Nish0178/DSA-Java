class Solution {

    private int[] original;

    public Solution(int[] nums) {
        original = nums.clone();
    }

    public int[] reset() {
        return original.clone();
    }

    public int[] shuffle() {
        int[] arr = original.clone();

        Random random = new Random();

        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }
}
