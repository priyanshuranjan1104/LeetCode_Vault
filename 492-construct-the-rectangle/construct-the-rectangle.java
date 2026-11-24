class Solution {
    public int[] constructRectangle(int area) {
         int[] ans = new int[2];
        for (int i = (int)(Math.sqrt(area)); i >= 1 ; i--) {
            if (area % i == 0) {
                ans[1] = i;
                ans[0] = area / i;
                break;
            }
        }
        return ans;
    }
}