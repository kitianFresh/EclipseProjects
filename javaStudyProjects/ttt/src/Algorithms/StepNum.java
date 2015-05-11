package Algorithms;

import java.util.Stack;

public class StepNum {
	/**
	 * 一个人爬楼梯，一步可以迈一级，二级，三级台阶，如果楼梯有N级，编写程序，输出所有走法。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	    System.out.println(Integer.MAX_VALUE);
	    long startt1 = System.currentTimeMillis();
	    NonRecursive(35);
	    long endt1 = System.currentTimeMillis();
	    System.out.println("NonRecursive: " + (endt1-startt1) + "ms");
	    
	    long startt2 = System.currentTimeMillis();
	    System.out.println("stepNum: " + Recursive(35));
	    long endt2 = System.currentTimeMillis(); 
	    System.out.println("Recursive: " + (endt2-startt2) + "ms");
	    
	    Stack<Integer> stt = new Stack<Integer>();
	    buileT(stt, 4);
	}

	public static void buileT(Stack<Integer> stt, int N) {
	    if (N >= 1) {
	        stt.push(1);
	        buileT(stt, N - 1);
	        stt.pop();
	    }
	    if (N >= 2) {
	        stt.push(2);
	        buileT(stt, N - 2);
	        stt.pop();
	    }
	    if (N >= 3) {
	        stt.push(3);
	        buileT(stt, N - 3);
	        stt.pop();
	    }
	    if (N == 0) {
	    	System.out.print("Step: ");
	        for (int i : stt) {
	            System.out.print(i + "-->");
	        }
	        System.out.println("完成");
	    }
	}
	
	public static void NonRecursive(int N){
		if(N == 0){
			System.out.println("stepNum: " + "0");
			return;
		}
		if(N == 1){
			System.out.println("stepNum: " + "1");
			return;
		}
		if(N == 2){
			System.out.println("stepNum: " + "2");
			return;
		}
		if(N == 3){
			System.out.println("stepNum: " + "4");
			return;
		}
		if(N > 3){
			long[] stepNum = new long[N+1];
			stepNum[0] = 0;
			stepNum[1] = 1;
			stepNum[2] = 2;
			stepNum[3] = 4;
			for(int i=4;i<=N;i++){
				stepNum[i] = stepNum[i-1] + stepNum[i-2] + stepNum[i-3];
			}
			System.out.println("stepNum: " + stepNum[N]);
			return;
		}
		
	}
	
	public static long Recursive(int N){
		switch (N) {
			case 0:
				return 0;
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				return 4;
			default:
				return (Recursive(N - 1) + Recursive(N - 2) + Recursive(N - 3));
		}	
	}
}
