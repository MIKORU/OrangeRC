package Client;
import java.io.IOException;
import java.net.UnknownHostException;

import ClientUI.CBigScreen;
import ClientUI.ClientGUI;


/**
 * Զ�̼�ؿͻ���
 * 
 * @author MIKORU
 *
 */
public class RemoteClient {
	private static String ip = "127.0.0.1";
	public static void main(String[] args) throws UnknownHostException, IOException {
		//ϵͳ����
		new ClientGUI();
		
		//��Ļ����
        new Thread(new SendThread(ip)).start();
        
        //���ػ�ָ�����
        new Thread(new ReceiveOrder(ip)).start();
        
        //������ָ��
        new Thread(new EventThread()).start();
        
        //��ʦ�㲥ָ��
        new Thread(new ReceiveThread(new CBigScreen(),ip)).start();
    }
}