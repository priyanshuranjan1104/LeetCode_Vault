class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) 
    {
        List<Pair> list = new ArrayList<>();
        
        for(int i=0;i<buildings.length;i++){
            int sp = buildings[i][0];      
            int ep = buildings[i][1];      
            int ht = buildings[i][2];       
            
            list.add(new Pair (sp, -ht)); 
            list.add(new Pair (ep, ht)); 
        }
        
        Collections.sort(list);    
        List<List<Integer>> res= new ArrayList<>();   

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());     
        
        int currheight =0;
        pq.add(0);     
        
        for(int i=0;i<list.size();i++)
        {     
            int x= list.get(i).x;
            int ht = list.get(i).ht;
            
            if(ht<0)
            {              
                pq.add(-ht);   
            }
            else
            {
                pq.remove(ht);   
            }
            
            if(currheight != pq.peek())
            {    
                List<Integer> temp = new ArrayList<>(); 
                
                temp.add(x);           
                temp.add(pq.peek());   
                
                res.add(temp);          
                currheight = pq.peek();    
            }
            
        }
        
        return res;   
        
    }
    
    public class Pair implements Comparable<Pair>
    {
        int x;
        int ht;
        
        public Pair(int x, int ht)
        {
            this.x=x;
            this.ht=ht;
        }
        
        public int compareTo(Pair o)
        {
            if(this.x != o.x)
            {
                return this.x -o.x;
            }
            else
            {
                return this.ht - o.ht;
            }
        }
    }
}