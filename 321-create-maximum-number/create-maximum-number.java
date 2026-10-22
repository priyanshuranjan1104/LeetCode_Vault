class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int n = nums1.length, m = nums2.length;
        int[] best = new int[k];

        for (int i = Math.max(0, k - m); i <= Math.min(k, n); i++) {

            int[] a = maxArray(nums1, i);
            int[] b = maxArray(nums2, k - i);
            int[] cur = merge(a, b, k);

            if (greater(cur, 0, best, 0))
                best = cur;
        }

        return best;
    }

    public int[] maxArray(int[] nums, int k) {

        int n = nums.length;
        int[] st = new int[k];
        int top = 0;

        for (int i = 0; i < n; i++) {

            while (top > 0 &&
                   st[top - 1] < nums[i] &&
                   top + n - i > k)
                top--;

            if (top < k)
                st[top++] = nums[i];
        }

        return st;
    }

    public int[] merge(int[] a, int[] b, int k) {

        int[] res = new int[k];

        int i = 0, j = 0, r = 0;

        while (r < k) {

            if (greater(a, i, b, j))
                res[r++] = a[i++];
            else
                res[r++] = b[j++];
        }

        return res;
    }

    public boolean greater(int[] a, int i, int[] b, int j) {

        int na = a.length;
        int nb = b.length;

        while (i < na && j < nb) {

            if (a[i] != b[j])
                return a[i] > b[j];

            i++;
            j++;
        }

        return j == nb;
    }
}