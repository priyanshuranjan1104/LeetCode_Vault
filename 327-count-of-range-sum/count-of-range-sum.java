class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return mergeSort(prefix, 0, prefix.length - 1, lower, upper);
    }

    public int mergeSort(long[] prefix, int left, int right, int lower, int upper) {
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        int count = mergeSort(prefix, left, mid, lower, upper)
                  + mergeSort(prefix, mid + 1, right, lower, upper);

        int j = mid + 1, k = mid + 1, t = mid + 1;
        long[] temp = new long[right - left + 1];
        int r = 0;

        for (int i = left; i <= mid; i++) {
            while (k <= right && prefix[k] - prefix[i] < lower) k++;
            while (j <= right && prefix[j] - prefix[i] <= upper) j++;
            while (t <= right && prefix[t] < prefix[i]) temp[r++] = prefix[t++];
            temp[r++] = prefix[i];
            count += j - k;
        }

        System.arraycopy(temp, 0, prefix, left, r);
        return count;
    }
}
