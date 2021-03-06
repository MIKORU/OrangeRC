package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Document.SDocument;
import ServerUI.GUI;
import ServerUI.Login;

/***
 * 远程监控服务器端
 * 
 * @author MIKORU
 */
public class RemoteServer {
    
    public static final int USERNUM = 60;
	public static String ip;
    public static Socket st;
    public static String[] ips = new String[USERNUM];
    
    private static int order = 0;
    private static int port;
    
	public static void main(String[] args) {
    	
    	//new Login();
    	new GUI();
    	port = 1113;
        ServerSocket server = null;  
        try {  
            server = new ServerSocket(port);
            new Thread(new SDocument()).start();
			while(true){
				st = server.accept();
				ip = st.getInetAddress().getHostAddress();

				int Sorder = getorder(ip);
				new Thread(new Receive(st,port,Sorder,ip)).start();

			}
        } catch (IOException e) {
        }
        
    }
	/**
	 * 对每个IP分配序号
	 * 
	 */
	public static int getorder(String oip){
    	for(int i=0;i<order;i++){
    		if(ips[i].equals(oip)){
    			return i;
    		}
    	}
    	ips[order]=oip;
    	order++;
    	return order-1;
    }
}  
  
  
