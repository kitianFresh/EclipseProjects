package Array;

public class HighArray {
	private long[] arr;
	private int nElems = 0;

	public HighArray(int Max) {
		arr = new long[Max];
	}

	public boolean find(long searchKey) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (arr[i] == searchKey) {
				break;
			}
		}
		if (i < nElems) {
			return true;
		} else {
			return false;
		}
	}

	public void insert(long elem) {
		if (nElems == arr.length) {
			System.err.println("��������������ʧ�ܣ�");
			System.exit(-1);
		}
		arr[nElems] = elem;
		nElems++;
	}

	public boolean delete(long elem) {
		int i, k;
		for (i = 0; i < nElems; i++) {
			if (arr[i] == elem) {
				break;
			}
		}
		if (i == nElems) {
			return false;
		} else {
			for (k = i; k < nElems - 1; k++) {
				arr[k] = arr[k + 1];
			}
			nElems--;
			return true;
		}

	}

	public long removeMax() {
		int i;
		long maxElem = 0;
		for (i = 0; i < nElems; i++) {
			if (arr[i] > maxElem) {
				maxElem = arr[i];
			}
		}
		delete(maxElem);
		return maxElem;
	}

	// Ч�ʲ��ߵ�д��
	public void noDup() {
		int i, j;
		for (i = 0; i < nElems; i++) {
			for (j = 0; j < nElems; j++) {
				if (arr[i] == arr[j] && j != i) {
					delete(arr[j]);
				}
			}
		}
	}
	
	//remove duplicates from a sorted array  T = O(n)
	public void removeDup(){
		int i = 1;
		int j = 0; //��������Ŀ�ʼ�±꣨����ԭ���飩
		while(i<nElems){
			if(arr[i] == arr[j]){      //����ԭ���飬�����ֱ������
				i ++;
			}
			else{						//����ʱ������Ԫ�ز��뵽��������
				j ++;
				arr[j] = arr[i];
				i ++;
			}
		}
		
		nElems = j+1;
	}
	
	
	/*//���趼����������0-nElems֮��ſ���
	public int RemoveRep(){
        long[] arrayflag = new long[nElems];
        int left = 0, i = 0;
        while(i<nElems)
        arrayflag[i++] = -1; //��ʼ����־����
 
        for(i=0;i<nElems;i++)//�޳��㷨
        {
                arrayflag[(int) arr[i]] = arr[i]; //�����ֹ��������浽��Ӧ��λ��
        }
 
        for(i=0;i<nElems;i++) //ȡ����Ч��
        {
                if(arrayflag[i] != -1)
                       arr[left++] = arrayflag[i];
        }
        nElems = left;
        return left;
	}
*/
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}
}
