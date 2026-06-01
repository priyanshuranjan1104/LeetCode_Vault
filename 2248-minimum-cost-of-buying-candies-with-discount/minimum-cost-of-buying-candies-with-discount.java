class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int ans = 0;
        int cnt = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            cnt++;
            if (cnt == 3) {
                cnt = 0;
                continue;
            }
            ans += cost[i];
        }

        return ans;
    }
}