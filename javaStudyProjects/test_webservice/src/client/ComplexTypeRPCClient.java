package client;

import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class ComplexTypeRPCClient
{

    public static void main(String[] args) throws Exception
    {
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8080/axis2/services/ComplexTypeService");
        options.setTo(targetEPR);
        // ����Ĵ������uploadImageWithByte�����ϴ�ͼ���ļ�
        /////////////////////////////////////////
        // ��ͼ���ļ���ȷ��ͼ���ļ��Ĵ�С
        java.io.File file = new java.io.File("e:\\websevicetest.pdf");
        java.io.FileInputStream fis = new java.io.FileInputStream(file);
        // ��������Ҫ�ϴ���ͼ���ļ����ݵ��ֽ�����
        byte[] buffer = new byte[(int) file.length()];
        // ��ͼ���ļ������ݶ�ȡbuffer������
        int n = fis.read(buffer);
        System.out.println("�ļ����ȣ�" + file.length());
        Object[] opAddEntryArgs = new Object[]{ buffer, n };
        Class[] classes = new Class[]{ Boolean.class };
        QName opAddEntry = new QName("http://ws.apache.org/axis2","uploadImageWithByte");
        fis.close();
        // ��ʼ�ϴ�ͼ���ļ��������uploadImageWithByte�����ķ��ش�
        System.out.println(serviceClient.invokeBlocking(opAddEntry,opAddEntryArgs, classes)[0]);
        /////////////////////////////////////////
        
        // ����Ĵ��������getArray������������һάString����
        /////////////////////////////////////////  
        opAddEntry = new QName("http://ws.apache.org/axis2", "getArray");
        String[] strArray = (String[]) serviceClient.invokeBlocking(opAddEntry, 
                            new Object[]{}, new Class[]{String[].class })[0];
        for (String s : strArray)
            System.out.print(s + "  ");
        System.out.println();
        ///////////////////////////////////////// 
        

        // ����Ĵ��������getMDArray������������һάString����
        /////////////////////////////////////////  
        opAddEntry = new QName("http://ws.apache.org/axis2", "getMDArray");
        strArray = (String[]) serviceClient.invokeBlocking(opAddEntry, new Object[]{}, 
                                                          new Class[]{String[].class})[0];
        for (String s : strArray)
        {
            String[] array = s.split(",");
            for(String ss: array)
                System.out.print("<" + ss + "> ");
            System.out.println();
        }
        System.out.println();
        ///////////////////////////////////////// 

        // ����Ĵ��������getDataForm������������DataForm����ʵ��
        /////////////////////////////////////////  
        opAddEntry = new QName("http://ws.apache.org/axis2", "getDataForm");
        data.DataForm df = (data.DataForm) serviceClient.invokeBlocking(opAddEntry, new Object[]{},
                                                                  new Class[]{data.DataForm.class})[0];
        System.out.println(df.getAge());
        /////////////////////////////////////////
        
        // ����Ĵ��������getDataFormBytes�������������ֽ����飬��󽫷��ص��ֽ����鷴���л���ת����DataForm����ʵ��
        /////////////////////////////////////////          
        opAddEntry = new QName("http://ws.apache.org/axis2", "getDataFormBytes");
        buffer = (byte[]) serviceClient.invokeBlocking(opAddEntry, new Object[]{}, new Class[]{byte[].class})[0];
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                new java.io.ByteArrayInputStream(buffer));
        df = (data.DataForm) ois.readObject();
        System.out.println(df.getName());
        //////////////////////////////////////////
    }
}