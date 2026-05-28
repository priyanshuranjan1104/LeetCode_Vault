class Solution {
    static class TreeNode {
        TreeNode[] children = new TreeNode[26];
        int idx;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int best = 0;

        for (int i = 1; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[best].length()) {
                best = i;
            }
        }

        TreeNode root = new TreeNode();
        root.idx = best;

        for (int i = 0; i < wordsContainer.length; i++) {
            String s = wordsContainer[i];
            TreeNode node = root;

            for (int j = s.length() - 1; j >= 0; j--) {
                int c = s.charAt(j) - 'a';

                if (node.children[c] == null) {
                    node.children[c] = new TreeNode();
                    node.children[c].idx = i;
                }

                node = node.children[c];

                int len1 = wordsContainer[node.idx].length();
                int len2 = s.length();

                if (len2 < len1 || (len2 == len1 && i < node.idx)) {
                    node.idx = i;
                }
            }
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            String s = wordsQuery[i];
            TreeNode node = root;

            for (int j = s.length() - 1; j >= 0; j--) {
                int c = s.charAt(j) - 'a';

                if (node.children[c] == null) {
                    break;
                }

                node = node.children[c];
            }

            ans[i] = node.idx;
        }

        return ans;
    }
}