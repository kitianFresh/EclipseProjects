import java.util.Map.Entry;

public class TestJDKClassLoader {

	public static void main(String[] args) {

		TestJDKClassLoader t = new TestJDKClassLoader();
		t.testClassLoaderClientage();
		t.printSystemInfo();
	}
	
	public void testClassLoaderClientage() {
		ClassLoader loader = this.getClass().getClassLoader();
		System.out.println("������������: " + loader.getClass().getName());
		System.out.println("SystemClassLoader: "+ ClassLoader.getSystemClassLoader().getClass()
				.getName());//������ĵȼ�

		if (loader != null) {
			System.out.println("String ����������: "+String.class.getClassLoader());//Bootstrap
			System.out.println("AppClassLoader��ֱ�Ӽ�����: "
					+ loader.getClass().getClassLoader());//Bootstrap
			ClassLoader fatherLoader = loader.getParent();
			System.out.println("�����үү����������: "
					+ fatherLoader.getClass().getName());
		}
	}
	
	public void printSystemInfo(){
		for (Entry<Object, Object> entry : System.getProperties().entrySet()) { 
            System.out.println(entry.getKey()+"\t"+entry.getValue()); 
		}
	}
}
