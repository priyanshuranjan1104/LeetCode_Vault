class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int len = 0, sum = 0, prev = -2;

        for (int i = 0, n = p.length(); i < n; i++) {
            int c = p.charAt(i) - 'a';
            
            len = (c == prev + 1 || prev == 25 && c == 0) ? len + 1 : 1;
            if (len > count[c]) {
                sum += len - count[c];
                count[c] = len;
            }
            prev = c;
        }
        return sum;
    }
}