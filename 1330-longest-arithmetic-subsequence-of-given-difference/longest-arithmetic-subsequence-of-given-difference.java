class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 1;

        for (int num : arr) {
            int len = map.getOrDefault(num - difference, 0) + 1;
            map.put(num, len);
            ans = Math.max(ans, len);
        }

        return ans;
    }
}