package Client;
import java.io.IOException;
import java.net.UnknownHostException;

import ClientUI.BigScreen;
import Document.CDocument;
import Document.CDocument1;


/**
 * 远程监控客户端
 * 
 * @author MIKORU
 *
 */
public class RemoteClient {
	private static String ip = "127.0.0.1";
	static String fpaths="0";
	public static void main(String[] args) throws UnknownHostException, IOException {
        //屏幕监听1113
        new Thread(new SendThread(ip)).start();
        
        //开关机指令接收1133
        new Thread(new ReceiveOrder(ip)).start();
        
        //鼠标控制指令1123
        new Thread(new EventThread()).start();
        
        //教师广播指令1153
        new Thread(new ReceiveThread(new BigScreen(),ip)).start();
        
        //文件传输指令接收
        new Thread(new CDocument1(ip,fpaths)).start();//8888
	}
}