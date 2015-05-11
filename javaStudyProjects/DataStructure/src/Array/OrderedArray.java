package Array;

public class OrderedArray {
	private long[] arr = null;
	private int nElems = 0;
	
	public OrderedArray(int max){
		arr = new long[max];
	}
	
	//返回searchKey的索引
	public int find(long searchKey){
		int lowIndex = 0;
		int highIndex = nElems - 1;
		int cur;
		while(lowIndex<=highIndex){
			cur = (lowIndex + highIndex)/2;
			if(arr[cur] == searchKey){
				return cur;
			}
			else if(arr[cur] > searchKey){
				highIndex = cur - 1;
			}
			else{
				lowIndex = cur + 1;
			}
		}
		
		return -1;
	}
	
	public void insert(long elem){
		if(nElems == arr.length){
			System.err.println("数组已满，插入失败！");
			System.exit(-1);
		}
		int lowIndex = 0;
		int highIndex = nElems - 1;
		int cur,ins = 0;
		while(lowIndex<=highIndex){
			cur = (lowIndex + highIndex)/2;
			if(arr[cur] > elem){
				highIndex = cur - 1;
				ins = cur;
			}
			else if(arr[cur] < elem){
				lowIndex = cur + 1;
				ins = cur + 1;
			}
			else{
				ins = cur;
				break;
			}
		}
		//默认往后插入
		for(int j=nElems;j>ins;j--){
			arr[j] = arr[j-1];
		}
		arr[ins] = elem;
		nElems ++;
	}
	
	public boolean delete(long elem){
		int k = find(elem);
		if(k != -1){
			for(int j=k;j<nElems-1;j++){
				arr[j] = arr[j+1];
			}
			nElems --;
			return true;
		}
		return false;
	}
	
	//remove duplicates from a sorted array  T = O(n)
		public void removeDup(){
			int i = 1;
			int j = 0; //假想数组的开始下标（就是原数组）
			while(i<nElems){
				if(arr[i] == arr[j]){      //遍历原数组，相等则直接跳过
					i ++;
				}
				else{						//不等时，将新元素插入到假想数组
					j ++;
					arr[j] = arr[i];
					i ++;
				}
			}
			
			nElems = j+1;
		}
		
	
	public void display(){
		for(int i=0;i<nElems;i++){
			System.out.print(arr[i]+"\t");
		}
		System.out.println();
	}
}
