class Solution {
    public static final int MOD = 1000000007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;

        int depth = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;

            while (size-- > 0) {
                int node = queue.poll();

                for (int next : graph[node]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        long ans = 1;
        long base = 2;
        int power = depth - 1;

        while (power > 0) {
            if ((power & 1) == 1) {
                ans = (ans * base) % MOD;
            }

            base = (base * base) % MOD;
            power >>= 1;
        }

        return (int) ans;
    }
}