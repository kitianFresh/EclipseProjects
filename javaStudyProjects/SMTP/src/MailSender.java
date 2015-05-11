import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;

import Decoder.BASE64Encoder;


public class MailSender {
	String SMTPServer = "smtp.qq.com";
	int port = 25;
	Socket s = null;
	PrintWriter pw = null;
	BufferedReader br = null;
	DataInputStream dis = null;
	//private static final byte[] CRLF = { (byte)'\r', (byte)'\n' };
	
	public static void main(String[] args){
		/*Message msg = new Message("13237102479@163.com",
				"1549722424@qq.com",
				"FROM: 13237102479@163.com\r\n"
 + "TO: 1549722424@qq.com\r\n"
 + "SUBJECT: this is a test\r\n"
 + "Date: Fri, 8 Jan 2010 16:12:30\r\n"
+"X-Mailer: shadowstar's mailer\r\n"
 + "MIME-Version: 1.0\r\n"
  +"Content-type: text/plain"
				);
	*/
		Message msg = new Message("1549722424@qq.com",
				"13237102479@163.com",
				"FROM: 1549722424@qq.com\r\n"
						 + "TO: 13237102479@163.com\r\n"
						 + "SUBJECT: this is a test\r\n"		
				);
		
		new MailSender().sendMail(msg);
		
	}
	
	public void sendMail(Message msg){
		
			connect(SMTPServer,port);
			/*String userName = "13237102479@163.com";
			String passWord = "163777tianqi";*/
			
			String userName = "1549722424@qq.com";
			String passWord = "qqmail777tianqi";
			
			userName = new BASE64Encoder().encode(userName.getBytes());
			passWord = new BASE64Encoder().encode(passWord.getBytes());
			sendAndRecv("EHLO " + s.getLocalAddress().getHostAddress(), br, pw);
			sendAndRecv("AUTH LOGIN ", br, pw);
			sendAndRecv(userName, br, pw);
			sendAndRecv(passWord, br, pw);
			sendAndRecv("MAIL FROM:<" + msg.from + ">" , br, pw);
			sendAndRecv("RCPT TO:<" + msg.to +">", br, pw);
			
			sendAndRecv("DATA" , br, pw);
			sendAndRecv(msg.data , br, pw);
			pw.println();
			sendAndRecv(".",br,pw);
			sendAndRecv("QUIT",br,pw);
			
		
			if(s!=null){
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
	}
	
	public boolean connect(String SMTPServer, int port ){
		try {
			s = new Socket(SMTPServer,port);
			 pw = new PrintWriter(s.getOutputStream());
			 br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			 dis = new DataInputStream(s.getInputStream());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	} 
	
	public BufferedReader getBufferReader(Socket s) {
		BufferedReader br = null;
		InputStreamReader is = null;
		try {
			is = new InputStreamReader(s.getInputStream());
			
			br = new BufferedReader(is);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return br;
		
	}
	
	/*public DataInputStream getDataInputStream(Socket s) {
		DataInputStream dis = null;
		try {
			
			dis = new DataInputStream(s.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dis;
		
	}*/
	
	public PrintWriter getWriter(Socket s){
		PrintWriter pw;
		try {
			pw = new PrintWriter(s.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return pw;
	}
	
	public void sendAndRecv(String s, BufferedReader br, PrintWriter pw){
		if(s != null){
			System.out.println("Client>" + s);
			pw.println(s);
			pw.flush();
		}
		String response = null;
		   boolean compacted = false;
			try {
				if((response = br.readLine()) != null){
					if(response.subSequence(0, 4).equals("250-")){
						compacted = true;
					}
					while(compacted){
						if(response.subSequence(0, 4).equals("250 ")){
							compacted = false;
						}
						System.out.println("Sevrer>" + response);
						response = br.readLine();
					}
					if(!compacted){
						System.out.println("Sevrer>" + response);
					}
					
				}
				//System.out.println("Sevrer> disconnected");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	
/*	public void sendAndRecv(String s, DataInputStream dis, PrintWriter pw){
		if(s != null){
			System.out.println("Client>" + s);
			pw.println(s);
			pw.flush();
		}
		int response = -1;
		byte[] buf = new byte[1024];
		   boolean compacted = false;
			try {
				
				while((response = dis.read(buf)) != -1){
					//System.out.println("Sevrer>" + response);
				}
				System.out.println("Sevrer>" + buf.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}*/
}	


class Message {
	
	String from;
	String to;
	String data;
	
	public Message(String from, String to, String data) {
		this.from = from;
		this.to = to;
		this.data = data;
	}
}