public class TestDynamicLoading {
	public static void main(String args[]){
		new A();
		System.out.println("------------------------------------------------");
		new B();
		
		new C();
		new C();
		
		new D();
		new D();
	}
}

class A {
	
}

class B {
	
}

class C {
	static{
		System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
	}
}

class D {
	{
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
	}
}