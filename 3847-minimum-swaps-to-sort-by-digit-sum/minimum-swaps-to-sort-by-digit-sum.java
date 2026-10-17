class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            int digitSum = 0;

            while (curr > 0) {
                digitSum += curr % 10;
                curr /= 10;
            }

            arr[i][0] = digitSum;
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }

            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(a[2], b[2]);
        });

        int[] perm = new int[n];

        for (int i = 0; i < n; i++) {
            perm[i] = arr[i][2];
        }

        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            cycles++;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = perm[j];
            }
        }

        return n - cycles;
    }
}