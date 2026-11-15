class Solution {
    public int numOfSubarrays(int[] arr) {
        int odd = 0;
        int parity = 0;

        for (int num : arr) {
            odd += (parity ^= (num & 1));
        }

        long even = arr.length - odd + 1;
        return (int) ((odd * even) % 1_000_000_007);
    }
}