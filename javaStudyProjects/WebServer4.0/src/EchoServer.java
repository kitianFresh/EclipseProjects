import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;


public class EchoServer {

	private ServerSocket serverSocket = null;
	private static final int ServerPort = 8080;
	private ExecutorService executorService = null;
	private final int POOL_SIZE = 4;
	
	private static final int shutdownPort = 8081;
	private ServerSocket shutSS = null;
	private boolean isShutdown = false;
	
	public static void main(String[] args) {
		new EchoServer().service();
	}
	
	private Thread ShutdownThread = new Thread(){
		public void start(){
			this.setDaemon(true);
			super.start();
		}
		
		public void run(){
			try {
				Socket socketForShutdown = null;
				while(!isShutdown){
					socketForShutdown = shutSS.accept();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(socketForShutdown.getInputStream()));
					String command = br.readLine();
					if("shutdown".equals(command)){
						long beginTime = System.currentTimeMillis();
						socketForShutdown.getOutputStream().write(("���������ڹرա�����\r\n").getBytes());
						isShutdown = true;
						//����ر��̳߳�
						//�̳߳ز��ڽ��������񣬵���������ִ�е���������ִ�У�
						executorService.shutdown();
						//�ȴ��ر��̳߳أ�ÿ�εȴ���ʱʱ��Ϊ30��
						while(!executorService.isTerminated()){
							executorService.awaitTermination(30, TimeUnit.SECONDS);
							System.out.println("�ȴ��̳߳عر��С���.");
						}
						if(serverSocket != null) serverSocket.close();
						long endTime = System.currentTimeMillis();
						socketForShutdown.getOutputStream().write(("�������ѹر�" + 
								" �رշ���������" + (endTime-beginTime) + "����\r\n").getBytes());
						Thread.sleep(1000);//������Ҫ����Ϊ�������䷢���ͻ��˻�����ʱ������ͻ��˻�û���յ�����ֱ��ִ������Ĺر�socket�����ܿͻ��˾��޷����ܵ�
						socketForShutdown.close();
						shutSS.close();
					}
					else{
						socketForShutdown.getOutputStream().write(("���������\r\n").getBytes());
						socketForShutdown.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	public EchoServer(){
		try {
			serverSocket = new ServerSocket(ServerPort);
			//serverSocket.setSoTimeout(60000);//һ�㲻����������������60����û�пͻ������ӣ���ô��serverSocket.accept()�ĵط����׳���ʱ�쳣�������޷��ټ�����
			shutSS = new ServerSocket(shutdownPort);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			System.out.println("�˿ڼ�����ʱ������");
		} catch (IOException e) {
			e.printStackTrace();
		}
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()*POOL_SIZE);
		ShutdownThread.start();
		System.out.println("cpu num: "+Runtime.getRuntime().availableProcessors());
		System.out.println("����������");
	}
	public void service(){
		Socket s = null;
		try {
			while(!isShutdown){
				 s = serverSocket.accept();
				 s.setSoTimeout(5*60000);
				 executorService.execute(new Handler(s));
			}
		} catch (SocketTimeoutException e) {
			System.out.println("ServerSocket timeout");
		} catch (RejectedExecutionException e){
			try {
				if(s != null) s.close();
			} catch (IOException e1) {
				System.out.println("RejectExecution....");
			}
			return;
		} catch(SocketException e){
			if(e.getMessage().indexOf("socket closed") != -1){ 
				System.out.println("Socket closed");
				return;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	} 

	
	
	private class Handler implements Runnable {
		Socket s = null;
		boolean bconnected = false;
		Handler(Socket s){
			this.s = s;
			bconnected = true;
		}
		@Override
		public void run() {
			System.out.println("New client Connected-" 
					+s.getInetAddress().getHostAddress()+":"+s.getPort());
			PrintWriter pw = this.getWriter(s);
			BufferedReader br = this.getReader(s);
			String msg = null;
			while(bconnected){
				try {
					while((msg = br.readLine())!=null){
						printMsg(msg);
						pw.println(echo(msg));
						pw.flush();
						if(msg.equals("bye"))
							break;
					}
					
				}catch(EOFException e){	
					System.out.println("Client closed1 !");
				}catch(SocketException e){
					System.out.println("Client closed2 !");
				}catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(s!=null)
						try {
							bconnected = false;
							s.close();
							br.close();
							pw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
			
		}
		
		private String echo(String msg) {
			return "hello: "+msg;
		}
		
		private void printMsg(String msg) {
			System.out.println("Client-" 
					+s.getInetAddress().getHostAddress()+":"+s.getPort() + "[" + msg + "]");
		}
		
		private PrintWriter getWriter(Socket s){
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return pw;
		}
		
		private BufferedReader getReader(Socket s){
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return br;
		}
		
	}
}
