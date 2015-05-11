package data;
import java.io.Serializable;
public class DataForm implements Serializable{
	private String name="bill";
	private int age=20;
	public String getName(){
		return name;
	}
	public void setName(String name){
	this.name=name;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age=age;
	}
} 