class Solution {
    public int firstUniqChar(String s) {

        HashMap<Character, Integer> count = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(count.get(ch) == 1) return i;
        }

        return -1;
    }
}