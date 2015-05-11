package com;

public class MonthCounter {
	public static void main(String args[]){
		String[] s = args[0].split(",");
		if(s == null || s.length > 2){
			System.out.println("¸ñÊ½´íÎó£¡");
			System.out.println("usage: number1,number2");
			System.exit(-1);
		}
		int year0 = Integer.parseInt(s[0].substring(0,s[0].length()-2));
		int month0 = Integer.parseInt(s[0].substring(s[0].length()-2));
		int year1 = Integer.parseInt(s[1].substring(0,s[1].length()-2));
		int month1 = Integer.parseInt(s[1].substring(s[1].length()-2));
		System.out.println((year1-year0)*12 + month1 - month0 + 1);
	}
}
