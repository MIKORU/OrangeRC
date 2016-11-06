package Document;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

public class Document1 extends Thread{
	public Socket socket;
	public File fi;
	public Document1(){
		
	}
	public Document1(Socket socket,File fi) {
		super();
		this.socket=socket;
		this.fi=fi;
		this.start();
	}
	@Override
	public void run(){
		//上传文件
//		while(true){
		try { 
			
			System.out.println("文件长度:" + (int) fi.length());
			// public Socket accept() throws
			// IOException侦听并接受到此套接字的连接。此方法在进行连接之前一直阻塞。
			/* DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
           dis.readByte();
			 */
			DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(fi)));
			DataOutputStream ps = new DataOutputStream(socket.getOutputStream());
			//将文件名及长度传给客户端。这里要真正适用所有平台，例如中文名的处理，还需要加工，具体可以参见Think In Java 4th里有现成的代码。
			ps.writeUTF(fi.getName());
			System.out.println(fi.getName());
			ps.flush();
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			while (true) {
				int read = 0;
				if (fis != null) {
					read = fis.read(buf);
				}
				if (read == -1) {
					break;
				}
				ps.write(buf, 0, read);
			}
			ps.flush();
			// 注意关闭socket链接哦，不然客户端会等待server的数据过来，
			// 直到socket超时，导致数据不完整。                
			fis.close();
			socket.close();                
			System.out.println("文件传输1完成");

		} catch (IOException e) {  
			e.printStackTrace();  
		}  
//		}
	}
	
}
