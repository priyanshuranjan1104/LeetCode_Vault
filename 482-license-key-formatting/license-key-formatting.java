class Solution {
    public String licenseKeyFormatting(String s, int k) {
        char[] arr = s.toCharArray();
        int count = 0, l = arr.length;
        for(int i = 0; i < l; i++) {
            if(arr[i] != '-') {
                count++;
            }
        }

        if(count == 0) {
            return "";
        }

        int len = count + (count / k) + (count % k == 0 ? -1 : 0);
        char[] mem = new char[len];
        int dash = k;

        for(int i = l - 1; i >= 0; i--) {
            if(arr[i] == '-') {
                continue;
            }
            if(dash == 0) {
                dash = k;
                mem[--len] = '-';
            }
            if(arr[i] > 90) {
                mem[--len] = (char)(arr[i] - 32);
            } else {
                mem[--len] = arr[i];
            }
            dash--;
        }

        return new String(mem); 
    }
}