class Solution {
    public boolean validUtf8(int[] data) {
        int remaining = 0;

        for (int x : data) {
            if (x < 0 || x > 255) return false;

            if (remaining == 0) {
                if ((x & 0x80) == 0) continue;
                if ((x & 0xE0) == 0xC0) remaining = 1;
                else if ((x & 0xF0) == 0xE0) remaining = 2;
                else if ((x & 0xF8) == 0xF0) remaining = 3;
                else return false;
            } else {
                if ((x & 0xC0) != 0x80) return false;
                remaining--;
            }
        }

        return remaining == 0;
    }
}