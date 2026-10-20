class Solution {
    Integer[][] dp;
    public int solve(int[] nums, int i, int j){
        if(i>j) return 0;

        if(dp[i][j]!=null) return dp[i][j];

        int ans = Integer.MIN_VALUE;
        for(int k=i; k<=j; k++){
            int left = solve(nums, i, k-1);
            int coin = (i==0? 1 : nums[i-1]) * nums[k] * (j==nums.length-1? 1 : nums[j+1]);
            int right = solve(nums, k+1, j);

            int currGain = left + coin + right;
            ans = Math.max(ans, currGain);
        }
        return dp[i][j] = ans;
    }
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int i=n-1; i>=0; i--){
            for(int j=0; j<n; j++){
                if(i>j){
                    dp[i][j] = 0;
                    continue;
                }

                int ans = Integer.MIN_VALUE;
                for(int k=i; k<=j; k++){
                    int left = k==i? 0 : dp[i][k-1];
                    int coin = (i==0? 1 : nums[i-1]) * nums[k] * (j==nums.length-1? 1 : nums[j+1]);
                    int right = k==j? 0 : dp[k+1][j];

                    int currGain = left + coin + right;
                    ans = Math.max(ans, currGain);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][n-1];
    }
}