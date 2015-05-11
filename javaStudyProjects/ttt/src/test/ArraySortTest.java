package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArraySortTest {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//DataInputStream dis = new DataInputStream(System.in);//System.in中的数据都是字节为单位
		Integer[] arr = new Integer[10];
		int temp;
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
			
			/*//这种方法读取很麻烦，所以不用
			for(int i=0;i<10;i++){
				temp = dis.readInt();//这里从控制台输入都是一个个ascii码，比如一个1，就是0x31(一个字节并不是真正的整数1)，一个回车键就是0x0D和0x0A(两个字节0x0D0A)
										//而这里readInt每次要从输入流中读四个字节(0x0D0A0D0A=218762506)，因此需要40个字节才算读完10次
				arr[i] = temp;
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				try {
					//if(dis == null) dis.close();
					if(br == null) br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		Arrays.sort(arr);
		for(int i=0;i<10;i++){
			System.out.println(arr[i]);
		}
		
	}

}
