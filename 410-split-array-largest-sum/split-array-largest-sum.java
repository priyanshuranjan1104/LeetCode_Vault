class Solution {
    public int splitArray( int[ ] nums , int  k )  {
        int low = 0 , high = 0 ;
        for( int  i : nums ){
            high += i ;
            low = Math.max( i , low ) ;
        }
        return find( low , high , nums , k ) ;
    }
    public int find( int l , int h , int n [ ] , int k ) {
        if( l >h ) return l;
        int c = 1 , m = l + ( h - l ) / 2 , sum = 0 ;
        for(int i : n ) {
            if( i + sum > m ){
                sum = i ;
                c ++ ;
            }
            else    sum += i ;
        }
        if(c <= k ) return find( l , m - 1 , n , k ) ;
        return find( m + 1 , h , n , k ) ;
    }
}