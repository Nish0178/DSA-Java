class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        int result = 10;
        int unique = 9;
        int available = 9;

        for (int digits = 2; digits <= n; digits++) {
            unique *= available;
            result += unique;
            available--;
        }

        return result;
    }
}