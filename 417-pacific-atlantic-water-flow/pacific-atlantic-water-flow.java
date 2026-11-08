class Solution {
    void dfs(int i,int j,int heights[][],boolean ocean[][],int ph){
        if (i < 0 || i >= heights.length || j < 0 || j >= heights[0].length) return;
        if(ocean[i][j] || heights[i][j]<ph) return;

        ocean[i][j]=true;
        dfs(i+1,j,heights,ocean,heights[i][j]);
        dfs(i-1,j,heights,ocean,heights[i][j]);
        dfs(i,j-1,heights,ocean,heights[i][j]);
        dfs(i,j+1,heights,ocean,heights[i][j]);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> list=new ArrayList<>();
        if(heights==null || heights.length==0)return list;

        int m=heights.length;
        int n=heights[0].length;

        boolean pac[][]=new boolean[m][n];
        boolean atl[][]=new boolean[m][n];

        for(int j=0;j<n;j++){
            dfs(0,j,heights,pac,heights[0][j]);
            dfs(m-1,j,heights,atl,heights[m-1][j]);
        }

        for(int i=0;i<m;i++){
            dfs(i,0,heights,pac,heights[i][0]);
            dfs(i,n-1,heights,atl,heights[i][n-1]);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(pac[i][j] && atl[i][j])list.add(List.of(i,j));
            }
        }

        return list;
    }
}