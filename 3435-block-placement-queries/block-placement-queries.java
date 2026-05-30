class FenwickTree {
    int[] bit;

    FenwickTree(int n) {
        bit = new int[n + 1];
    }

    void update(int idx, int val) {
        while (idx < bit.length) {
            bit[idx] = Math.max(bit[idx], val);
            idx += idx & -idx;
        }
    }

    int query(int idx) {
        int res = 0;
        while (idx > 0) {
            res = Math.max(res, bit[idx]);
            idx -= idx & -idx;
        }
        return res;
    }
}

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int limit = Math.min(50000, queries.length * 3);

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(limit);

        for (int[] q : queries) {
            if (q[0] == 1) {
                set.add(q[1]);
            }
        }

        FenwickTree bit = new FenwickTree(limit + 2);

        Integer prev = null;
        for (int x : set) {
            if (prev != null) {
                bit.update(x, x - prev);
            }
            prev = x;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];

            if (q[0] == 1) {
                int x = q[1];
                Integer l = set.lower(x);
                Integer r = set.higher(x);

                if (r != null) {
                    bit.update(r, r - l);
                }

                set.remove(x);
            } else {
                int x = q[1];
                int sz = q[2];

                int p = set.floor(x);
                ans.add(bit.query(p) >= sz || x - p >= sz);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}