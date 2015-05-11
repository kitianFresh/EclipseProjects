package Algorithms;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Solution {
	public static void main(String args[]){
		int n= 8;
		int[] arr = new int[n];
		produce(arr,n);
		System.out.println("����֮ǰ�� ");
		print(arr);
		System.out.println("----------------------------------");
		sort(arr);
		System.out.println("----------------------------------");
		System.out.println("����֮�� ");
		print(arr);
	}
	
	public static void print(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(" " + arr[i] + "	");
		}
		System.out.println();
		for(int i=0;i<arr.length;i++){
			System.out.print("[" + i + "]" + "	");
		}
		System.out.println();
		System.out.println();
	}
	
	public static void produce(int[] rawArray, int n){
		Random r = new Random();
		int rn,count=0;
		Set<Integer> arr = new HashSet<Integer>();
		for(;count<n;){       //����Ӧ����count<n������count<=n�������С�ڵ��ڣ���ô������ѭ������Ϊ������n�����ظ����������е��������ˣ��ͻ�һֱcontinue
			rn = r.nextInt(n);
			if(arr.contains(rn)){
				continue;
			}
			arr.add(rn);
			count ++;
			rawArray[count-1] = rn;
			
			//System.out.print(rn + "	");
		}
		//System.out.println();
	}
	

    /**
     * ���÷���swap_with_zero����array��������
     */
     public static void sort(int[] array) {
    	 int len = array.length;
         if(len <= 1){
             return;
         }
         for(int i = len - 1; i > 0; --i){       //�����һλ��ʼ�����������ŵ����λ���ϣ�Ȼ�������Ҵδ�ķ�
        	 if(array[i] == i) continue;		//�Ѿ���ȣ��򲻽���,���ⲻ��Ҫ���ظ�����
             swap_with_zero(array, array[i]);  //�ֽ�0�����һλ�������Ա㽫��n���ֵ������n��λ����
             print(array);
             int curMax = array[i];
             for(int j = i; j >= 0; --j){       //�ҳ���n�����
                  if(array[j] > curMax){
                      curMax = array[j];
                  }
             }
             swap_with_zero(array, curMax); //����n�������0�������Ӷ��ŵ���n���λ����
             print(array);
         }
     }
     
     public static void swap_with_zero(int[] array, int number){
    	 	int len = array.length;
    	    int zIndex = -1;
    	    int nIndex = -1;
    	    for(int i = 0; i < len; ++i){
    	        if(array[i] == 0){
    	            zIndex = i;
    	        }
    	        if(array[i] == number){
    	            nIndex = i;
    	        }
    	    }
    	    int temp = array[zIndex];
    	    array[zIndex] = array[nIndex];
    	    array[nIndex] = temp;

    	}
}
