package com;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public class RandomAccess {
	static Set<Integer> arr = new HashSet<Integer>();
	int count = 0;
	public static void main(String args[]){
		new RandomAccess().produce();
		for (Integer i : arr) {
			System.out.print(i + "	");
		}
	}
	
	public void produce(){
		Random r = new Random();
		int rn;
		for(;count<900;){
			rn = r.nextInt(1000);
			if(arr.contains(rn)){
				continue;
			}
			arr.add(rn);
			System.out.print(rn + "	");
			count ++;
		}
		System.out.println();
	}
}
