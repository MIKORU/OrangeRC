package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * 端口1133
 * 关机重启命令发送给客户端
 * 
 * 分为给单个用户 和 多个用户发送命令
 * all 或 制定客户端的ip
 * @author MIKORU
 *
 */
public class Shutdown implements Runnable{
	private String order;
	private DataOutputStream ou;
	private String ip;
	private Socket st;
	
	//对单个用户的命令
	public Shutdown(String order,int Iorder){
		this.order = order;
		 ip = RemoteServer.ips[Iorder];
	}
	//对全体用户的命令
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
			
			//告诉所有连接的客户端 本次命令是全体还是个人
			if(ip==null)
				ou.writeUTF("all");
			else{
				ou.writeUTF(stIp);
			}
			
			ou.writeUTF("mmc");
			Thread.sleep(2000);
			if(order.equals("关机")){
				ou.writeUTF("shutdown -s -t 20");
			}else if(order.equals("重启")){
				ou.writeUTF("shutdown -r -t 20");
			}
			
			ou.writeUTF("over");
			
		} catch (IOException e) {
		}catch (InterruptedException e) {
		}
		finally{
			try {
				st.close();
				ou.close();
			} catch (IOException e) {
			}
		}
	}
}
