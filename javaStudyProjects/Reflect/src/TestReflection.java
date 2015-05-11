import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestReflection {
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String className = "T";
		Class c = Class.forName(className);
		Object o = c.newInstance();
		Method[] methods = c.getMethods();
		for(Method m : methods){
			//System.out.println(m.getName());
			if(m.getName().equals("mm")){
				m.invoke(o);
			}
			if(m.getName().equals("m1")){
				for(Class parameter : m.getParameterTypes()){
					System.out.println(parameter.getName());
				}
				m.invoke(o,1,2);
			}
		}
	}
}

class T {
	static{
		System.out.println("Class T loaded!!");
	}
	int i;
	String s;
	
	public T(){
		System.out.println("T constructed!!!");
	}
	public void m1(int i,int j){
		this.i = i + j;
		System.out.println(i);
	}
	public void mm(){
		System.out.println("mm invoked!!");
	}
	public String getS(){
		return this.s;
	}
}
