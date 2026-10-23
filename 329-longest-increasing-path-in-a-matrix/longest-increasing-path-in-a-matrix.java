class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int n=matrix.length,m=matrix[0].length;
        int[][]visited=new int[n][m];
        for(int[]row:visited) Arrays.fill(row,-1);
        int max=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                max=Math.max(max,dfs(matrix,visited,i,j,n,m));
            }
        }
        return max;
    }
    public static int dfs(int[][]arr,int[][]visited,int row,int col,int n,int m){
        if(visited[row][col]!=-1)return visited[row][col];
        int[]allowedRow={0,-1,1,0};
        int[]allowedCol={-1,0,0,1};
        int max=1;
        for(int i=0;i<4;i++){
            int newRow=row+allowedRow[i];
            int newCol=col+allowedCol[i];
            if(newRow>=0&&newRow<n&&newCol>=0&&newCol<m&&arr[row][col]<arr[newRow][newCol]){
               max=Math.max(max,1+dfs(arr,visited,newRow,newCol,n,m));
            }
        }
        visited[row][col]=max;
        return max;
    }
}