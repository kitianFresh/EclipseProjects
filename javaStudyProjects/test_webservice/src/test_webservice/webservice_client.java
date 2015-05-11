package test_webservice;
import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class webservice_client {
	public static void main(String[] args) throws Exception  
    {
        //  ʹ��RPC��ʽ����WebService        
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        //  ָ������WebService��URL
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8080/axis2/services/SimpleService");
        options.setTo(targetEPR);
        //  ָ��getGreeting�����Ĳ���ֵ
        Object[] opAddEntryArgs = new Object[] {"����"};
        //  ָ��getGreeting��������ֵ���������͵�Class����
        Class[] classes = new Class[] {String.class};
        //  ָ��Ҫ���õ�getGreeting������WSDL�ļ��������ռ�
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "getGreeting");
        //  ����getGreeting����������÷����ķ���ֵ
        System.out.println(serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0]);
        //  �����ǵ���getPrice�����Ĵ��룬��Щ���������getGreeting�����Ĵ�������
        classes = new Class[] {int.class};
        opAddEntry = new QName("http://ws.apache.org/axis2", "getPrice");
        System.out.println(serviceClient.invokeBlocking(opAddEntry, new Object[]{}, classes)[0]);
    } 
}
