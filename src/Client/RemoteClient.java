package Client;
import java.io.IOException;
import java.net.UnknownHostException;

import ClientUI.CBigScreen;
import ClientUI.ClientGUI;


/**
 * 远程监控客户端
 * 
 * @author MIKORU
 *
 */
public class RemoteClient {
	private static String ip = "127.0.0.1";
	public static void main(String[] args) throws UnknownHostException, IOException {
		//系统托盘
		new ClientGUI();
		
		//屏幕监听
        new Thread(new SendThread(ip)).start();
        
        //开关机指令接收
        new Thread(new ReceiveOrder(ip)).start();
        
        //鼠标控制指令
        new Thread(new EventThread()).start();
        
        //教师广播指令
        new Thread(new ReceiveThread(new CBigScreen(),ip)).start();
    }
}