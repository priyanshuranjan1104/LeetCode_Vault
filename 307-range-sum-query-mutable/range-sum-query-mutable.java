class SegmentTreeNode{
    int low;
    int high;
    SegmentTreeNode left;
    SegmentTreeNode right;
    int sum;
    public SegmentTreeNode(int low,int high){
        this.low=low;
        this.high=high;
        this.left=null;
        this.right=null;
        this.sum=0;
    }
}

class NumArray {
    SegmentTreeNode root=null;
    public NumArray(int[] nums) {
        root=buildTree(nums,0,nums.length-1);
    }
    public SegmentTreeNode buildTree(int []nums,int low,int high){
        if(low>high)return null;
        SegmentTreeNode temp = new SegmentTreeNode(low,high);
        if(low==high){
            temp.sum=nums[low];
        }
        else{
            int mid = (low+high)/2;
            temp.left= buildTree(nums,low,mid);
            temp.right =buildTree(nums,mid+1,high);
            temp.sum=temp.left.sum+temp.right.sum;
            }
        return temp;
    }
    
    public void update(int index, int val) {
        update(root,index,val);        
    }
    public void update(SegmentTreeNode root, int ind,int val){
        if(root.low==root.high){
            root.sum=val;
        }
        else{
            int mid = (root.low+root.high)/2;
            if(ind<=mid) update(root.left,ind,val);
            else update(root.right,ind,val);
            root.sum = root.left.sum+root.right.sum;
        }
       
    }
    
    public int sumRange(int left, int right) {
        return sumrange(root,left,right);
    }
    public int sumrange(SegmentTreeNode root, int low,int high){
        if(low==root.low&&high==root.high){
            return root.sum;
        }
        else{
            int mid = (root.low+root.high)/2;
            if(high<=mid) return sumrange(root.left,low,high);
            else if (low>=mid+1) return sumrange(root.right,low,high);
            else return sumrange(root.left,low,mid)+sumrange(root.right,mid+1,high);
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */