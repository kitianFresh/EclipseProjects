package com.FirstJavaProgram;

public class student {

	    public  static void main(String[] args)
	 {
	   int a[]={1,2,3,4};//定义及初始化
	    int b[];//声明
	   int i;
	   b=a;
	  b[0]=4;
	 for(i=0;i<=3;i++)
	   System.out.print(a[i]+" ");

	 for(i=0;i<=3;i++)
	   System.out.print(b[i]+" ");
	 }
	//结果是可以改变a的值，说明他们公用的是同一片空间
}
