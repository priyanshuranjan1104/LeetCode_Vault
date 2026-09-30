class Solution {
    public String getPermutation(int n, int k) {
        int[] fact = new int[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        boolean[] used = new boolean[n + 1];
        StringBuilder res = new StringBuilder();

        k--;

        for (int pos = n; pos >= 1; pos--) {
            int block = fact[pos - 1];
            int idx = k / block;
            k %= block;

            for (int num = 1; num <= n; num++) {
                if (used[num])
                    continue;

                if (idx == 0) {
                    res.append(num);
                    used[num] = true;
                    break;
                }

                idx--;
            }
        }

        return res.toString();
    }
}