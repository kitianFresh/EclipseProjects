package Algorithms;

public class EditDistanceComputer {
	private int sWeight = 1;		//替换操作substitute的权值，也就是代价overhead
	private int iWeight = 1;		//插入操作insert的权值
	private int dWeight = 1;		//删除操作delete的权值
	public static void main(String[] args){
		String s = "intention";
		String t = "execution";
		EditDistanceComputer editDC = new EditDistanceComputer();
		System.out.println(editDC.getMinEditDistance(s, t));
	}
	
	public void setWeight(int sWeight, int iWeight, int dWeight){
		this.sWeight = sWeight;
		this.iWeight = iWeight;
		this.dWeight = dWeight; 
	}
	
	public int getMinEditDistance(String s, String t){
		int m = s.length();
		int n = t.length();
		//申请(m+1)*(n+1)矩阵空间
		int[][] distance = new int[m+1][n+1];
		//初始化特殊值
		for(int i=0;i<m+1;i++){
			distance[i][0] = i;
		}
		for(int i=0;i<n+1;i++){
			distance[0][i] = i;
		}
		//利用递推公式遍历填充整个距离矩阵
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				distance[i][j] = getMin(distance[i-1][j]+dWeight, distance[i][j-1]+iWeight, distance[i-1][j-1]+(s.charAt(i-1)==t.charAt(j-1)?0:sWeight));
			}
		}
		
		printMatrix(distance,m+1,n+1);
		
		return distance[m][n];
	}
	//打印矩阵
	public void printMatrix(int[][] matrix, int rownum, int colnum){
		for(int i=rownum-1;i>=0;i--){
			for(int j=0;j<colnum;j++){
				System.out.print(matrix[i][j]+"	");
			}
			System.out.println();
		}
	}
	
	private int getMin(int a, int b, int c){
		return (a<b)?(a<c?a:c):(b<c?b:c);
	}
}
