class Solution {
    public final int[] original;
    public final int[] work;
    public final Random random = new Random();

    public Solution(int[] nums) {
        original = nums.clone();
        work = new int[nums.length];
    }

    public int[] reset() {
        return original.clone();
    }

    public int[] shuffle() {
        System.arraycopy(original, 0, work, 0, original.length);

        for (int i = work.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int t = work[i];
            work[i] = work[j];
            work[j] = t;
        }

        return work.clone();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */