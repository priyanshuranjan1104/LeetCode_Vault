class Solution {
    public boolean isSelfCrossing(int[] x) {
        int a1, a2, a3, a4, a5;

        boolean up = false;
        
        if (x.length < 4) {
            return false;
        }
        
        a1 = 0;
        a2 = x[0];
        a3 = x[1];
        a4 = x[2];
        
        if (a2 < a4) {
            up = true;
        }
        else {
            up = false;
        }
        
        for (int i = 3; i < x.length; i++) {
            a5 = x[i];
            
            if (!up && a5 >= a3) {
                return true;
            }
            else if (up && a5 <= a3) {
                if (a5 + a1 < a3 || (i + 1 < x.length && x[i + 1] + a2 < a4)) {
                    up = false;
                }
                else if (i + 1 < x.length) {
                    return true;
                }
            }
            
            a1 = a2;
            a2 = a3;
            a3 = a4;
            a4 = a5;
        }
        
        return false;
    }
}