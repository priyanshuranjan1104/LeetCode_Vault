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
    int maxF=0;
    List<Integer> maxSumArr = new ArrayList<>();
    Map<Integer, Integer> f = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        solve(root);

        int[] arr = new int[maxSumArr.size()];
        for(int i=0; i<arr.length; i++){
            arr[i] = maxSumArr.get(i);
        }
        return arr;
    }

    public int solve(TreeNode root){
        if(root == null) return 0;
        
        int sum = root.val + solve(root.left) + solve(root.right);
        f.put(sum , f.getOrDefault(sum, 0) + 1);
        
        if(f.get(sum) > maxF){
            maxSumArr.clear();
            maxSumArr.add(sum);
            maxF = f.get(sum);
        }else if(f.get(sum) == maxF){
            maxSumArr.add(sum);
        }

        return sum;
    }
}