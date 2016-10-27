package Document;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 教师接收文件   
 */
public class SDDocument extends Thread{

	private String ip = "127.0.0.1";// 设置成服务器IP
	private int port = 8822;//设置端口号
	public void start(){
		//从服务器端下载文件
		try { 
			Socket socket = new Socket(ip,port);
			while(true){  
				DataInputStream is = new  DataInputStream(socket.getInputStream());   
				OutputStream os = socket.getOutputStream();                  
				//1、得到文件名       
				String filename="E:\\";
				filename += is.readUTF(); 

				System.out.println("新生成的文件名为:"+filename);  
				FileOutputStream fos=new FileOutputStream(filename);
				//通过字节数组来缓冲数据（字节缓冲流）
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				DataOutputStream dos = new DataOutputStream(bos);
				byte[] b = new byte[1024]; 
				int length = 0;  
				while((length=is.read(b))!=-1){  
					//2、把socket输入流写到文件输出流中去  
					fos.write(b, 0, length);  
				}  
				dos.flush();  
				dos.close();               
				is.close();  
				socket.close();  
			}  

		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}   
	}
}
