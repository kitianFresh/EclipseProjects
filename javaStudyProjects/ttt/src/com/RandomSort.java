package com;
import java.util.Random;
public class RandomSort {
	static final int num = 10;
	public static void main(String[] args){
		int[] arr = new int[num];
		Random r = new Random();
		System.out.println("����������������ǣ�");
		for(int i=0;i<num;i++){
			arr[i] = 1 + r.nextInt(100);
			System.out.print(arr[i] + "	");
		}
		System.out.println();
		int max = 0;
		int min = 100;
		for(int i=0;i<num;i++){
			if(arr[i] > max){
				max = arr[i];
			}
			if(arr[i] < min){
				min = arr[i];
			}
		}
		
		System.out.println("�����е����ֵ�ǣ� " + max);
		System.out.println("�����е���Сֵ�ǣ� " + min);
	}
}
