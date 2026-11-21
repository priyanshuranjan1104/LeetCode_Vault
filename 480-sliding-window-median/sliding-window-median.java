class Solution {
    class Num implements Comparable<Num> {
        
        int val;
        int index;
        
        Num(int val, int index) {
            
            this.val = val;
            this.index = index;
        }
        
        public void update(int val, int index) {
            this.val = val;
            this.index = index;
        }
        
        @Override
        public int compareTo(Num num) {
		    
            if (num.val == this.val) return this.index - num.index;
            
            return Integer.compare(this.val, num.val);
	    } 
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
                
        double[] result = new double[nums.length-k+1];
                
        TreeSet<Num> minSet = new TreeSet<>();
        TreeSet<Num> maxSet = new TreeSet<>();
        
        Num[] window = new Num[k];
        
        for (int i = 0; i < k; i++) {
            
            window[i] = new Num(nums[i], i);
        }
        
        for (int i = 0; i < k/2; i++) {
            
            maxSet.add(window[i]);
        }
        
        for (int i = k/2; i < k; i++) {
            
            if (maxSet.size() == 0) {
                
                maxSet.add(window[i]);
            } else if (nums[i] < maxSet.first().val) {
                
                minSet.add(window[i]);
            } else {
                
                minSet.add(maxSet.pollFirst());
                maxSet.add(window[i]);
            }
        }
       
        int j = 0;
        
        result[j++] = getMedian(minSet, maxSet, k);
        
        for (int i = k; i < nums.length; i++) {
            
            Num last = window[i % k];
            
            if (minSet.size() > 0 && last.val <= minSet.last().val) {
                
                minSet.remove(last);
                
                last.update(nums[i], i);
                
                if (maxSet.size() == 0) {
                    
                    maxSet.add(last);
                } else if (nums[i] < maxSet.first().val) {

                    minSet.add(last);
                } else {

                    minSet.add(maxSet.pollFirst());
                    maxSet.add(last);
                }
            } else {
                
                maxSet.remove(last);
                
                last.update(nums[i], i);
                
                if (minSet.size() == 0) {
                    
                    minSet.add(last);
                } else if (nums[i] > minSet.last().val) {

                    maxSet.add(last);
                } else {

                    maxSet.add(minSet.pollLast());
                    minSet.add(last);
                }
            }
            
            result[j++] = getMedian(minSet, maxSet, k);
        }
        
        return result;
    }
    
    public double getMedian(TreeSet<Num> minSet, TreeSet<Num> maxSet, int k) {
        
        if (k % 2 == 0) {
            
            return ((double)minSet.last().val + (double)maxSet.first().val) / 2;
        }
        
        return minSet.size() > maxSet.size() ? minSet.last().val : 
        maxSet.first().val;
    }
}