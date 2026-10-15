class Solution {
    public int[] singleNumber(int[] nums) {
    int xor = 0;
    for (int num : nums) {
        xor ^= num;
    }
    
    int mask = 1;
    while ((xor & mask) == 0) {
        mask <<= 1;
    }
    
    int num1 = 0;
    int num2 = 0;
    
    for (int num : nums) {
        if ((num & mask) == 0) {
            num1 ^= num;
        } else {
            num2 ^= num;
        }
    }
    
    return new int[]{num1, num2};
}
}