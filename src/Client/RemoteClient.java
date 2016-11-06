package Client;
import java.io.IOException;
import java.net.UnknownHostException;

import ClientUI.BigScreen;
import Document.CDocument;
import Document.CDocument1;


/**
 * Զ�̼�ؿͻ���
 * 
 * @author MIKORU
 *
 */
public class RemoteClient {
	private static String ip = "127.0.0.1";
	static String fpaths="0";
	public static void main(String[] args) throws UnknownHostException, IOException {
        //��Ļ����1113
        new Thread(new SendThread(ip)).start();
        
        //���ػ�ָ�����1133
        new Thread(new ReceiveOrder(ip)).start();
        
        //������ָ��1123
        new Thread(new EventThread()).start();
        
        //��ʦ�㲥ָ��1153
        new Thread(new ReceiveThread(new BigScreen(),ip)).start();
        
        //�ļ�����ָ�����
        new Thread(new CDocument1(ip,fpaths)).start();//8888
	}
}