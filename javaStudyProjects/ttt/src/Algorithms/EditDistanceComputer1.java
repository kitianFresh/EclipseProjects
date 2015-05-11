package Algorithms;

public class EditDistanceComputer1 {
	private int sWeight = 1;		//�滻����substitute��Ȩֵ��Ҳ���Ǵ���overhead
	private int iWeight = 1;		//�������insert��Ȩֵ
	private int dWeight = 1;		//ɾ������delete��Ȩֵ
	public static void main(String[] args){
		String s = "GUMBO";
		String t = "GAMBOL";
		EditDistanceComputer1 editDC = new EditDistanceComputer1();
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
		int[] cur_row = new int[n+1];
		int[] pre_row = new int[n+1];
		int[] temp = null;
		for(int i=0;i<n+1;i++){
			pre_row[i] = i;
		}
		
		
		for(int i=1;i<=m;i++){
			cur_row[0] = i;
			for(int j=1;j<=n;j++){
				cur_row[j] = getMin(pre_row[j]+dWeight, cur_row[j-1]+iWeight, pre_row[j-1]+(s.charAt(i-1)==t.charAt(j-1)?0:sWeight));
			}
			
			printVector(cur_row,n+1);
			printVector(pre_row,n+1);
			System.out.println();
			//������ǰ�к���ǰ�У�Ϊ������һ�ֵ�����׼�����ڳ�pre_row��λ��
			temp = cur_row;
			cur_row = pre_row;
			pre_row = temp;
		}
		
		return pre_row[n];
	}
	
	public void printVector(int[] vector,int colnum){
		
			for(int j=0;j<colnum;j++){
				System.out.print(vector[j]+"	");
			}
			System.out.println();
	}
	
	private int getMin(int a, int b, int c){
		return (a<b)?(a<c?a:c):(b<c?b:c);
	}
}
