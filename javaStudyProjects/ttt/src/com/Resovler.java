package com;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author qitian
 * 求解一元二次方程的类,只能求解实数根，且系数范围不超过double范围
 */
class Resovler {
	private double a;
	private double b;
	private double c;
	private Set<Double> results = new HashSet<Double>(2);
	/**
	 * 构造方法
	 * @param a  二次项系数
	 * @param b 一次项系数
	 * @param c 常数项
	 */
	public Resovler(double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	 * 
	 * @return 返回计算结果，用集合Set表示
	 * 如果为任意解，则集合中只有一个Double.NaN元素；
	 * 如果有两个解，则集合中有两个解；
	 * 如果有一个解，则集合中有一个解；
	 * 如果无解，则集合中没有元素；
	 */
	public HashSet<Double> getResult(){
		if(a == 0 && b == 0){
			if(c == 0){
				results.add(Double.NaN);
			}
		}
		else if(a == 0 && b != 0){
			results.add(c/b);
		}
		else if(a != 0){
			double delt = b*b - 4*a*c;
			if(delt == 0){
				results.add(-b/a*0.5);
			}
			else if(delt > 0){
				double t = Math.sqrt(delt);
				results.add((-b+t)/a*0.5);
				results.add((-b-t)/a*0.5);
			}
		}
		return (HashSet<Double>) results;	
	}
	
	public static void main(String args[]){
		/*Resovler r = new Resovler(Double.parseDouble(args[0]), 
				Double.parseDouble(args[1]), Double.parseDouble(args[2]));*/
		Resovler r = new Resovler(1,2,-3);
		//System.out.println(r.a);
		Set<Double> s = r.getResult();
		for(double d: s){
			System.out.println(d);
		}
	}
}
