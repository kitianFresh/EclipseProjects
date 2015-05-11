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
        // 下面的代码调用uploadImageWithByte方法上传图像文件
        /////////////////////////////////////////
        // 打开图像文件，确定图像文件的大小
        java.io.File file = new java.io.File("e:\\websevicetest.pdf");
        java.io.FileInputStream fis = new java.io.FileInputStream(file);
        // 创建保存要上传的图像文件内容的字节数组
        byte[] buffer = new byte[(int) file.length()];
        // 将图像文件的内容读取buffer数组中
        int n = fis.read(buffer);
        System.out.println("文件长度：" + file.length());
        Object[] opAddEntryArgs = new Object[]{ buffer, n };
        Class[] classes = new Class[]{ Boolean.class };
        QName opAddEntry = new QName("http://ws.apache.org/axis2","uploadImageWithByte");
        fis.close();
        // 开始上传图像文件，并输出uploadImageWithByte方法的返回传
        System.out.println(serviceClient.invokeBlocking(opAddEntry,opAddEntryArgs, classes)[0]);
        /////////////////////////////////////////
        
        // 下面的代码调用了getArray方法，并返回一维String数组
        /////////////////////////////////////////  
        opAddEntry = new QName("http://ws.apache.org/axis2", "getArray");
        String[] strArray = (String[]) serviceClient.invokeBlocking(opAddEntry, 
                            new Object[]{}, new Class[]{String[].class })[0];
        for (String s : strArray)
            System.out.print(s + "  ");
        System.out.println();
        ///////////////////////////////////////// 
        

        // 下面的代码调用了getMDArray方法，并返回一维String数组
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

        // 下面的代码调用了getDataForm方法，并返回DataForm对象实例
        /////////////////////////////////////////  
        opAddEntry = new QName("http://ws.apache.org/axis2", "getDataForm");
        data.DataForm df = (data.DataForm) serviceClient.invokeBlocking(opAddEntry, new Object[]{},
                                                                  new Class[]{data.DataForm.class})[0];
        System.out.println(df.getAge());
        /////////////////////////////////////////
        
        // 下面的代码调用了getDataFormBytes方法，并返回字节数组，最后将返回的字节数组反序列化后，转换成DataForm对象实例
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