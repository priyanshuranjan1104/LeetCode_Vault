class Solution {
    static void generate(String s,List<String> wordDict,List<String> res,StringBuilder curr,int i){
        if(i>=s.length()){
            res.add(curr.toString().trim());
            return;
        }
        for(String word:wordDict){
            if(s.startsWith(word,i)){
                int l=curr.length();
                curr.append(word).append(" ");
                generate(s,wordDict,res,curr,i+word.length());
                curr.setLength(l);
            }
        }
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res=new ArrayList<>();
        generate(s,wordDict,res,new StringBuilder(),0);
        return res;
    }
}