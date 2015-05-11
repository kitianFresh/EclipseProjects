package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {
	public static void main(String[] args){
		Resovler r = new Resovler(1,2,3);
		//System.out.println(r.a);//a是不可见的
		System.out.println("请输入字符串：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		try {
			while((str = br.readLine()) != null){
				if("bye".equals(str)) return;
				System.out.println(isPalindrome(str));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isPalindrome(String str){
		
		int len = str.length();
		if(len == 0) return false;
		int left = 0,right = len-1;
		while(left <= right){
			if(str.charAt(left) != str.charAt(right)) return false;
			left ++;
			right --;
		}
		return true;
	}
}
