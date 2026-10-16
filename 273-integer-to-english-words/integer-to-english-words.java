class Solution {
    String[] ones = {
        "Zero",
        "One",
        "Two", 
        "Three", 
        "Four", 
        "Five", 
        "Six", 
        "Seven", 
        "Eight", 
        "Nine", 
        "Ten", 
        "Eleven", 
        "Twelve", 
        "Thirteen", 
        "Fourteen", 
        "Fifteen", 
        "Sixteen", 
        "Seventeen", 
        "Eighteen", 
        "Nineteen"
    };
    
    String[] tens = {
        "Zero",
        "Ten",
        "Twenty",
        "Thirty", 
        "Forty", 
        "Fifty", 
        "Sixty", 
        "Seventy", 
        "Eighty", 
        "Ninety"
    };
    
    String[] magnitude = {
        "",
        "Thousand",
        "Million",
        "Billion",
        "Trillion"
    };
    
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        int n = num;
        String ans = "";
        int count = 0;
        while(n > 0)
        {
            int rem = n % 1000;
            if(rem == 0) ans = ans;
            else ans = this.helper3Digit(rem) + " " + this.magnitude[count] + " " + ans;
            
            n=(int)n/1000;
            count++;
        }
        return ans.strip();
    }
    
    String helper3Digit(int n)
    {
        int num = n;
        String ans = "";
        int count = 0;
        int rem = 0, prevRem = 0;
        
        while(num > 0)
        {
            count++;
            rem = num % 10;
            num = (int)(num / 10);
            
            if(rem == 0) continue;
            
            else if(count == 1)
            {
                ans += this.ones[rem];
            }
            
            else if(count == 2)
            {
                if(rem == 1 || rem == 0) 
                {
                    ans = this.ones[rem*10 + prevRem];
                }
                else
                {
                    ans = this.tens[rem] + " " + ans;
                }
            }
            
            else if(count == 3)
            {
                ans = this.ones[rem] + " Hundred " + ans;
            }
            prevRem = rem;
        }
        return ans.strip();
    }
}