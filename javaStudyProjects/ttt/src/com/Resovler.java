package com;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author qitian
 * ���һԪ���η��̵���,ֻ�����ʵ��������ϵ����Χ������double��Χ
 */
class Resovler {
	private double a;
	private double b;
	private double c;
	private Set<Double> results = new HashSet<Double>(2);
	/**
	 * ���췽��
	 * @param a  ������ϵ��
	 * @param b һ����ϵ��
	 * @param c ������
	 */
	public Resovler(double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	 * 
	 * @return ���ؼ��������ü���Set��ʾ
	 * ���Ϊ����⣬�򼯺���ֻ��һ��Double.NaNԪ�أ�
	 * ����������⣬�򼯺����������⣻
	 * �����һ���⣬�򼯺�����һ���⣻
	 * ����޽⣬�򼯺���û��Ԫ�أ�
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
