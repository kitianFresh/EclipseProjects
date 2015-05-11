package Algorithms;

public class EditDistanceComputer {
	private int sWeight = 1;		//�滻����substitute��Ȩֵ��Ҳ���Ǵ���overhead
	private int iWeight = 1;		//�������insert��Ȩֵ
	private int dWeight = 1;		//ɾ������delete��Ȩֵ
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
		//����(m+1)*(n+1)����ռ�
		int[][] distance = new int[m+1][n+1];
		//��ʼ������ֵ
		for(int i=0;i<m+1;i++){
			distance[i][0] = i;
		}
		for(int i=0;i<n+1;i++){
			distance[0][i] = i;
		}
		//���õ��ƹ�ʽ������������������
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				distance[i][j] = getMin(distance[i-1][j]+dWeight, distance[i][j-1]+iWeight, distance[i-1][j-1]+(s.charAt(i-1)==t.charAt(j-1)?0:sWeight));
			}
		}
		
		printMatrix(distance,m+1,n+1);
		
		return distance[m][n];
	}
	//��ӡ����
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
