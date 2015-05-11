package Algorithms;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Solution {
	public static void main(String args[]){
		int n= 8;
		int[] arr = new int[n];
		produce(arr,n);
		System.out.println("排序之前： ");
		print(arr);
		System.out.println("----------------------------------");
		sort(arr);
		System.out.println("----------------------------------");
		System.out.println("排序之后： ");
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
		for(;count<n;){       //这里应该是count<n而不是count<=n；如果用小于等于，那么就是死循环，因为当产生n个不重复的数后，所有的数都有了，就会一直continue
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
     * 调用方法swap_with_zero来对array进行排序
     */
     public static void sort(int[] array) {
    	 int len = array.length;
         if(len <= 1){
             return;
         }
         for(int i = len - 1; i > 0; --i){       //从最后一位开始，将最大的数放到最大位置上，然后依次找次大的放
        	 if(array[i] == i) continue;		//已经相等，则不交换,避免不必要的重复交换
             swap_with_zero(array, array[i]);  //现将0和最后一位交换，以便将第n最大值换到第n大位置上
             print(array);
             int curMax = array[i];
             for(int j = i; j >= 0; --j){       //找出第n大的数
                  if(array[j] > curMax){
                      curMax = array[j];
                  }
             }
             swap_with_zero(array, curMax); //将第n大的数和0互换，从而放到第n大的位置上
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
