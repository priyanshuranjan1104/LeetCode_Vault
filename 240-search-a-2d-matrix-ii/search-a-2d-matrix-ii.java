class Solution {
    public boolean searchMatrix(int[][] matrix, int k) {
        int row = 0,col = matrix[0].length - 1;
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] == k){
                return true;
            }
            else if(matrix[row][col] < k){
                row++;
            }
            else{
                col--;
            }
        }
        return false;
    }
}