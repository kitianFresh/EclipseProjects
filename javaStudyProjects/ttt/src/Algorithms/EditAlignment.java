package Algorithms;
enum TraceOperator {L,D,S}; //L:LEFT D:DOWN S:SLANT
public class EditAlignment {
	private int sWeight = 1;		//替换操作substitute的权值，也就是代价overhead
	private int iWeight = 1;		//插入操作insert的权值
	private int dWeight = 1;		//删除操作delete的权值
	private int m = 0;
	private int n = 0;
	int[][] distance = null;
	TraceOperator[][] backtrace = null;
	StringBuffer sb = null;
	public static void main(String[] args){
		String s = "AGGCTATCACCTGACCTCCAGGCCGATGCCC";
		String t = "TAGCTATCACGACCGCGGTCGATTTGCCCGAC";
		EditAlignment editDC = new EditAlignment();
		System.out.println(editDC.getMinEditDistance(s, t));
		editDC.Alignment(s, t);
	}
	
	public void setWeight(int sWeight, int iWeight, int dWeight){
		this.sWeight = sWeight;
		this.iWeight = iWeight;
		this.dWeight = dWeight; 
	}
	
	public void Alignment(final String s, final String t){
		sb = new StringBuffer(s);
		System.out.println("SourceString StringBuffer before Alignment: " + sb);
		if(backtrace == null || distance == null) System.exit(-1);
		int i = m;
		int j = n;
		while(backtrace[i][j] != null){
			switch(backtrace[i][j]){
				case S:
					if(s.charAt(i-1)!=t.charAt(j-1)){
						sb.replace(i-1, i, ""+t.charAt(j-1));
						System.out.println("source string: " + sb);
						System.out.println("target string: " + t);
						System.out.println("---------------------------------------");
					}
					i--;j--;
					break;
				case L:
					sb.insert(i, t.charAt(j-1));
					j--;	
					System.out.println("source string: " + sb);
					System.out.println("target string: " + t);
					System.out.println("---------------------------------------");
					break;
				case D:
					sb.deleteCharAt(i-1);
					i--;
					System.out.println("source string: " + sb);
					System.out.println("target string: " + t);
					System.out.println("---------------------------------------");
					break;
				default:
					System.exit(-1);
			}
		}
		System.out.println("SourceString StringBuffer after Alignment: " + sb);
	}
	
	public int getMinEditDistance(final String s, final String t){
		m = s.length();                     //看成二维矩阵的话，m对应行，也就是纵坐标，n对应列，也就是横坐标
		n = t.length();
		int a,b,c;
		distance = new int[m+1][n+1];
		backtrace = new TraceOperator[m+1][n+1];
		initMatrix(m+1, n+1);
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				a = distance[i-1][j]+dWeight;	//deletion对于s的操作，以下都是以s为源串
				b = distance[i][j-1]+iWeight;	//insertion
				c = distance[i-1][j-1]+(s.charAt(i-1)==t.charAt(j-1)?0:sWeight);//substitution
				if(a == getMin(a,b,c)){
					distance[i][j] = a;
					backtrace[i][j]=TraceOperator.D;//deletion
				}
				else if(b == getMin(a,b,c)){
					distance[i][j] = b;
					backtrace[i][j]=TraceOperator.L;//insertiodn
				}
				else if(c == getMin(a,b,c)){
					distance[i][j] = c;
					backtrace[i][j]=TraceOperator.S;//substitution
				}
			}
		}
		
		printMatrix(distance,m+1,n+1);
		System.out.println();
		printMatrix(backtrace,m+1,n+1);
		
		return distance[m][n];
	}
	
	public void printMatrix(int[][] matrix, int rownum, int colnum){
		for(int i=rownum-1;i>=0;i--){
			for(int j=0;j<colnum;j++){
				System.out.print(matrix[i][j]+"	");
			}
			System.out.println();
		}
	}
	
	public void printMatrix(TraceOperator[][] matrix, int rownum, int colnum){
		for(int i=rownum-1;i>=0;i--){
			for(int j=0;j<colnum;j++){
				System.out.print(matrix[i][j]+"	");
			}
			System.out.println();
		}
	}
	
	private void initMatrix(int x, int y){
		for(int i=0;i<x;i++){
			distance[i][0] = i;
		}
		for(int i=0;i<y;i++){
			distance[0][i] = i;
		}
		
		for(int i=1;i<x;i++){
			backtrace[i][0] = TraceOperator.D ;
		}
		for(int i=1;i<y;i++){
			backtrace[0][i] = TraceOperator.L;
		}
	}
	
	private int getMin(int a, int b, int c){
		return (a<b)?(a<c?a:c):(b<c?b:c);
	}
}
