class Solution {
    public int myAtoi(String s) {
        int ans=0;
        int i=0;
        int sign=1;
        int max=Integer.MAX_VALUE;
        int min=Integer.MIN_VALUE;
        if(s.length()==0){
            return 0;
        }
        while(i<s.length() && s.charAt(i)==' '){
            i++;
        }

    if(i<s.length() && (s.charAt(i)=='-'|| s.charAt(i)=='+')){
        if(s.charAt(i)=='-'){
            sign=-1;
        }
        i++;
    }
        while(i<s.length() && s.charAt(i)-'0'<=9 && s.charAt(i)-'0'>=0){
            int digit = s.charAt(i)-'0';
            if(ans>max/10 || (ans==max/10 && digit>max%10)){
                return (sign == 1) ? max : min;            
            }
            ans=ans*10+digit;
            i++;
        }
        return ans*sign;
    }
}