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
		File file=new File("dir/1.txt");//ע��ִ�иþ䣬��ʹ����ļ������ڣ�����ļ�Ҳ���ᱻ������ֻ��File��Ķ��󱻹����ˣ�����ļ����������
		//file.createNewFile();//File�޷��ݹ�Ĵ����ļ����ļ���,�þ�ᱨ���Ҳ���·��
		System.out.println(file.getAbsolutePath());

		   
		File file2=new File("/dir/1.txt");//����·��
		System.out.println(file2.getAbsolutePath());

		File file3=new File("1.txt");
		try {
			file3.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//����
		System.out.println(file3.getAbsolutePath()); 
		
		File file4=new File("2.txt");//���·��
		System.out.println(file4.getAbsolutePath());
		
		File file5=new File("");
		System.out.println(file5.getAbsolutePath());
		
		File file6=new File("RC/0.gif");
		System.out.println(file6.getAbsolutePath());
		
		File file7=new File("/RC/0.gif");//����·��
		System.out.println(file7.getAbsolutePath());
		
		System.out.println(System.getProperty("user.dir"));
		System.out.println("--------------------------------------------------------------");
	}
	
	//getResource()�����ó�������URL������������·��д����Ҫ�õ�������·��д����������...getResource().getPath()
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