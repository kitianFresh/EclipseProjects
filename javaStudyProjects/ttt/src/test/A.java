package test;

class B {
	static {
		System.out.println("	Static block B");
	}
	
	B(){
		System.out.println("	Construct B");
	}
	{
		System.out.println("	non-static block B");
	}
}

public class A extends B{
	static {
		System.out.println("	Static block A");
	}
	
	A(){
		System.out.println("	Construct A");
	}
	
	{
		System.out.println("	non-static block A");
	}
	
	public boolean ret(){
		try{
			return true;
		} catch(Exception e){
			
		} finally{
			return false;
		}
	}
	public static void main(String args[]){
		A a = new A();
		System.out.println(a.ret());
	}
}
