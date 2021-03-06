package com;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

class Student {
	private String stuName;
	private String stuId;
	private double course1Grade;
	private double course2Grade;
	private double course3Grade;
	public Student(){}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public double getCourse1Grade() {
		return course1Grade;
	}
	public void setCourse1Grade(double course1Grade) {
		this.course1Grade = course1Grade;
	}
	public double getCourse2Grade() {
		return course2Grade;
	}
	public void setCourse2Grade(double course2Grade) {
		this.course2Grade = course2Grade;
	}
	public double getCourse3Grade() {
		return course3Grade;
	}
	public void setCourse3Grade(double course3Grade) {
		this.course3Grade = course3Grade;
	}
	
}

public class ScoreStu {
	public static void main(String[] args) throws FileNotFoundException{
		InputStream is = System.in;
		
		Scanner scanner = new Scanner(is);
		
		Student[] students = new Student[5];
		for(int i=0;i<5;i++){
			System.out.println("请输入第" + (i+1) + "个学生的信息");
			students[i] = new Student();
			System.out.print("姓名：");
			students[i].setStuName(scanner.next());
			System.out.print("学号：");
			students[i].setStuId(scanner.next());
			System.out.print("第一门成绩：");
			students[i].setCourse1Grade(scanner.nextDouble());
			System.out.print("第二门成绩：");
			students[i].setCourse2Grade(scanner.nextDouble());
			System.out.print("第三门成绩：");
			students[i].setCourse3Grade(scanner.nextDouble());
		}
		
		//getResource()函数得出来的是URL，不是真正的路径写法，要得到真正的路径写法，可以用...getResource().getPath()1
		
		File file = new File(ScoreStu.class.getResource("").getPath()+"stu.dat");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fis = null;
		DataOutputStream dis = null;
		try {
			fis = new FileOutputStream(file);
			dis = new DataOutputStream(fis);
			for(int i=0;i<5;i++){
				dis.writeUTF(students[i].getStuName());
				dis.writeUTF(students[i].getStuId());
				dis.writeDouble(students[i].getCourse1Grade());
				dis.writeDouble(students[i].getCourse2Grade());
				dis.writeDouble(students[i].getCourse3Grade());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
