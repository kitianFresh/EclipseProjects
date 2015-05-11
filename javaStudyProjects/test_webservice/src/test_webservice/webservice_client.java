package test_webservice;
import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class webservice_client {
	public static void main(String[] args) throws Exception  
    {
        //  使用RPC方式调用WebService        
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        //  指定调用WebService的URL
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8080/axis2/services/SimpleService");
        options.setTo(targetEPR);
        //  指定getGreeting方法的参数值
        Object[] opAddEntryArgs = new Object[] {"田奇"};
        //  指定getGreeting方法返回值的数据类型的Class对象
        Class[] classes = new Class[] {String.class};
        //  指定要调用的getGreeting方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "getGreeting");
        //  调用getGreeting方法并输出该方法的返回值
        System.out.println(serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0]);
        //  下面是调用getPrice方法的代码，这些代码与调用getGreeting方法的代码类似
        classes = new Class[] {int.class};
        opAddEntry = new QName("http://ws.apache.org/axis2", "getPrice");
        System.out.println(serviceClient.invokeBlocking(opAddEntry, new Object[]{}, classes)[0]);
    } 
}
