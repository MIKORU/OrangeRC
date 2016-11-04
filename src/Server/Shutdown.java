package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Shutdown implements Runnable{
	String order;
	DataOutputStream ou;
	String ip;
	Socket st;
	
	public Shutdown(String order,int Iorder) throws IOException{
		this.order = order;
		 ip = RemoteServer.ips[Iorder];
	}
	public Shutdown(String order){
		this.order = order;
		ip = null;
	}
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket server = new ServerSocket(1133);
			st = server.accept();
			String stIp = st.getInetAddress().getHostAddress();
			ou = new DataOutputStream(st.getOutputStream());
			if(ip==null)
				ou.writeUTF("all");
			else{
				//System.out.println(stIp);
				ou.writeUTF(stIp);
			}
			ou.writeUTF("mmc");
			//Thread.sleep(2000);
			if(order.equals("¹Ø»ú")){
				ou.writeUTF("shutdown -s -t 20");
			}else if(order.equals("ÖØÆô")){
				ou.writeUTF("shutdown -r -t 20");
			}
			ou.writeUTF("over");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		finally{
			try {
				st.close();
				ou.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
