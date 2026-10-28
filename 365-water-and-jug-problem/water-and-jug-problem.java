class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        if (x + y < target) {
            return false;
        }

        if (x == 0) {
            return target == y || target == 0;
        }
        if (y == 0) {
            return target == x || target == 0;
        }

        while (x != 0) {
            int temp = x;
            x = y % x;
            y = temp;
        }

        return target % y == 0;
    }
}