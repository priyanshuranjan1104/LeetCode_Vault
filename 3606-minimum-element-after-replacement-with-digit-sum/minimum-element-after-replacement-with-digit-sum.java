class Solution {
    public int minElement(int[] nums) {
        int ans = Integer.MAX_VALUE;

        for (int num : nums) {
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            ans = Math.min(ans, sum);
        }

        return ans;
    }
}