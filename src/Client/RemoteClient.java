package Client;
import java.io.IOException;
import java.net.UnknownHostException;



import Document.CDocument;
import Document.CDocument1;
import ServerUI.HelpUI;
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
		
		//��Ļ����1113

        new Thread(new SendThread(ip)).start();
        
        //���ػ�ָ�����1133
        new Thread(new ReceiveOrder(ip)).start();
        
        //������ָ��1123
        new Thread(new EventThread()).start();
        

        
        //�ļ�����ָ�����
        
        new Thread(new CDocument1(ip)).start();//8888
	

        //��ʦ�㲥ָ��1153
        new Thread(new ReceiveThread(new CBigScreen(),ip)).start();
    }

}