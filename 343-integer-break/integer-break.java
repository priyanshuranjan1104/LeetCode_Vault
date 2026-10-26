class Solution{
    public int integerBreak(int n){
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return solve(n,dp);
    }
    int solve(int n,int[] dp){
        if(n==1) return 1;
        if(dp[n]!=-1) return dp[n];
        int max=0;
        for(int i=1;i<=n/2;i++){
            max=Math.max(max,Math.max(i*(n-i),i*solve(n-i,dp)));
        }
        return dp[n]= max;
    }
}