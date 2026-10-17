/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsA(root, sb);
        return sb.toString();
    }

    public void dfsA(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append("N,");
            return;
        }

        sb.append(node.val).append(",");

        dfsA(node.left, sb);
        dfsA(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int[] idx = new int[1];
        String[] arr = data.split(",");
        return dfsB(arr, idx);
    }

    public TreeNode dfsB(String[] arr,int[] idx){
        if(arr[idx[0]].equals("N")){
            idx[0]++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(arr[idx[0]]));
        idx[0]++;

        node.left = dfsB(arr, idx);
        node.right = dfsB(arr, idx);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));