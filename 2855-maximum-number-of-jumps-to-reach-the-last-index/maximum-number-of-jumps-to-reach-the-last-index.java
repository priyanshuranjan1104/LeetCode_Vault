class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        
        for (int i = 1; i < n; i++) {
            dp[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            if (i != 0 && dp[i] == -1) continue;

            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[j] - nums[i]) <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}