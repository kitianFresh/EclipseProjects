package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArraySortTest {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//DataInputStream dis = new DataInputStream(System.in);//System.in�е����ݶ����ֽ�Ϊ��λ
		Integer[] arr = new Integer[10];
		int temp;
		String str=null;
		String[] strs = new String[10];
		try {
			str=br.readLine();
			strs = str.split(" ");
			if(strs.length != 10){
				System.out.println("Usage:Number Number ...,����10�������ÿո����");
				System.exit(-1);
			}
			for(int i=0;i<10;i++){
				arr[i] = Integer.parseInt(strs[i]);
			}
			
			/*//���ַ�����ȡ���鷳�����Բ���
			for(int i=0;i<10;i++){
				temp = dis.readInt();//����ӿ���̨���붼��һ����ascii�룬����һ��1������0x31(һ���ֽڲ���������������1)��һ���س�������0x0D��0x0A(�����ֽ�0x0D0A)
										//������readIntÿ��Ҫ���������ж��ĸ��ֽ�(0x0D0A0D0A=218762506)�������Ҫ40���ֽڲ������10��
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