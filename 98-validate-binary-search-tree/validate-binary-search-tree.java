/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return check(root, null, null);
    }

    public boolean check(TreeNode node, Long min, Long max) {
        if (node == null) return true;

        long val = node.val;

        if ((min != null && val <= min) || (max != null && val >= max)) {
            return false;
        }

        return check(node.left, min, val) && check(node.right, val, max);
    }
}