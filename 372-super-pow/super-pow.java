class Solution {
    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        a %= MOD;

        int result = 1;

        for (int digit : b) {
            result = (pow(result, 10) * pow(a, digit)) % MOD;
        }

        return result;
    }

    private int pow(int base, int exp) {
        long result = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return (int) result;
    }
}