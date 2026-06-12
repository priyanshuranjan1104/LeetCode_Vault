class Solution {
    public static final int MOD = 1_000_000_007;
    public static final int LOG = 18;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        int[] degree = new int[n + 1];
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        int[][] graph = new int[n + 1][];
        for (int i = 1; i <= n; i++) {
            graph[i] = new int[degree[i]];
        }

        int[] idx = new int[n + 1];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u][idx[u]++] = v;
            graph[v][idx[v]++] = u;
        }

        int[][] parent = new int[LOG][n + 1];
        int[] depth = new int[n + 1];

        int[] queue = new int[n];
        int front = 0, rear = 0;
        queue[rear++] = 1;
        parent[0][1] = 0;

        while (front < rear) {
            int u = queue[front++];

            for (int v : graph[u]) {
                if (v == parent[0][u]) {
                    continue;
                }

                parent[0][v] = u;
                depth[v] = depth[u] + 1;
                queue[rear++] = v;
            }
        }

        for (int i = 1; i < LOG; i++) {
            for (int j = 1; j <= n; j++) {
                int p = parent[i - 1][j];
                parent[i][j] = p == 0 ? 0 : parent[i - 1][p];
            }
        }

        int maxDepth = 0;
        for (int d : depth) {
            maxDepth = Math.max(maxDepth, d);
        }

      long[] pow = new long[n];
pow[0] = 1;
for (int i = 1; i < n; i++) {
    pow[i] = (pow[i - 1] * 2) % MOD;
}

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int lca = getLCA(u, v, parent, depth);
            int len = depth[u] + depth[v] - 2 * depth[lca];

            ans[i] = len == 0 ? 0 : (int) pow[len - 1];
        }

        return ans;
    }

    public int getLCA(int u, int v, int[][] parent, int[] depth) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int i = 0; i < LOG; i++) {
            if (((diff >> i) & 1) == 1) {
                u = parent[i][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[i][u] != parent[i][v]) {
                u = parent[i][u];
                v = parent[i][v];
            }
        }

        return parent[0][u];
    }
}