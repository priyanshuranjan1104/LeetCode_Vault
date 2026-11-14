class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;

            int slow = i, fast = i;
            boolean forward = nums[i] > 0;

            while (true) {
                int nextSlow = nextIndex(nums, slow);
                int nextFast1 = nextIndex(nums, fast);
                if ((nums[nextFast1] > 0) != forward) break;

                int nextFast2 = nextIndex(nums, nextFast1);
                if ((nums[nextFast2] > 0) != forward) break;
                fast = nextFast2;

                if ((nums[nextSlow] > 0) != forward ||
                    (nums[nextFast2] > 0) != forward) {
                    break;
                }

                if (nextSlow == slow || nextFast1 == fast || nextFast2 == nextFast1) break;
                    
                slow = nextSlow;
                fast = nextFast2;

                if (slow == fast) {
                    return true; 
                }
            }

            int index = i;
            while (nums[index] != 0 && (nums[index] > 0) == forward) {
                int next = nextIndex(nums, index);
                nums[index] = 0;
                if (next == index) break;
                index = next;
            }

        }

        return false;
    }

    public int nextIndex(int[] nums, int i) {
        int n = nums.length;
        return ((i + nums[i]) % n + n) % n; 
    }
}