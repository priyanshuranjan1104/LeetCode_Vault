class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][k + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int val = grid[i][j];
                int cost = (val == 0) ? 0 : 1;
                int score = val;

                for (int c = 0; c <= k; c++) {
                    if (dp[i][j][c] == -1) continue;

                    if (j + 1 < n) {
                        int nc = c + ((grid[i][j + 1] == 0) ? 0 : 1);
                        if (nc <= k) {
                            dp[i][j + 1][nc] = Math.max(
                                dp[i][j + 1][nc],
                                dp[i][j][c] + grid[i][j + 1]
                            );
                        }
                    }

                    if (i + 1 < m) {
                        int nc = c + ((grid[i + 1][j] == 0) ? 0 : 1);
                        if (nc <= k) {
                            dp[i + 1][j][nc] = Math.max(
                                dp[i + 1][j][nc],
                                dp[i][j][c] + grid[i + 1][j]
                            );
                        }
                    }
                }
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[m - 1][n - 1][c]);
        }

        return ans;
    }
}