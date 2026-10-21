class Solution {
    public String removeDuplicateLetters(String s) {

        int[] frequency = new int[26];

        boolean[] included = new boolean[26];

        for (char ch : s.toCharArray()) {
            frequency[ch - 'a']++;
        }

        StringBuilder result = new StringBuilder();

        for (char ch : s.toCharArray()) {
            frequency[ch - 'a']--;

            if (included[ch - 'a']) {
                continue;
            }

            while (
                result.length() > 0
                && result.charAt(result.length() - 1) > ch
                && frequency[
                    result.charAt(result.length() - 1) - 'a'
                ] > 0
            ) {
                char removed =
                    result.charAt(result.length() - 1);

                result.deleteCharAt(result.length() - 1);

                included[removed - 'a'] = false;
            }
            result.append(ch);
            included[ch - 'a'] = true;
        }

        return result.toString();
    }
}