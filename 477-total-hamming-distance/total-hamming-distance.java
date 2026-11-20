class Solution {
    public int totalHammingDistance(int[] nums) {
        int[] bits = new int[32];
        int n = nums.length;

        for (int num : nums) {
            for (int b = 0; b < 32; b++) {
                bits[b] += num & 1;
                num >>>= 1;
            }
        }

        long ans = 0;
        for (int count : bits) {
            ans += (long) count * (n - count);
        }

        return (int) ans;
    }
}