class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    public int reversePairs(int[] nums , int left , int mid , int right) {   
        int count = 0;
        int pointer = mid+1;
        for(int i = left ; i < mid+1 ; i ++){
            while(pointer <= right && nums[i] > 2*(long)nums[pointer]){
                pointer++;
            }
            count+= pointer-(mid+1);
        }
        
        return count;
    }

    public int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = (left + right) / 2;
        int leftPairs = mergeSort(nums, left, mid);
        int rightPairs = mergeSort(nums, mid + 1, right);
        int crossPairs = reversePairs(nums , left , mid , right);

        merge(nums, left, mid, right);

        return leftPairs+rightPairs+crossPairs;
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= right) {
            temp[k++] = nums[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            nums[left + x] = temp[x];
        }
    }
}