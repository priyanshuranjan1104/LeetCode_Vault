class SummaryRanges {
    public final TreeSet<Integer> set = new TreeSet<>();

    public SummaryRanges() {}

    public void addNum(int value) {
        set.add(value);  
    }

    public int[][] getIntervals() {
        if (set.isEmpty()) 
            return new int[0][0];

        List<int[]> res = new ArrayList<>();
        Iterator<Integer> it = set.iterator();

        int start = it.next(), end = start;

        while (it.hasNext()) { 
            int x = it.next();
            if (x == end + 1)
                end = x;
            else {
                res.add(new int[]{start, end});
                start = end = x;
            }
        }
        res.add(new int[]{start, end});

        return res.toArray(new int[res.size()][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */