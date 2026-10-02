class Solution {
    public int numDecodings(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n == 1) {
            return arr[0] == '0' ? 0 : 1;
        }
        if (arr[0] == '0') {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        if (arr[1] == '0' && arr[0] != '1' && arr[0] != '2') {
            return 0;
        }
        int sum = (arr[0] - '0') * 10 + arr[1] - '0';
        dp[1] = 1 + ((sum > 10 && sum != 20 && sum <= 26) ? 1 : 0);
        for (int i = 2; i < n; i++) {
            if (arr[i] == '0') {
                if (arr[i - 1] != '1' && arr[i - 1] != '2') {
                    return 0;
                } else {
                    dp[i] = dp[i - 2];
                }
            } else {
                sum = (arr[i - 1] - '0') * 10 + arr[i] - '0';
                dp[i] = dp[i - 1];
                if (sum > 10 && sum <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[n - 1];
    }
}