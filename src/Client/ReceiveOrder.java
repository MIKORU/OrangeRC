package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReceiveOrder implements Runnable{
	public ReceiveOrder(){
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			ServerSocket server = new ServerSocket(1133);
			Socket st = server.accept();
			DataInputStream ins = new DataInputStream(st.getInputStream());
			String order = ins.readUTF();
			Runtime ec = Runtime.getRuntime();
			ec.exec(order);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
