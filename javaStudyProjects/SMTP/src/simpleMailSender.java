import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;;
public class simpleMailSender {
    String host = "";
    String user = "";
    String password = "";
    public void setHost(String host) {
        this.host = host;
    }
    public void setAccount(String user, String password) {
        this.user = user;
        this.password = password;
    }
    /*
* @param String �����˵ĵ�ַ
* 
* @param String �ռ��˵�ַ
* 
* @param String �ʼ�����
* 
* @param String �ʼ�����
*/
    
    public void send(String from, String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host); // ָ��SMTP������
        props.put("mail.smtp.auth", "true"); // ָ���Ƿ���ҪSMTP��֤
        try {
            Session mailSession = Session.getDefaultInstance(props);
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from)); // ������
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // �ռ���
            message.setSubject(subject); // �ʼ�����
            message.setText(content); // �ʼ�����
            message.saveChanges();
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
    	simpleMailSender sm = new simpleMailSender();
    	
        /*sm.setHost("smtp.163.com"); // ָ��Ҫʹ�õ��ʼ�������
        sm.setAccount("13237102479@163.com","163777tianqi"); // ָ���ʺź�����
        sm.send( "13237102479@163.com","1549722424@qq.com", "����", "HelloWold!");*/
        
        sm.setHost("smtp.qq.com"); // ָ��Ҫʹ�õ��ʼ�������
        sm.setAccount("1549722424@qq.com","qqmail777tianqi"); // ָ���ʺź�����
        sm.send( "1549722424@qq.com","13237102479@163.com", "�ʼ�����", "HelloWold!");
    }
}
