class Solution {
    public int lengthLongestPath(String input) {
        int res = 0;
        List<Integer> list = new ArrayList<>();
        String[] path = input.split("\n");
        for (String s : path) {
            if (s.isEmpty()) continue;
            int level = 0;
            while (s.charAt(level) == '\t') level++;
            if (list.size() <= level) {
                list.add(s.substring(level).length());
            } else {
                list.set(level, s.substring(level).length()); 
            }
            if (s.indexOf('.') != -1) {
                int sum = 0;
                for (int i = 0; i <= level; i++) {
                    sum += list.get(i);
                }
                res = Math.max(res, sum + level);  
            }            
        }
        return res;
    }
}