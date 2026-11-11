class Solution {
    public int numberOfArithmeticSlices(int[] A) {
    int n = A.length;

    HashMap<Pair<Long, Pair<Integer, Integer>>, Integer> memo = new HashMap<>();
    int ans = 0;
    for(int i=0; i<n; i++){
        for(int j=i+1; j<n; j++){
            long diff = (long) A[j] - (long) A[i];
            
            ans += helper(A, diff, j+1, j, memo);
        }
    }
    
    return ans;
}

public int helper(int [] A, long diff, int i, int prev, HashMap<Pair<Long, Pair<Integer, Integer>>, Integer> memo){
    if(i >= A.length || diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE){
        return 0;
    }
    
    Pair<Long, Pair<Integer, Integer>> key = new Pair(diff, new Pair(i, prev));
    
    if(memo.get(key) != null){
        return memo.get(key);
    }
        
    int ans = 0;
    for(int j=i; j<A.length; j++){
        long currentDiff = (long)A[j] - (long)A[prev];
        if(currentDiff == diff){
            ans += 1 + helper(A, diff, j+1, j, memo);    
        } 
    }
    
    memo.put(key, ans);
    
    return ans;
}
}