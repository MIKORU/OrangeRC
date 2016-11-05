package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * �˿�1133
 * �ػ���������͸��ͻ���
 * 
 * ��Ϊ�������û� �� ����û���������
 * all �� �ƶ��ͻ��˵�ip
 * @author MIKORU
 *
 */
public class Shutdown implements Runnable{
	private String order;
	private DataOutputStream ou;
	private String ip;
	private Socket st;
	
	//�Ե����û�������
	public Shutdown(String order,int Iorder){
		this.order = order;
		 ip = RemoteServer.ips[Iorder];
	}
	//��ȫ���û�������
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
			
			//�����������ӵĿͻ��� ����������ȫ�廹�Ǹ���
			if(ip==null)
				ou.writeUTF("all");
			else{
				ou.writeUTF(stIp);
			}
			
			ou.writeUTF("mmc");
			Thread.sleep(2000);
			if(order.equals("�ػ�")){
				ou.writeUTF("shutdown -s -t 20");
			}else if(order.equals("����")){
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
