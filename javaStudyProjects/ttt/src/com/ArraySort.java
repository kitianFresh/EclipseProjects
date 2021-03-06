package com;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArraySort {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer[] arr = new Integer[10];
		String str=null;
		String[] strs = new String[10];
		try {
			str=br.readLine();
			strs = str.split(" ");
			if(strs.length != 10){
				System.out.println("Usage:Number Number ...,输入10个数，用空格隔开");
				System.exit(-1);
			}
			for(int i=0;i<10;i++){
				arr[i] = Integer.parseInt(strs[i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				try {
					if(br == null) br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		Arrays.sort(arr);
		for(int i=0;i<10;i++){
			System.out.println(arr[i]);
		}
		
	}

}
