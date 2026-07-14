class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        int[][] dp = new int[maxNum + 1][maxNum + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] next = new int[maxNum + 1][maxNum + 1];
            for (int x = 0; x <= maxNum; x++) {
                for (int y = 0; y <= maxNum; y++) {
                    int cur = dp[x][y];
                    if (cur == 0) continue;

                    next[x][y] = (next[x][y] + cur) % MOD;

                    int gx = gcd(x, num);
                    next[gx][y] = (next[gx][y] + cur) % MOD;

                    int gy = gcd(y, num);
                    next[x][gy] = (next[x][gy] + cur) % MOD;
                }
            }
            dp = next;
        }

        int ans = 0;
        for (int g = 1; g <= maxNum; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}