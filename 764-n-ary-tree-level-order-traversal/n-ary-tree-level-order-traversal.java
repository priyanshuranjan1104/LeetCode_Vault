/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        queue.offer(root);
        while(queue.size() > 0)
        {
            List<Integer> arr = new ArrayList<>();
            int length = queue.size();
            for(int i = 0;i<length;i++)
            {
                Node curr = queue.poll();
                arr.add(curr.val);
                for(Node child : curr.children)
                {
                    queue.offer(child);
                }
            }
            result.add(arr);
        }
        return result;
    }
}