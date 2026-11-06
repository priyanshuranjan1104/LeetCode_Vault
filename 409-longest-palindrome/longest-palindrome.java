class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int len = 0;
        for (int i : map.values()) {
            len += (i % 2 == 0)? i : i - 1;
        }

        return len < s.length() ? len + 1 : len;
    }
}

