class Solution {
    public int numberOfBoomerangs(int[][] p) {
        int n = p.length;
        int size = 1;

        while (size < n * 2) size <<= 1;

        int[] key = new int[size];
        int[] cnt = new int[size];
        int[] seen = new int[size];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int stamp = i + 1;

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                int dx = p[i][0] - p[j][0];
                int dy = p[i][1] - p[j][1];
                int d = dx * dx + dy * dy;

                int k = (d * 0x9E3779B9) & (size - 1);

                while (seen[k] == stamp && key[k] != d)
                    k = (k + 1) & (size - 1);

                if (seen[k] != stamp) {
                    seen[k] = stamp;
                    key[k] = d;
                    cnt[k] = 0;
                }

                ans += cnt[k] << 1;
                cnt[k]++;
            }
        }

        return ans;
    }
}