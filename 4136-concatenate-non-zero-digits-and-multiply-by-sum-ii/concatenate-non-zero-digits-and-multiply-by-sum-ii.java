class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int q = queries.length;
        int mod = 1_000_000_007;

        long[] pref = new long[n + 1];
        int[] cnt = new int[n + 1];
        int[] sum = new int[n + 1];
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % mod;
        }

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            sum[i + 1] = sum[i] + d;
            cnt[i + 1] = cnt[i];
            pref[i + 1] = pref[i];
            if (d != 0) {
                cnt[i + 1]++;
                pref[i + 1] = (pref[i] * 10 + d) % mod;
            }
        }

        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int nonZero = cnt[r + 1] - cnt[l];
            int digitSum = sum[r + 1] - sum[l];

            long x = (pref[r + 1] - pref[l] * pow10[nonZero]) % mod;
            if (x < 0) x += mod;

            ans[i] = (int) (x * digitSum % mod);
        }

        return ans;
    }
}