class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {

        int m = matrix.length;
        int n = matrix[0].length;

        int ans = Integer.MIN_VALUE;

        if (m <= n) {

            for (int top = 0; top < m; top++) {

                int[] cols = new int[n];

                for (int bottom = top; bottom < m; bottom++) {

                    for (int c = 0; c < n; c++)
                        cols[c] += matrix[bottom][c];

                    int cur = solve(cols, k);

                    if (cur > ans)
                        ans = cur;

                    if (ans == k)
                        return k;
                }
            }

        } else {

            for (int left = 0; left < n; left++) {

                int[] rows = new int[m];

                for (int right = left; right < n; right++) {

                    for (int r = 0; r < m; r++)
                        rows[r] += matrix[r][right];

                    int cur = solve(rows, k);

                    if (cur > ans)
                        ans = cur;

                    if (ans == k)
                        return k;
                }
            }
        }

        return ans;
    }


    public int solve(int[] nums, int k) {

        int[] prefix = new int[101];

        int size = 1;
        prefix[0] = 0;

        int sum = 0;
        int ans = Integer.MIN_VALUE;

        for (int x : nums) {

            sum += x;

            int l = 0, r = size;

            while (l < r) {
                int mid = (l + r) >>> 1;

                if (prefix[mid] < sum - k)
                    l = mid + 1;
                else
                    r = mid;
            }

            if (l < size)
                ans = Math.max(ans, sum - prefix[l]);


            int pos = size;

            while (pos > 0 && prefix[pos - 1] > sum) {
                prefix[pos] = prefix[pos - 1];
                pos--;
            }

            prefix[pos] = sum;
            size++;
        }

        return ans;
    }
}