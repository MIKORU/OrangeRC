package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/***
 * 
 * �ػ����� ѧ���˽���ָ��ִ��
 * ���޲��ȫ��͸���
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
				
				//ȫ��ػ�����ָ�� ���� ��Ը����û�
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
