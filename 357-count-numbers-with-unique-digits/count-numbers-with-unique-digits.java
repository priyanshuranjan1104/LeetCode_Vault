class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int c=1;
        int b=0;
        int a=9;
        for(int i=0;i<n;i++){
            if(i==0){
                c=9;
            }
            else{
                c*=a;
                a--;
            }
            b+=c;
        }
        return b+1;
    }
}