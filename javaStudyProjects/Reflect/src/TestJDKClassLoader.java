import java.util.Map.Entry;

public class TestJDKClassLoader {

	public static void main(String[] args) {

		TestJDKClassLoader t = new TestJDKClassLoader();
		t.testClassLoaderClientage();
		t.printSystemInfo();
	}
	
	public void testClassLoaderClientage() {
		ClassLoader loader = this.getClass().getClassLoader();
		System.out.println("本类的类加载器: " + loader.getClass().getName());
		System.out.println("SystemClassLoader: "+ ClassLoader.getSystemClassLoader().getClass()
				.getName());//和上面的等价

		if (loader != null) {
			System.out.println("String 类的类加载器: "+String.class.getClassLoader());//Bootstrap
			System.out.println("AppClassLoader的直接加载器: "
					+ loader.getClass().getClassLoader());//Bootstrap
			ClassLoader fatherLoader = loader.getParent();
			System.out.println("本类的爷爷加载器名称: "
					+ fatherLoader.getClass().getName());
		}
	}
	
	public void printSystemInfo(){
		for (Entry<Object, Object> entry : System.getProperties().entrySet()) { 
            System.out.println(entry.getKey()+"\t"+entry.getValue()); 
		}
	}
}
