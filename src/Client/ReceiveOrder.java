package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/***
 * 
 * 关机重启 学生端接收指令执行
 * 分无差别全体和个人
 * 
 * @author MIKORU
 *
 */
public class ReceiveOrder implements Runnable{
	
	private boolean isAlive = true;
	
	private String order;
	private String ip;
	
	public ReceiveOrder(String ip){
		this.ip = ip;
	}
	public void run() {
		while(isAlive){
			try {
				Socket st = new Socket(ip,1133);
				DataInputStream ins = new DataInputStream(st.getInputStream());
				String Serverorder = ins.readUTF();
				String LocalIP = st.getInetAddress().getHostAddress();
				
				//全体关机重启指令 或者 针对个别用户
				if(("all".equals(Serverorder))||(LocalIP.equals(Serverorder))){
					while(!("over".equals(order = ins.readUTF()))){
						Runtime ec = Runtime.getRuntime();
						ec.exec(order);
					}
				}
			} catch (IOException e) {
			}
		}
	}
	
}
