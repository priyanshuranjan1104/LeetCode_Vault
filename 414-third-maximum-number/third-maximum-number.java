class Solution {
    public int thirdMax(int[] nums) {
        if(nums.length  == 2){
            return Math.max(nums[0],nums[1]);
        }
        HashSet<Integer> set = new HashSet<>();
        for(int x : nums){
            set.add(x);
        }
        int idx = 0;
        int[] arr = new int[set.size()];
        for(int x : set){
            arr[idx++] = x;
        }

        if(arr.length  == 2){
            return Math.max(arr[0],arr[1]);
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for(int i = 0; i<arr.length;i++){
            if(arr[i] > first){
                third = second;
                second = first;
                first = arr[i];
                continue;
            }

            if(arr[i] > second){
                third = second;
                second = arr[i];
                continue;
            }

            if(arr[i] > third){
                third = arr[i];
            }
        }

        if(arr.length == 1){
            return arr[0];
        }

        if(third == Integer.MIN_VALUE && arr[0] != Integer.MIN_VALUE && arr[1] != Integer.MIN_VALUE){
            if(second == Integer.MIN_VALUE){
                return first;
            }

            return second;
        }

        return third;
    }
}