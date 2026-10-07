class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        if (target > x + y)
            return false;

        return target % gcd(x, y) == 0;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}