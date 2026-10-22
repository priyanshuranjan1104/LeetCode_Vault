class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] v = nums.clone();
        Arrays.sort(v);

        int mid = (n + 1) / 2;
        int left = mid - 1;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                nums[i] = v[left--];
            else
                nums[i] = v[right--];
        }
    }
}