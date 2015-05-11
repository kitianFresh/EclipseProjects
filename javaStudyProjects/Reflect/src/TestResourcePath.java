import java.io.File;
import java.io.IOException;


public class TestResourcePath {

	public static void main(String[] args) throws IOException {
		TestResourcePath t = new TestResourcePath();
		t.testFile();
		t.testClass();
		t.testClassLoader();
	}
	
	public void testFile(){
		File file=new File("dir/1.txt");//注意执行该句，即使这个文件不存在，这个文件也不会被创建，只是File类的对象被构造了，这个文件并不会产生
		//file.createNewFile();//File无法递归的创建文件及文件夹,该句会报错找不到路径
		System.out.println(file.getAbsolutePath());

		   
		File file2=new File("/dir/1.txt");//绝对路径
		System.out.println(file2.getAbsolutePath());

		File file3=new File("1.txt");
		try {
			file3.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//可行
		System.out.println(file3.getAbsolutePath()); 
		
		File file4=new File("2.txt");//相对路劲
		System.out.println(file4.getAbsolutePath());
		
		File file5=new File("");
		System.out.println(file5.getAbsolutePath());
		
		File file6=new File("RC/0.gif");
		System.out.println(file6.getAbsolutePath());
		
		File file7=new File("/RC/0.gif");//绝对路劲
		System.out.println(file7.getAbsolutePath());
		
		System.out.println(System.getProperty("user.dir"));
		System.out.println("--------------------------------------------------------------");
	}
	
	//getResource()函数得出来的是URL，不是真正的路径写法，要得到真正的路径写法，可以用...getResource().getPath()
	public void testClass(){
		System.out.println(this.getClass().getResource("RC/0.gif"));
		System.out.println(this.getClass().getResource("/RC/0.gif"));
		System.out.println(this.getClass().getResource("1.txt"));
		System.out.println(this.getClass().getResource("/1.txt"));
		System.out.println(this.getClass().getResource(""));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println("--------------------------------------------------------------");
	}
	
	public void testClassLoader(){
		System.out.println(this.getClass().getClassLoader().getResource("RC/0.gif"));
		System.out.println(this.getClass().getClassLoader().getResource("/RC/0.gif"));
		System.out.println(this.getClass().getClassLoader().getResource("1.txt"));
		System.out.println(this.getClass().getClassLoader().getResource("/1.txt"));
		System.out.println(this.getClass().getResource(""));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println();
	}
}
