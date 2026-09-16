class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        // Answer = C(n + k - 1, 2k)
        int N = n + k - 1;
        int R = 2 * k;

        long[] fact = new long[N + 1];
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        long[] invFact = new long[N + 1];
        invFact[N] = modPow(fact[N], MOD - 2);
        for (int i = N; i >= 1; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        long ans = fact[N];
        ans = ans * invFact[R] % MOD;
        ans = ans * invFact[N - R] % MOD;

        return (int) ans;
    }

    private long modPow(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = res * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }
}